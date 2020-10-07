package uts.uajy.kelompok_b_jualonline;



public class UserAccount2  {

    private String Username;
    private String FName;
    private String Phone;
    private String Address;
    private String Email;
    private String Password;

    public UserAccount2() {
    }

    public UserAccount2(String Username, String FName, String Phone, String Address, String Email, String Password) {
        this.Username = Username;
        this.FName = FName;
        this.Phone = Phone;
        this.Address = Address;
        this.Email = Email;
        this.Password = Password;
    }

    public UserAccount2(String s, String s1) {
    }


    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

}