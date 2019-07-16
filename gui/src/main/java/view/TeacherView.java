package view;
import controller.TeacherController;
import controller.TeacherMouseController;
import model.TeacherDecoratorModel;
import javax.swing.*;
import java.awt.*;

public class TeacherView {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;

    private JPanel jpanel;
    private String name;
    private TeacherDecoratorModel model;
    private TeacherController controller;

    private JLabel portrait = new JLabel();

    public TeacherView(int id, String name) {
        this.model = new TeacherDecoratorModel(id);
        this.name = name;
        this.controller = new TeacherController(model, this);
    }

    public void initPortrait(Image image) {
       ImageIcon imageIcon = new ImageIcon(image);
        portrait.setIcon(imageIcon);
        portrait.revalidate(); // Применяем изменения фотографий на GUI
        portrait.addMouseListener(new TeacherMouseController(model));
    }

    public void init() {
        jpanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(boxLayout);
        JLabel imageLabel = new JLabel(name);
        jpanel.add(portrait);
        jpanel.add(imageLabel);

    }

    public JPanel getJpanel() {return jpanel;}

}
