package hcmue.congvu.drlstudent.Controller.ActivityClassDetailController;

/**
 * Created by CongVu on 09/10/2018.
 */
public interface ControllerImpProcessActivityClassDetail {
    void getDataListClassDetail(int idClass, int idClassDetail);
    void getActivityGroupList();
    void getActivityLevelList();
    void createAcitvityClass(int idUser, int idClassDetail, int idActivityGroup, int idActivityLevel, String dateTimeStart, String dateTimeEnd, String content, String scores);
}
