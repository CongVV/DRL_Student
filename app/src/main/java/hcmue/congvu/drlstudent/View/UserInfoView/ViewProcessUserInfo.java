package hcmue.congvu.drlstudent.View.UserInfoView;

import org.json.JSONArray;

import hcmue.congvu.drlstudent.Model.UserModel.UserInfo;

/**
 * Created by CongVu on 31/08/2018.
 */
public interface ViewProcessUserInfo {
    void setDataUserInfo(UserInfo userInfo);
    void setSpinnerSchool(JSONArray listSchool);
    void updateFail();
    void updateSuccessful();
    void resultCheckPassword(String result);
    void updatePassword(String result);
    void resultGetAvatar(String response);
}
