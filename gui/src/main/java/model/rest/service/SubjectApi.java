package model.rest.service;

import model.SubjectModel;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.*;

/**
 * Описание апи с предметами
 */
public interface SubjectApi {

    @GET("/subjects/all/")
    Call<List<SubjectModel>> getSubjects(@Query(value = "id", encoded = true) int id);

    @POST("/subjects")
    Call<SubjectModel> createSubject(@Body SubjectModel subjectModel);

    @PUT("/subjects")
    Call<SubjectModel> updateSubject(@Body SubjectModel subjectModel);

    @DELETE("/subjects/{id}")
    Call<String> deletSubject(@Path("id") String id);
}
