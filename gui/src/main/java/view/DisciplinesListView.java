package view;

import controller.DisciplineListController;
import model.DisciplineListModel;
import model.SubjectModel;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class DisciplinesListView {

    private JPanel jpanel;
    private DisciplineListModel model;
    private DisciplineListController controller;


    public DisciplinesListView(int teacherId) {
        this.model = new DisciplineListModel(teacherId);
        this.controller = new DisciplineListController(model, this);
    }

    private JPanel initTitle() {
        Font font = new Font(null, Font.ITALIC, 18);
        JPanel panelTitle = new JPanel();
        BoxLayout layoutTitle = new BoxLayout(panelTitle, BoxLayout.LINE_AXIS);
        panelTitle.setLayout(layoutTitle);
        JLabel label = new JLabel("Subjects & Groups");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(font);
        panelTitle.add(label);
        return panelTitle;
    }

    /**
     * Инициализируем пришедшие дисциплины в гуи
     *
     * @param subjectModels
     */
    public void initSubjects(List<SubjectModel> subjectModels) {
        JPanel panelTitle = initTitle();
        jpanel.add(panelTitle);

        jpanel.add(Box.createRigidArea(new Dimension(40, 20))); // делаем отступ
        subjectModels.forEach(it -> {
            JPanel panel = new JPanel();
            panel.setMinimumSize(new Dimension(MainFrame.WIDTH, 100));
            BoxLayout layout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
            panel.setLayout(layout);

            JLabel subject = new JLabel(it.getName());
            Font myLovelyFont = new Font("Helvetica", Font.PLAIN, 12);
            subject.setFont(myLovelyFont);

            panel.add(subject);
            panel.add(Box.createRigidArea(new Dimension(100, 20)));
            panel.add(Box.createHorizontalGlue());

            JLabel groups = new JLabel(it.getGroups());
            subject.setFont(myLovelyFont);
            panel.add(groups);

            jpanel.add(panel);
        });
        jpanel.revalidate();
    }

    /**
     * Инициализируем основную часть гуи
     */

    public void initGui() {
        jpanel = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(jpanel, BoxLayout.Y_AXIS);
        jpanel.setLayout(verticalLayout);
    }

    public JPanel getJpanel() {
        return jpanel;
    }
}
