package model.rest.service;

import model.rest.model.UserModel;
import model.rest.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Описание апи с пользователями
 */
public interface UserApi {

    final static String API_VERIFIED = "/login/verified";

    String API_REGISTER_VERIFED = "/register/verified";

    @POST(API_VERIFIED)
    public Call<UserResponse> postUser(@Body UserModel user);

    @POST(API_REGISTER_VERIFED)
    public Call<UserResponse> registerUser(@Body UserModel user);
}
