package hcmue.congvu.drlstudent.Model.ActivityModel;

/**
 * Created by CongVu on 10/11/2018.
 */
public class ActivityManagementItem {
    int idUser, idActivity;
    String mUsername;
    String mFullname;
    String mActivityname;

    public ActivityManagementItem() {
    }

    public ActivityManagementItem(int idUser, int idActivity, String mUsername, String mFullname, String mActivityname) {
        this.idUser = idUser;
        this.idActivity = idActivity;
        this.mUsername = mUsername;
        this.mFullname = mFullname;
        this.mActivityname = mActivityname;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmFullname() {
        return mFullname;
    }

    public void setmFullname(String mFullname) {
        this.mFullname = mFullname;
    }

    public String getmActivityname() {
        return mActivityname;
    }

    public void setmActivityname(String mActivityname) {
        this.mActivityname = mActivityname;
    }
}
