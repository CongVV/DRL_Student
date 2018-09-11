package hcmue.congvu.drlstudent.View.CreateClassView;

import org.json.JSONArray;

/**
 * Created by CongVu on 11/09/2018.
 */
public interface ViewProcessCreateClass {
    void createClassSucess();
    void createClassFail();
    void setSpinnerSchool(JSONArray listSchool);
}
