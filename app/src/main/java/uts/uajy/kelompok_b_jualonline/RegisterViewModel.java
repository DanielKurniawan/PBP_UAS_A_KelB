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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;

import uts.uajy.kelompok_b_jualonline.BR;
import uts.uajy.kelompok_b_jualonline.UserAccount2;
import uts.uajy.kelompok_b_jualonline.Register;
import uts.uajy.kelompok_b_jualonline.util.FireAuthentication;

import java.util.HashMap;
import java.util.Map;

import static android.service.controls.ControlsProviderService.TAG;

public class RegisterViewModel extends BaseObservable {
    private UserAccount2 user;
    private String ID;

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
        if(getUserAccountEmail().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if(getUserAccountUsername().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if(getUserAccountAddress().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Address tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if(getUserAccountFName().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Full name tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if(getUserAccountPhone().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Phone number tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else if(getUserAccountPassword().equalsIgnoreCase("")){
            Toast.makeText(view.getContext(), "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else{
            FireAuthentication.getInstance().Register(this.getUserAccountEmail(), this.getUserAccountPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                ID = FireAuthentication.getInstance().getUser().getUid();
                                DocumentReference documentReference = FireAuthentication.getInstance()
                                        .getFirestoreInstance()
                                        .collection("users").document(ID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fName", getUserAccountFName());
                                user.put("Address", getUserAccountAddress());
                                user.put("Phone", getUserAccountPhone());
                                user.put("Username", getUserAccountUsername());

                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        FireAuthentication.getInstance().Logout();
                                        Register.startActivityLogin(view.getContext());
                                        Toast.makeText(view.getContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                Toast.makeText(view.getContext(), "Registrasi gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
        }

    }

