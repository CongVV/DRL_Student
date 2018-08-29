package hcmue.congvu.drlstudent.View.SignUpView;

import org.json.JSONArray;

/**
 * Created by CongVu on 28/08/2018.
 */
public interface ViewProcessSignUp {
    void signUpSuccessfull();
    void signUpFail();
    boolean validateForm();
    void setSpinnerSchool(JSONArray listSchool);
}
