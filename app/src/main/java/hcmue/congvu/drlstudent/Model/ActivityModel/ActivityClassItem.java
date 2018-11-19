package hcmue.congvu.drlstudent.Model.ActivityModel;

/**
 * Created by CongVu on 11/10/2018.
 */
public class ActivityClassItem {
    int mId, mIdGroup, mIdLevel, mScores, mTypeUser;
    String mDateTimeStart, mDateTimeEnd, mContent;

    public ActivityClassItem() {
    }

    public ActivityClassItem(int mId, int mIdGroup, int mIdLevel, int mScores, int mTypeUser, String mDateTimeStart, String mDateTimeEnd, String mContent) {
        this.mId = mId;
        this.mIdGroup = mIdGroup;
        this.mIdLevel = mIdLevel;
        this.mScores = mScores;
        this.mTypeUser = mTypeUser;
        this.mDateTimeStart = mDateTimeStart;
        this.mDateTimeEnd = mDateTimeEnd;
        this.mContent = mContent;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmIdGroup() {
        return mIdGroup;
    }

    public void setmIdGroup(int mIdGroup) {
        this.mIdGroup = mIdGroup;
    }

    public int getmIdLevel() {
        return mIdLevel;
    }

    public void setmIdLevel(int mIdLevel) {
        this.mIdLevel = mIdLevel;
    }

    public int getmScores() {
        return mScores;
    }

    public void setmScores(int mScores) {
        this.mScores = mScores;
    }

    public int getmTypeUser() {
        return mTypeUser;
    }

    public void setmTypeUser(int mTypeUser) {
        this.mTypeUser = mTypeUser;
    }

    public String getmDateTimeStart() {
        return mDateTimeStart;
    }

    public void setmDateTimeStart(String mDateTimeStart) {
        this.mDateTimeStart = mDateTimeStart;
    }

    public String getmDateTimeEnd() {
        return mDateTimeEnd;
    }

    public void setmDateTimeEnd(String mDateTimeEnd) {
        this.mDateTimeEnd = mDateTimeEnd;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }
}
