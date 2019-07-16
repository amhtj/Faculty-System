package controller;

import model.LoginModel;
import model.rest.model.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.LoginWindowView;
import view.MainFrame;
import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Слушатель клика на кнопке логина
 */

public class LoginMouseListener implements MouseListener {

    private LoginModel model;
    private LoginWindowView view;

    public LoginMouseListener(LoginModel model, LoginWindowView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Когда происходит клик, делаем запрос к модели, спрашивая, есть
     * ли юзер с таким-то логином и паролем
     *
     * @param e
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        String login = view.getLoginField().getText();
        String password = view.getPasswordField().getText();

        if (view.isRegisterView()) {
            model.isValidRegister(login, password).enqueue(new LoginCallback());
        } else {
            model.isValid(login, password).enqueue(new LoginCallback());
        }
    }

    private class LoginCallback implements Callback<UserResponse> {

        @Override
        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
            if (response.body() != null && response.body().isVerified()) {
                view.setVisible(false);
                final UserResponse body = response.body();
                MainFrame.userId = body.getId();
                MainFrame.isTeacher = body.isTeacher();
                MainFrame.getInstance();
            } else {
                if (view.isRegisterView()) {
                    JOptionPane.showMessageDialog(null, "We have such user, please choose another");
                } else {
                    JOptionPane.showMessageDialog(null, "Dear user, you entered wrong password");
                }
            }
        }

        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {
            t.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ooop, something went wrong...");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
