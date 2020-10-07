package uts.uajy.kelompok_b_jualonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import uts.uajy.kelompok_b_jualonline.Fragment.Cart_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Chat_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.Home_Fragment;
import uts.uajy.kelompok_b_jualonline.Fragment.User_Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView botNav = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragment);
        NavigationUI.setupWithNavController(botNav, navController);


        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.Fragment_Home :
                        fragment = new Home_Fragment(); break;
                    case R.id.Fragment_Cart:
                        fragment = new Cart_Fragment(); break;
                    case R.id.Fragment_Chat:
                        fragment = new Chat_Fragment(); break;
                    case R.id.Fragment_User:
                        fragment = new User_Fragment(); break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            //R.id.fragment = fragment container di layout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}