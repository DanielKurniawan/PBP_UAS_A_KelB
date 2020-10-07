package uts.uajy.kelompok_b_jualonline;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

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
}
