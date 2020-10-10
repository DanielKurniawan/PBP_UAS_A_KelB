package uts.uajy.kelompok_b_jualonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Space;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import uts.uajy.kelompok_b_jualonline.Fragment.Cart_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Chat_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Home_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.User_Fragment;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class MainActivity extends AppCompatActivity{
    public ArrayList<Barang> listCart;

    public Home_Fragment homeFragment;
    public Cart_Fragment cartFragment;
    public Chat_Fragment chatFragment;
    public User_Fragment userFragment;
    public sharedpref sharedpref;
    public Bundle mBundle;
    public Boolean checkTheme;

    private String CHANNEL_ID="Channel 1";
    Button signout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        sharedpref = new sharedpref(MainActivity.this);
        SharedPreferences sharedPreferences = getSharedPreferences("filename", Context.MODE_PRIVATE);
        checkTheme = sharedPreferences.getBoolean("NightMode",false);
        if(checkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            this.setTheme(R.style.darktheme);

        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            this.setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"MAIN",Toast.LENGTH_SHORT).show();
        homeFragment = new Home_Fragment();
        cartFragment = new Cart_Fragment();
        chatFragment = new Chat_Fragment();
        userFragment = new User_Fragment();
        listCart = new ArrayList<Barang>();

        BottomNavigationView botNav = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,homeFragment)
                .commit();

        //set default pagenya
//        botNav.setSelectedItemId(R.id.Fragment_Home);

//        NavController navController = Navigation.findNavController(this,  R.id.fragment);
//        NavigationUI.setupWithNavController(botNav, navController);


        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Fragment home_fragment  = new Home_Fragment();
                Fragment cart_fragment = new Cart_Fragment();
                Fragment chat_fragment = new Chat_Fragment();
                Fragment user_fragment = new User_Fragment();
                switch (item.getItemId()) {
                    case R.id.Fragment_Home :
                        loadFragment(home_fragment);
                        break;
                    case R.id.Fragment_Cart:
                        loadFragment(cart_fragment);
                        break;
                    case R.id.Fragment_Chat:
                        loadFragment(chat_fragment);
                        break;
                    case R.id.Fragment_User:
                        loadFragment(user_fragment);
                        break;
                }
                return true;
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {

            //R.id.fragment = fragment container di layout

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void getBundle() {
        mBundle = getIntent().getBundleExtra("register");
        sharedpref = mBundle.getParcelable("sharedpref");
    }

    public sharedpref getSharedpref (){
        return sharedpref;
    }

    private void createNotificationChannel(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Goodbye")
                .setContentText("Comeback Again...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notifcationIntent= new Intent(this,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0, notifcationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
    private void addNotification(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}