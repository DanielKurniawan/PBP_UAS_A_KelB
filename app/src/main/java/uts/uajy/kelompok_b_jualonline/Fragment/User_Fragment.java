package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class User_Fragment extends Fragment {
    public FloatingActionButton btnSettings;
    public SettingsFragment settingsFragment;
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
                transaction.replace(R.id.fragment,settingsFragment);
                transaction.commit();
            }
        });

        return view;
    }
}