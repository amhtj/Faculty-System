package model.rest.service;

import model.TeacherModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import javax.xml.ws.Action;
import java.util.*;

/**
 * Описание апи с преподавателями
 */
public interface TeacherApi {

    @GET("/teachers/all")
    Call<List<TeacherModel>> getAllTeachers(); // Получение преподавателей

    @GET("/teachers/")
    Call<TeacherModel> getTeacher(@Query(value = "id", encoded = true) int id); // Получение конкретного преподавателя

    @GET("/teachers/image/")
    Call<ResponseBody> fetchPortrait(@Query(value = "id", encoded = true) int id); // Получение портрета

    @POST("/teachers")
    Call<TeacherModel> createTeacher(@Body TeacherModel teacherModel); // Создание преподавателя

    @PUT("/teachers")
    Call<TeacherModel> updateTeacher(@Body TeacherModel teacherModel); // Обновление преподавателя

    @DELETE("/teachers?id={id}")
    Call<String> deleteTeacher(@Path("id") String id); // Удаление преподавателя
}
