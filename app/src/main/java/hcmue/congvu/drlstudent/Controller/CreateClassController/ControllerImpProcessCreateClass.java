package hcmue.congvu.drlstudent.Controller.CreateClassController;

/**
 * Created by CongVu on 11/09/2018.
 */
public interface ControllerImpProcessCreateClass {
    void createClass(String className, int idUser, int idSchool);
    void getSchoolList();
}
