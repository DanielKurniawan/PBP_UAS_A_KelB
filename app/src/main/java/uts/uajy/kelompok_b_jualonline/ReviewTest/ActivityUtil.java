package uts.uajy.kelompok_b_jualonline.ReviewTest;

import android.content.Context;
import android.content.Intent;

import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.SplashScreen;

public class ActivityUtil {
    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startMainActivity() {
        new Intent(context, SplashScreen.class);
    }
}