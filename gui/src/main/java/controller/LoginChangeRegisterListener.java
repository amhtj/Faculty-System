package controller;
import model.LoginModel;
import view.LoginWindowView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginChangeRegisterListener implements MouseListener {

    private LoginModel model;
    private LoginWindowView view;

    public LoginChangeRegisterListener(LoginModel model, LoginWindowView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        view.setRegisterView(!view.isRegisterView());
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
