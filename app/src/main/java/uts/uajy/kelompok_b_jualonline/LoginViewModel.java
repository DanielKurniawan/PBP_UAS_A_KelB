package uts.uajy.kelompok_b_jualonline;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import uts.uajy.kelompok_b_jualonline.util.FireAuthentication;
import uts.uajy.kelompok_b_jualonline.BR;
import uts.uajy.kelompok_b_jualonline.UserAccount2;
import uts.uajy.kelompok_b_jualonline.ActivityLogin;
import uts.uajy.kelompok_b_jualonline.Register;

import static android.service.controls.ControlsProviderService.TAG;

public class LoginViewModel extends BaseObservable{
    private UserAccount2 user;

    public LoginViewModel() {
        user = new UserAccount2("", "");
    }



    @Bindable
    public String getUserAccountEmail(){
        return user.getEmail();
    }
    public void setUserAccountEmail(String Email){
        user.setEmail(Email);
        notifyPropertyChanged(BR.userAccountEmail);
    }

    @Bindable
    public String getUserAccountPassword(){
        return user.getPassword();
    }
    public void setUserAccountPassword(String Password){
        user.setPassword(Password);
        notifyPropertyChanged(BR.userAccountPassword);
    }

    public void btnLogin(final View view) {
        if (!TextUtils.isEmpty(this.getUserAccountEmail()) && !TextUtils.isEmpty(this.getUserAccountPassword())) {
            FireAuthentication.getInstance().Login(this.getUserAccountEmail(), this.getUserAccountPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.i(TAG, "Login sukses !!!");
                                ActivityLogin.startMainActivity(view.getContext());
                                Activity activity = (Activity) view.getContext();
                                activity.finish();
                            } else {
                                Log.i(TAG, "Login gagal");
                                Toast.makeText(view.getContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(view.getContext(), "Input salah", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnSignUp(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), Register.class));
    }
}
