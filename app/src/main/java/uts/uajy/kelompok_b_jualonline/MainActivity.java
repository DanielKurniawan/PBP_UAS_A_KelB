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
}