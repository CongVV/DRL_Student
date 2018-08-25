package hcmue.congvu.drlstudent.Adapter.SchoolAdapter;

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

    public String getmSchoolName(){
        return mSchoolName;
    }

    public int getmIdSchool(){
        return mIdSchool;
    }

}
