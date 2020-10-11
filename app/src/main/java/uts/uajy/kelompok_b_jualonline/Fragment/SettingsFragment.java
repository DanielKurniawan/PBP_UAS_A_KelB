package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.MapActivity;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.SplashScreen;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class SettingsFragment extends Fragment {
    public SwitchMaterial themeSwitch;
    public Boolean checkSwitch;
    public MaterialButton btnAboutUs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
//        sharedpref = ((MainActivity)getActivity()).getSharedpref();
        themeSwitch = view.findViewById(R.id.themeSwitch);
        btnAboutUs = view.findViewById(R.id.btnAboutUs);
        load();

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getContext().setTheme(R.style.darktheme);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getContext().setTheme(R.style.darktheme);
                }
                //save the state
//                sharedpref.setNightModeState(themeSwitch.isChecked());
                save();
            }
        });
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MapActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void load() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("filename",Context.MODE_PRIVATE);
        checkSwitch = sharedPreferences.getBoolean("NightMode",false);
        if (checkSwitch) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getContext().setTheme(R.style.darktheme);
            themeSwitch.setChecked(true);
        }
        else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getContext().setTheme(R.style.AppTheme);
            themeSwitch.setChecked(false);
        }
    }

    public void save() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("filename",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode",themeSwitch.isChecked());
        editor.commit();
    }
}