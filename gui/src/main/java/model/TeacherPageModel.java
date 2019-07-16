package model;

import model.rest.service.Service;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class TeacherPageModel {

    private int id;

    public TeacherPageModel(int id) {
        this.id = id;
    }

    public Call<ResponseBody> getImage() {
        return Service.getInstance().getTeacherApi().fetchPortrait(id);
    }

    public int getId() {
        return id;
    }
}
