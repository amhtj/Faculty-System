package model.rest.service;

import model.rest.model.FileGetPayload;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.*;

public interface FileApi {

    @GET("/file/")
    Call<List<FileGetPayload>> getFiles(@Query(value = "teacherId", encoded = true) int teacherId);

    @POST("/file/")
    Call<FileGetPayload> uploadFile(@Query(value = "teacherId", encoded = true) int teacherId,
                                    @Query(value = "name", encoded = true) String name, @Body RequestBody body);

    @GET("/file/download")
    Call<ResponseBody> downloadFile(@Query(value="path", encoded = true) String path);
}
