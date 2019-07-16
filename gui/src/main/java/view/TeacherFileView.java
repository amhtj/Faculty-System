package view;

import controller.*;
import model.TeacherFileModel;
import model.rest.model.FileGetPayload;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class TeacherFileView {

    private JPanel panel;
    private TeacherFileModel model;
    private TeacherFileController controller;

    public TeacherFileView(int teacherId) {
        model = new TeacherFileModel(teacherId);
        controller = new TeacherFileController(this, model);
    }

    public void initFiles(List<FileGetPayload> files) {
        panel.removeAll();
        files.forEach(it -> {
            JLabel label = new JLabel(it.getName());
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.addMouseListener(new DownloadListener(model, it.getPath(), it.getName()));
            panel.add(label);
        });
        if (MainFrame.isTeacher) {
            panel.add(initButton());
        }
        panel.add(initBackButton());
        panel.revalidate();
    }

    public void initGui() {
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
    }

    private JPanel initButton() {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        JButton button = new JButton("Load files");
        button.addMouseListener(new UpLoadButton(model, this));
        button.setVisible(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(button);
        return panel;
    }

    private JPanel initBackButton() {
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(layout);

        JButton button = new JButton("Back");
        button.addMouseListener(new TeacherPreviousMouseListener());
        button.setVisible(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(button);
        return panel;
    }

    public JPanel getJPanel() {
        return panel;
    }
}
