package hcmue.congvu.drlstudent.Controller.UserInfoController;

import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;

/**
 * Created by CongVu on 31/08/2018.
 */
public interface ControllerImpProcessUserInfo {
    void getDataUserInfo(int idUser);
    void getSchoolList();
    void updateUserInfo(UserInfo userInfo);
    void checkCurrentPassword(int userId, String currentPassword);
    void updatePassword(int userId, String newPassword);
}
