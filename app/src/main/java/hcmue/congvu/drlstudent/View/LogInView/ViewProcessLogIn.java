package hcmue.congvu.drlstudent.View.LogInView;

import hcmue.congvu.drlstudent.Model.UserModel.UserItem;

/**
 * Created by CongVu on 22/08/2018.
 */
public interface ViewProcessLogIn {
    void logInSuccessfull(int userId, String avatar);
    void logInFail();
}
