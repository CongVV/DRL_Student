package hcmue.congvu.drlstudent.View.ManagementClassView;

import org.json.JSONArray;

/**
 * Created by CongVu on 18/11/2018.
 */
public interface ViewProcessManagementClass {
    void setStudentClassList(JSONArray jsonArray);
    void setClassListTypeStudent(JSONArray jsonArray);
    void resultDeleteStudentClass(boolean result);
    void resultTypeStudentClass(boolean result);
}
