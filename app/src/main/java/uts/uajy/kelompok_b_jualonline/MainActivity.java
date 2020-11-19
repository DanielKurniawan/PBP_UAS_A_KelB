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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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
import com.google.firebase.messaging.FirebaseMessaging;

import uts.uajy.kelompok_b_jualonline.Fragment.Cart_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Chat_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Home_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.User_Fragment;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class MainActivity extends AppCompatActivity{
    public List<Barang> listCart;

    Fragment home_fragment  = new Home_Fragment();
    Fragment cart_fragment = new Cart_Fragment();
    Fragment chat_fragment = new Chat_Fragment();
    Fragment user_fragment = new User_Fragment();
    public sharedpref sharedpref;
    public Bundle mBundle;
    public Boolean checkTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("filename", Context.MODE_PRIVATE);
        checkTheme = sharedPreferences.getBoolean("NightMode",false);
        if(checkTheme) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            this.setTheme(R.style.darktheme);

        }
        else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            this.setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
            String CHANNEL_ID = "Channel 1";
            CharSequence name = "Channel 1";
            String description  = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)  {
                        String mag = "Successful";
                        if(!task.isSuccessful()){
                            mag="Failed";
                        }
                        Toast.makeText(MainActivity.this, mag, Toast.LENGTH_SHORT).show();
                    }
                });


        listCart = new ArrayList<Barang>();

        BottomNavigationView botNav = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,home_fragment)
                .commit();


        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //create fragment without recreating previous fragment
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.hide(User_Fragment.this);
//                transaction.addToBackStack(null);
//                transaction.replace(R.id.fragment,settingsFragment);
//                transaction.commit()
                Fragment fragment = null;

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
}