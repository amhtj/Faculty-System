package view;

import controller.TeacherDescriptionController;
import model.TeacherDescriptionModel;
import model.TeacherModel;

import javax.swing.*;
import java.awt.*;

/**
 * Описание преподователя
 */
public class TeacherDescriptionView {
    private JPanel panel;

    private final static String DEGREE = "Degree";
    private final static String LOCATION = "Location";
    private final static String MAIL = "Email";
    private final static String PHONE_NUMBER = "Number";
    private final static String DATE_VISITING = "Visit date";

    private TeacherDescriptionModel model;
    private TeacherDescriptionController controller;

    public TeacherDescriptionView(int id) {
        this.model = new TeacherDescriptionModel(id);
        this.controller = new TeacherDescriptionController(model, this);
    }

    private JLabel initName(String name){
        JLabel nameLabel = new JLabel(name);
        Font font = new Font(null, Font.ITALIC, 18);
        nameLabel.setFont(font);
        return nameLabel;
    }

    /**
     * Инициализируем информацию о преподователе сразу как она пришла
     * @param model - модель информации о преподователе
     */
    public void initData(TeacherModel model) {
        panel.add(initName(model.getName()));
        panel.add(Box.createRigidArea(new Dimension(20,40)));
        panel.add(getLabel(DEGREE, model.getDegree()));
        panel.add(getLabel(LOCATION, model.getLocation()));
        panel.add(getLabel(MAIL, model.getMail()));
        panel.add(getLabel(PHONE_NUMBER, model.getNumber()));
        panel.add(getLabel(DATE_VISITING, model.getVisitDate().toString()));
        panel.revalidate();
    }

    public void initGui() {
        panel = new JPanel();
        BoxLayout descriptionPane = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(descriptionPane);
    }

    private JLabel getLabel(String name, String value) {
        Font myLovelyFont = new Font("Helvetica", Font.PLAIN, 12);
        JLabel label = new JLabel(String.format("%s:  %s", name, value));
        label.setFont(myLovelyFont);
        return label;
    }

    public JPanel getPanel() {
        return panel;
    }
}
