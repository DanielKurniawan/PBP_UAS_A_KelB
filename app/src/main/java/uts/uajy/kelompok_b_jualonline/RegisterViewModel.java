package uts.uajy.kelompok_b_jualonline;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import uts.uajy.kelompok_b_jualonline.BR;
import uts.uajy.kelompok_b_jualonline.UserAccount2;
import uts.uajy.kelompok_b_jualonline.Register;

import java.util.HashMap;
import java.util.Map;

public class RegisterViewModel extends BaseObservable {
    private UserAccount2 user;

    public RegisterViewModel() {
        user = new UserAccount2("", "", "", "", "","");
    }


    @Bindable
    public String getUserAccountUsername(){
        return user.getUsername();
    }
    public void setUserAccountUsername(String username){
        user.setUsername(username);
        notifyPropertyChanged(BR.userAccountUsername);
    }

    @Bindable
    public String getUserAccountFName(){
        return user.getFName();
    }
    public void setUserAccountFName(String fName){
        user.setFName(fName);
        notifyPropertyChanged(BR.userAccountFName);
    }

    @Bindable
    public String getUserAccountPhone(){
        return user.getPhone();
    }
    public void setUserAccountPhone(String Phone){
        user.setPhone(Phone);
        notifyPropertyChanged(BR.userAccountPhone);
    }

    @Bindable
    public String getUserAccountAddress(){
        return user.getAddress();
    }
    public void setUserAccountAddress(String Address){
        user.setAddress(Address);
        notifyPropertyChanged(BR.userAccountAddress);
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



    public void btnSignUp(final View view) {
//        btnSignUp().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getUserAccountUsername().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Username Tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else if(getUserAccountFName().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Full name tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else if(getUserAccountPhone().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Phone number tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else if(getUserAccountAddress().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Address tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else if(getUserAccountEmail().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Email tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else if(getUserAccountPassword().equalsIgnoreCase("")){
//                    Toast.makeText(view.getContext(),"Password tidak boleh kosong",Toast.LENGTH_SHORT).show();
//                }else{
                    Toast.makeText(view.getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                    Register.startLoginActivity(view.getContext());
                }
//            }
//        });

//    }

}