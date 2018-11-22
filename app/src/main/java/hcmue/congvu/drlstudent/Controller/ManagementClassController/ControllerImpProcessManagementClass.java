package hcmue.congvu.drlstudent.Controller.ManagementClassController;

import java.util.ArrayList;

import hcmue.congvu.drlstudent.Model.StudentModel.StudentClassItem;

/**
 * Created by CongVu on 18/11/2018.
 */
public interface ControllerImpProcessManagementClass {
    void getClassList(int idClass);
    void getClassListTypeStudent(int idClass);
    void deleteStudentClass(int idClass, int idUser, int numberStudent);
    void updateTypeStudentClass(int idClass, ArrayList<StudentClassItem> arrayList);
}
