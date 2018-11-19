package hcmue.congvu.drlstudent.Controller.CurrentClassDetailController;

/**
 * Created by CongVu on 24/09/2018.
 */
public interface ControllerImpProcessCurrentClassDetail {
    void getCurrentClassDetailList(int idUser, int idCurrentClassDetail);
    void createCurrentClassDetail(int idUser, int idClass, int yearStart, int yearEnd, int yearTerm);
    void deleteCurrentClassDetail(int idClassDetail);
    void checkUsername(int idClass, String username);
    void addNewClassMember(int idClass, int idUser, int idUserAdd, int typeStudent);
}
