package model.rest.model;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    private int id;
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("isTeacher")
    private boolean isTeacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }
}
