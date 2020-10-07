package uts.uajy.kelompok_b_jualonline;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.databinding.ActivityLoginBinding;
import uts.uajy.kelompok_b_jualonline.LoginViewModel;

public class ActivityLogin extends AppCompatActivity{
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.setLVM(new LoginViewModel());
        binding.executePendingBindings();
    }
    public static void startMainActivity(Context mContext) {
        Intent i = new Intent(mContext, MainActivity.class);
        mContext.startActivity(i);
    }
}
