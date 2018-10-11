package hcmue.congvu.drlstudent.Model.ActivityModel;

/**
 * Created by CongVu on 09/10/2018.
 */
public class ActivityGroupItem {
    int mId;
    String mName;

    public ActivityGroupItem() {
    }

    public ActivityGroupItem(int mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
