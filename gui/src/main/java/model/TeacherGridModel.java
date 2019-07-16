package model;

import model.rest.service.Service;
import retrofit2.Call;
import java.util.List;

public class TeacherGridModel {

    public Call<List<TeacherModel>> getTeachers() { // Получение преподавателей
       return Service.getInstance().getTeacherApi().getAllTeachers();
    }


}
