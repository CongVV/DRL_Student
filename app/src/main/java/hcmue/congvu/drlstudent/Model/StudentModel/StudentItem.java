package hcmue.congvu.drlstudent.Model.StudentModel;

/**
 * Created by CongVu on 25/09/2018.
 */
public class StudentItem {
    String username;
    int idUser;
    int typeStudent;

    public StudentItem() {
    }

    public StudentItem(String username, int idUser, int typeStudent) {
        this.username = username;
        this.idUser = idUser;
        this.typeStudent = typeStudent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTypeStudent() {
        return typeStudent;
    }

    public void setTypeStudent(int typeStudent) {
        this.typeStudent = typeStudent;
    }
}
