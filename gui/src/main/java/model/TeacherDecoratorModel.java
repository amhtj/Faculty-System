package model;

import model.rest.service.Service;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class TeacherDecoratorModel {
    private int id;

    public TeacherDecoratorModel(int id) {
        this.id = id;
    }

    public Call<ResponseBody> getImage() {
        return Service.getInstance().getTeacherApi().fetchPortrait(id); // Получение фото для конкретного преподавателя
    }

    public int getId() {return id;}
}
