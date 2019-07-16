package model.rest.service;

import model.rest.model.Messages;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Описание апи сообщений
 */
public interface MessageApi {

    @GET("/messages/all")
    Call<List<Messages>> getMessages(@Query(value = "userId", encoded = true) int userId,
                                     @Query(value = "teacherId", encoded = true) int teacherId);

    @POST("/messages/")
    Call<Messages> createMessage(@Body Messages body);
}
