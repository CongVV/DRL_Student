package hcmue.congvu.drlstudent.Model.ActivityModel;

/**
 * Created by CongVu on 08/11/2018.
 */
public class ActivityStudentInfoItem {
    String mContentActivity;
    int mStatus;
    int mScores;

    public ActivityStudentInfoItem() {
    }

    public ActivityStudentInfoItem(String mContentActivity, int mStatus, int mScores) {
        this.mContentActivity = mContentActivity;
        this.mStatus = mStatus;
        this.mScores = mScores;
    }

    public String getmContentActivity() {
        return mContentActivity;
    }

    public void setmContentActivity(String mContentActivity) {
        this.mContentActivity = mContentActivity;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public int getmScores() {
        return mScores;
    }

    public void setmScores(int mScores) {
        this.mScores = mScores;
    }
}
