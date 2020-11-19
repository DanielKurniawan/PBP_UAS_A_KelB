package uts.uajy.kelompok_b_jualonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Objects;

import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;
import uts.uajy.kelompok_b_jualonline.util.MessagingService;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_DURATION = 3000;
    public Boolean checkTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenlogo);

        Animation splashAnim = AnimationUtils.loadAnimation(this, R.anim.animasi);
        ImageView img = findViewById(R.id.splash_logo);
        img.setAnimation(splashAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,ActivityLogin.class);
                startActivity(i);
                finish();
            }
        },SPLASH_DURATION);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.animasi);


    }
}