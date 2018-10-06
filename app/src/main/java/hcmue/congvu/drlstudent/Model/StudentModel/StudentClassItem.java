package hcmue.congvu.drlstudent.Model.StudentModel;

/**
 * Created by CongVu on 02/10/2018.
 */
public class StudentClassItem {
    int typeStudentClass;
    String studentCode;

    public StudentClassItem() {
    }

    public StudentClassItem(int typeStudentClass, String studentCode) {
        this.typeStudentClass = typeStudentClass;
        this.studentCode = studentCode;
    }

    public int getTypeStudentClass() {
        return typeStudentClass;
    }

    public void setTypeStudentClass(int typeStudentClass) {
        this.typeStudentClass = typeStudentClass;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}
