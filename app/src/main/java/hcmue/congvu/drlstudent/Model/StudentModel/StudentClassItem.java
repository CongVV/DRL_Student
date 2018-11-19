package hcmue.congvu.drlstudent.Model.StudentModel;

/**
 * Created by CongVu on 02/10/2018.
 */
public class StudentClassItem {
    int typeStudentClass, idUser, idClass;
    String userNameStudent;
    String fullNameStudent;

    public StudentClassItem() {
    }

    public StudentClassItem(int idClass, int idUser, int typeStudentClass, String userNameStudent, String fullNameStudent) {
        this.idClass = idClass;
        this.idUser = idUser;
        this.typeStudentClass = typeStudentClass;
        this.userNameStudent = userNameStudent;
        this.fullNameStudent = fullNameStudent;
    }

    public int getTypeStudentClass() {
        return typeStudentClass;
    }

    public void setTypeStudentClass(int typeStudentClass) {
        this.typeStudentClass = typeStudentClass;
    }

    public String getUserNameStudent() {
        return userNameStudent;
    }

    public void setUserNameStudent(String userNameStudent) {
        this.userNameStudent = userNameStudent;
    }

    public String getFullNameStudent() {
        return fullNameStudent;
    }

    public void setFullNameStudent(String fullNameStudent) {
        this.fullNameStudent = fullNameStudent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }
}
