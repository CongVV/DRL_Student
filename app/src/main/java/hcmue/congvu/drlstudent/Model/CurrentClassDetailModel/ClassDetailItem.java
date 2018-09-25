package hcmue.congvu.drlstudent.Model.CurrentClassDetailModel;

/**
 * Created by CongVu on 24/09/2018.
 */
public class ClassDetailItem {
    int mNumberStudent;
    int mYearStart;
    int mYearEnd;
    int mTerm;
    int mIdClass;
    String mClassDetailImg;

    public ClassDetailItem() {
    }

    public ClassDetailItem(int mNumberStudent, int mYearStart, int mYearEnd, int mTerm, int mIdClass, String mClassDetailImg) {
        this.mNumberStudent = mNumberStudent;
        this.mYearStart = mYearStart;
        this.mYearEnd = mYearEnd;
        this.mTerm = mTerm;
        this.mIdClass = mIdClass;
        this.mClassDetailImg = mClassDetailImg;
    }

    public int getmNumberStudent() {
        return mNumberStudent;
    }

    public void setmNumberStudent(int mNumberStudent) {
        this.mNumberStudent = mNumberStudent;
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

    public int getmIdClass() {
        return mIdClass;
    }

    public void setmIdClass(int mIdClass) {
        this.mIdClass = mIdClass;
    }

    public String getmClassDetailImg() {
        return mClassDetailImg;
    }

    public void setmClassDetailImg(String mClassDetailImg) {
        this.mClassDetailImg = mClassDetailImg;
    }
}
