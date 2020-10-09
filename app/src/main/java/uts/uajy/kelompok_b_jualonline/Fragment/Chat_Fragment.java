package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uts.uajy.kelompok_b_jualonline.MainActivity;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class Chat_Fragment extends Fragment {

    sharedpref sharedpref;
    Boolean checkTheme;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        sharedpref = ((MainActivity)getActivity()).getSharedpref();
        View view = inflater.inflate(R.layout.fragment_chat_, container, false);
//        if(sharedpref.loadNightModeState()==true) {
//            getActivity().setTheme(R.style.darktheme);
//        }
//        else{
//            getActivity().setTheme(R.style.darktheme);
//        }
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
        return view;
    }
}