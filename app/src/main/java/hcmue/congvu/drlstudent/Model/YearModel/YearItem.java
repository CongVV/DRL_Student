package hcmue.congvu.drlstudent.Model.YearModel;

/**
 * Created by CongVu on 03/10/2018.
 */
public class YearItem {
    int mYearStart;
    int mYearEnd;

    public YearItem() {
    }

    public YearItem(int mYearStart, int mYearEnd) {
        this.mYearStart = mYearStart;
        this.mYearEnd = mYearEnd;
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
}
