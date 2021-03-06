package hcmue.congvu.drlstudent.View.ActivityClassDetailView;

import org.json.JSONArray;

/**
 * Created by CongVu on 06/10/2018.
 */
public interface ViewProcessActivityClassDetail {
    void setActivityGroupList(JSONArray jsonArray);
    void setActivityLevelList(JSONArray jsonArray);
    void resultCreateActivityClass(Boolean result);
    void setActivityClass(JSONArray jsonArray);
    void setActivityClassList(JSONArray jsonArray);
    void setActivityStudentInfo(JSONArray jsonArray);
    void setActivityManagement(JSONArray jsonArray);
    void resultConfirmActivity(boolean result);
    void resultDeleteActivityClass(boolean result);
    void resultAcceptActivityClass(boolean result);
    void resultCancelActivityClass(boolean result);
    void resultUpdateActivityClass(boolean result);
}
