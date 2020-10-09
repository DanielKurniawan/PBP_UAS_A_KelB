package uts.uajy.kelompok_b_jualonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.databinding.RegisterBinding;
import uts.uajy.kelompok_b_jualonline.RegisterViewModel;

public class Register extends AppCompatActivity {
    private RegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.register);
        binding.setRVM(new RegisterViewModel());
        binding.executePendingBindings();
    }
    public static void startActivityLogin(Context mContext) {
        Intent i = new Intent(mContext, MainActivity.class);
        mContext.startActivity(i);
    }
}
