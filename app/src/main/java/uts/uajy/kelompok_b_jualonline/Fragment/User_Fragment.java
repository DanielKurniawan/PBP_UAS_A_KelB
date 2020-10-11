package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class User_Fragment extends Fragment {
    private FloatingActionButton btnSettings;
    private MaterialButton btnUpdateProfilePicture;
    public SettingsFragment settingsFragment;

    //hardware
    private long lastUpdate=0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int CAMERA_PERMISSION_CODE = 1;

    // camera
//    private Camera mCamera = null;
//    private CameraView mCameraView = null;


    Boolean checkTheme;
    //    sharedpref sharedpref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_, container, false);

//        sharedpref = ((MainActivity)getActivity()).getSharedpref();
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

        settingsFragment = new SettingsFragment();
        btnSettings = view.findViewById(R.id.fab_settings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addFragment = new SettingsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(User_Fragment.this);
//                transaction.addToBackStack(null);
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
                        .setTitle("Are you sure want to change profile picture ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // camera permission

//                                if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                                    Toast.makeText(view.getContext(),"Permission Not Granted Yet",Toast.LENGTH_SHORT).show();
//                                    requestCameraPermission(view);
//                                }
//                                else {
//                                    Toast.makeText(view.getContext(),"Permission Already Granted",Toast.LENGTH_SHORT).show();
//                                }

                                // camera Activity
//                                Intent intent = new Intent(view.getContext(), CameraActivity.class);
//                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
        return view;
    }
//    private void requestCameraPermission(View view) {
//        if(ActivityCompat.shouldShowRequestPermissionRationale(, Manifest.permission.CAMERA)) {
//            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
//        }
//        else {
//            ActivityCompat.requestPermissions(MainActivity.this , new String[] {Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
//        }
//    }
}