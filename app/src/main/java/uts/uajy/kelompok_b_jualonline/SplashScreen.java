package uts.uajy.kelompok_b_jualonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

import uts.uajy.kelompok_b_jualonline.util.MessagingService;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_DURATION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenlogo);
        Animation splashAnim = AnimationUtils.loadAnimation(this, R.anim.animasi);
        ImageView img = findViewById(R.id.splash_logo);
        img.setAnimation(splashAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_DURATION);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.animasi);


    }

}