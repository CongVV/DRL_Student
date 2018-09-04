package hcmue.congvu.drlstudent.Model.UserModel;

import java.util.Date;

/**
 * Created by CongVu on 30/08/2018.
 */
public class UserInfo {
    private int idUser;
    private int idSchool;
    private String fullName;
    private String birthday;
    private String email;
    private String address;
    private int gender;
    private String avatar;

    public UserInfo() {
    }

    public UserInfo(int idUser, int idSchool, String fullName, String birthday, String email, String address, int gender, String avatar) {
        this.idUser = idUser;
        this.idSchool = idSchool;
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.avatar = avatar;
    }

    public void setUserInfo(UserInfo userInfo){
        this.idUser     = userInfo.idUser;
        this.idSchool   = userInfo.idSchool;
        this.fullName   = userInfo.fullName;
        this.birthday   = userInfo.birthday;
        this.email      = userInfo.email;
        this.address    = userInfo.address;
        this.gender     = userInfo.gender;
        this.avatar     = userInfo.avatar;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdSchool() {
        return idSchool;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getGender() {
        return gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdSchool(int idSchool) {
        this.idSchool = idSchool;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
