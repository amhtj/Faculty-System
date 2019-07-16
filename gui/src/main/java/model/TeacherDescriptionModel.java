package model;
import model.rest.service.Service;
import retrofit2.Call;

public class TeacherDescriptionModel {

    private int id;

    public TeacherDescriptionModel(int id) {
        this.id = id;
    }

    public Call<TeacherModel> getTeacher() {
        return Service.getInstance().getTeacherApi().getTeacher(id);
    }
}
