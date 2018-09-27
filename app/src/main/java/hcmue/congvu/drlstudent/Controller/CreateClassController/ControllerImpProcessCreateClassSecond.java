package hcmue.congvu.drlstudent.Controller.CreateClassController;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.StudentModel.StudentItem;

/**
 * Created by CongVu on 25/09/2018.
 */
public interface ControllerImpProcessCreateClassSecond {
    void checkUsername(String username);
    void saveCreateClass(ArrayList<StudentItem> studentItemArrayList, String nameClass, int idUser, int idSchool);
}
