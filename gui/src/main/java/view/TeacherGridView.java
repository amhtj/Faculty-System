package view;

import controller.TeacherGridController;
import model.TeacherGridModel;
import model.TeacherModel;

import javax.swing.*;

import java.awt.*;
import java.util.List;

/**
 * Сеька с преподователями
 */

public class TeacherGridView {

    private JPanel panel;
    private JPanel teachGrid;
    private TeacherGridModel model;
    private TeacherGridController controller;

    public TeacherGridView() {
        model = new TeacherGridModel();
        controller = new TeacherGridController(model, this);
    }

    public void feelGrid(List<TeacherModel> teachers) {
        teachGrid.setLayout(new GridLayout(teachers.size() / 4 + 1, 4));
        teachers.forEach(it -> {
            TeacherView teacher = new TeacherView(it.getId(), it.getName());

            teachGrid.add(teacher.getJpanel());
        });
        teachGrid.revalidate(); // Принять изменения, сделанные преподавателем
    }

    private JPanel initDescription() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Font myLovelyFont = new Font("Helvetica", Font.BOLD, 17);
        JLabel department = new JLabel("Department of Automation & Computer Engineering NSTU");
        department.setFont(myLovelyFont);
        department.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font font = new Font("Helvetica", Font.PLAIN, 12);
        JLabel location = new JLabel("Location: 630087, Novosibirsk, Nem-Danchenko Street 136, 7-316");
        location.setAlignmentX(Component.CENTER_ALIGNMENT);
        location.setFont(font);

        JLabel number = new JLabel("Number: 8 (383) 346 04 92");
        number.setAlignmentX(Component.CENTER_ALIGNMENT);
        number.setFont(font);

        JLabel mail = new JLabel("Email: kafedra_vt@vt.cs.nstu.ru");
        mail.setAlignmentX(Component.CENTER_ALIGNMENT);
        mail.setFont(font);

        panel.add(department);
        panel.add(location);
        panel.add(number);
        panel.add(mail);
        return panel;
    }

    public void initGui() {
        teachGrid = new JPanel();
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        final JPanel description = initDescription();
        panel.add(description);
        panel.add(Box.createRigidArea(new Dimension(20,20)));
        panel.add(teachGrid);
    }

    public JPanel getPanel() {
        return panel;
    }
}
