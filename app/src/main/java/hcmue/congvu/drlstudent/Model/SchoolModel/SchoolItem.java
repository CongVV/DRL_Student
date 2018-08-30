package hcmue.congvu.drlstudent.Model.SchoolModel;

/**
 * Created by CongVu on 25/08/2018.
 */
public class SchoolItem {
    private String mSchoolName;
    private int mIdSchool;

    public SchoolItem(String schoolName, int idSchool){
        mSchoolName = schoolName;
        mIdSchool = idSchool;
    }

    public SchoolItem() {
    }

    public void setmSchoolName(String mSchoolName) {
        this.mSchoolName = mSchoolName;
    }

    public void setmIdSchool(int mIdSchool) {
        this.mIdSchool = mIdSchool;
    }

    public String getmSchoolName(){
        return mSchoolName;
    }

    public int getmIdSchool(){
        return mIdSchool;
    }

}
