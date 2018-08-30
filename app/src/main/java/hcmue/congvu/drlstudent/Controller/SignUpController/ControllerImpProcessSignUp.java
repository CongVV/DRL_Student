package hcmue.congvu.drlstudent.Controller.SignUpController;

import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;
import hcmue.congvu.drlstudent.Model.UserModel.UserItem;

/**
 * Created by CongVu on 28/08/2018.
 */
public interface ControllerImpProcessSignUp {
    void validateUser(String username);
    void getSchoolList();
    void signUpUser(UserItem userItem, UserInfo userInfo);
}
