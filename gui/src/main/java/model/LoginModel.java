package model;

import model.rest.model.UserModel;
import model.rest.model.UserResponse;
import model.rest.service.Service;
import retrofit2.Call;


public class LoginModel {

    public Call<UserResponse> isValid(String username, String password) {
        UserModel user = new UserModel();
        user.setLogin(username);
        user.setPassword(password);

        return Service.getInstance().getUserApi().postUser(user);
    }

    public Call<UserResponse> isValidRegister(String userName, String password) {
        UserModel user = new UserModel();
        user.setLogin(userName);
        user.setPassword(password);

        return Service.getInstance().getUserApi().registerUser(user);
    }
}
