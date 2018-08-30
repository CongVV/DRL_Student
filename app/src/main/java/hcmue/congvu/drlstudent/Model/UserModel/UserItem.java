package hcmue.congvu.drlstudent.Model.UserModel;

/**
 * Created by CongVu on 29/08/2018.
 */
public class UserItem {
    int id;
    int idTypeUser;
    String username;
    String password;

    public UserItem(int id, int idTypeUser, String username, String password) {
        this.id = id;
        this.idTypeUser = idTypeUser;
        this.username = username;
        this.password = password;
    }

    public UserItem() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTypeUser(int idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public int getIdTypeUser() {
        return idTypeUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
