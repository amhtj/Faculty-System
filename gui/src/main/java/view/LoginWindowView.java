package view;

import controller.LoginChangeRegisterListener;
import controller.LoginMouseListener;
import model.LoginModel;

import javax.swing.*;

import java.awt.*;

/**
 * Окно с логином
 */
public class LoginWindowView {

    private final static int WIDTH = 300;
    private final static int HEIGHT = 140;
    private JFrame frame;
    private LoginModel model;
    private TextField loginField;
    private TextField passwordField;
    private boolean isRegisterView = false;

    public LoginWindowView() {
        model = new LoginModel();
        initGui();
    }

    private void initGui() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        ImageIcon icon =
                new ImageIcon("C:\\Users\\Мама\\IdeaProjects\\coursework\\gui\\src\\main\\resources\\nstu.png");
        frame.setIconImage(icon.getImage());
        JPanel panel = initPanel();
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private JPanel initLogin() {
        JPanel login = new JPanel();
        BoxLayout loginLayout = new BoxLayout(login, BoxLayout.X_AXIS);
        loginField = new TextField("Please, enter your login");
        JLabel loginLabel = new JLabel("Login: ");
        login.setLayout(loginLayout);

        login.add(loginLabel);
        login.add(loginField);
        loginField.setMaximumSize(new Dimension(150, 20));
        return login;
    }

    private JPanel initPassword() {
        JPanel password = new JPanel();
        BoxLayout passwordLayout = new BoxLayout(password, BoxLayout.X_AXIS);
        passwordField = new TextField("Please, enter your password");
        JLabel passwordLabel = new JLabel("Password: ");
        password.setLayout(passwordLayout);
        password.add(passwordLabel);
        password.add(passwordField);
        passwordField.setMaximumSize(new Dimension(150, 20));
        return password;
    }

    private JPanel initButton() {
        JPanel button = new JPanel();
        BoxLayout okLayout = new BoxLayout(button, BoxLayout.X_AXIS);
        JButton ok = new JButton("OK");
        ok.setMaximumSize(new Dimension(60, 20));
        button.setLayout(okLayout);
        ok.addMouseListener(new LoginMouseListener(model, this));
        button.add(ok);
        return button;
    }

    private JPanel initPanel() {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);

        JPanel login = initLogin();
        JPanel password = initPassword();
        JPanel button = initButton();
        JPanel registerLabel = addRegisterLabel();

        panel.add(login);
        panel.add(password);
        panel.add(button);
        panel.add(registerLabel);
        return panel;
    }

    private JPanel addRegisterLabel() {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);

        JLabel label = new JLabel("Don't already have account, Register");
        label.addMouseListener(new LoginChangeRegisterListener(model, this));
        panel.add(label);

        return panel;
    }

    public TextField getLoginField() {
        return loginField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public void setVisible(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public boolean isRegisterView() {
        return isRegisterView;
    }

    public void setRegisterView(boolean registerView) {
        isRegisterView = registerView;
        if (isRegisterView) {
            frame.setTitle("Register");
        } else {
            frame.setTitle("Login");
        }
        frame.revalidate();
    }
}
