package hcmue.congvu.drlstudent.Model.ActivityModel;

/**
 * Created by CongVu on 10/11/2018.
 */
public class ActivityManagementItem {
    String mUsername;
    String mFullname;
    String mActivityname;

    public ActivityManagementItem() {
    }

    public ActivityManagementItem(String mUsername, String mFullname, String mActivityname) {
        this.mUsername = mUsername;
        this.mFullname = mFullname;
        this.mActivityname = mActivityname;
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
