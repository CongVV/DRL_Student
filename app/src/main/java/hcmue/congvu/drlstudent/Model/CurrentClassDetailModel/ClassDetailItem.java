package hcmue.congvu.drlstudent.Model.CurrentClassDetailModel;

/**
 * Created by CongVu on 24/09/2018.
 */
public class ClassDetailItem {
    int mYearStart;
    int mYearEnd;
    int mTerm;
    int mIdClassDetail;
    boolean mIsAdmin;

    public ClassDetailItem(int mYearStart, int mYearEnd, int mTerm, int mIdClassDetail, boolean mIsAdmin) {
        this.mYearStart = mYearStart;
        this.mYearEnd = mYearEnd;
        this.mTerm = mTerm;
        this.mIdClassDetail = mIdClassDetail;
        this.mIsAdmin = mIsAdmin;
    }

    public ClassDetailItem() {
    }


    public int getmYearStart() {
        return mYearStart;
    }

    public void setmYearStart(int mYearStart) {
        this.mYearStart = mYearStart;
    }

    public int getmYearEnd() {
        return mYearEnd;
    }

    public void setmYearEnd(int mYearEnd) {
        this.mYearEnd = mYearEnd;
    }

    public int getmTerm() {
        return mTerm;
    }

    public void setmTerm(int mTerm) {
        this.mTerm = mTerm;
    }

    public int getmIdClassDetail() {
        return mIdClassDetail;
    }

    public void setmIdClassDetail(int mIdClassDetail) {
        this.mIdClassDetail = mIdClassDetail;
    }

    public boolean ismIsAdmin() {
        return mIsAdmin;
    }

    public void setmIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

}
