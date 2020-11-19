package uts.uajy.kelompok_b_jualonline.Fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;

import uts.uajy.kelompok_b_jualonline.ActivityLogin;
import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class User_Fragment extends Fragment {
    private FloatingActionButton btnSettings;
    private MaterialButton btnUpdateProfilePicture,btnSignout;
    public SettingsFragment settingsFragment;
    public MaterialTextView txtEmail;

    //hardware
    private long lastUpdate=0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int CAMERA_PERMISSION_CODE = 1;

    private String CHANNEL_ID = "Channel 1" ;


    Boolean checkTheme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("filename", Context.MODE_PRIVATE);
        checkTheme = sharedPreferences.getBoolean("NightMode",false);
        if(checkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getContext().setTheme(R.style.darktheme);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getContext().setTheme(R.style.AppTheme);
        }

        //retrieve the email
        txtEmail = view.findViewById(R.id.txtEmail);
        SharedPreferences sharedEmail = getContext().getSharedPreferences("userEmail",Context.MODE_PRIVATE);
        String email = sharedEmail.getString("email","");
        txtEmail.setText(email);

        settingsFragment = new SettingsFragment();
        btnSettings = view.findViewById(R.id.fab_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new SettingsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(User_Fragment.this);
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment,settingsFragment);
                transaction.commit();
            }
        });

        // INI BUTTON UNTUK UPDATE PROFILE PICTURE
        btnUpdateProfilePicture = view.findViewById(R.id.btnUpdateProfile);
        btnUpdateProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("TENTANG DEVELOPER")
                        .setMessage("Aplikasi PBP Mart dibuat dan dikembangkan oleh kelompok B :\n\n\n" +
                                "1. Cornellius Philipo Julianto / 180709605\n" +
                                "2. Daniel Axcella Kurniawan / 180709738\n" +
                                "3. Julius Donald Giftiardi / 180709834")
                        .show();
            }
        });

        btnSignout = view.findViewById(R.id.btn_signout);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Sign Out Alert")
                        .setMessage("Are you sure want to sign out ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseAuth.getInstance().signOut();
                                createNotificationChannel(view);
                                addNotificaion("Goodbye","Comeback Again...",view);
                                startActivity(new Intent(view.getContext(), ActivityLogin.class));
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        return view;
    }

    public void createNotificationChannel(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = view.getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    public void addNotificaion(String title, String desc, View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(Notification.PRIORITY_DEFAULT);


        //intent yang menampilkan notifikasi
        Intent notificationIntent = new Intent(view.getContext(), MainActivity.class);
        PendingIntent ContentIntent = PendingIntent.getActivity(view.getContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(ContentIntent);

        //tampil notifikasi
        NotificationManager manager = (NotificationManager) view.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}