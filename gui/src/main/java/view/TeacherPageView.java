package view;

import controller.*;
import model.TeacherPageModel;

import javax.swing.*;

import java.awt.*;

/**
 * Страница с профилем преподователя
 */
public class TeacherPageView {

    private JPanel panel;
    private TeacherPageModel model;
    private TeacherPageController controller;
    private JLabel portrait = new JLabel();

    public TeacherPageView(int id) {
        this.model = new TeacherPageModel(id);
        this.controller = new TeacherPageController(model, this);
    }

    public void initPortrait(Image image) {
        portrait.setIcon(new ImageIcon(image));
        portrait.revalidate();
    }

    public void initGui() {
        panel = new JPanel();
        panel.setMinimumSize(new Dimension(MainFrame.WIDTH, 600));
        panel.setMaximumSize(new Dimension(MainFrame.WIDTH, 600));
        BoxLayout verticalLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(verticalLayout);

        JPanel upPanel = new JPanel();
        upPanel.setMinimumSize(new Dimension(MainFrame.WIDTH, 400));
        upPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        upPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        BoxLayout horizontalLayout = new BoxLayout(upPanel, BoxLayout.LINE_AXIS);
        upPanel.setLayout(horizontalLayout);

        portrait = new JLabel();
        portrait.setAlignmentX(Component.LEFT_ALIGNMENT);

        TeacherDescriptionView description = new TeacherDescriptionView(model.getId());
        upPanel.add(portrait);
        upPanel.add(Box.createRigidArea(new Dimension(46, 5)));
        JPanel panel = description.getPanel();
        upPanel.add(panel);

        JPanel downPanel = initDownPanel();

        this.panel.add(upPanel);
        this.panel.add(downPanel);

        JPanel watchFiles = new JPanel();
        JButton filesButton = new JButton("Show files");
        filesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        watchFiles.add(filesButton);
        final ToFileMouseListener listener = new ToFileMouseListener(model);
        filesButton.addMouseListener(listener);
        this.panel.add(watchFiles);

        JPanel sendPanel = new JPanel();
        JButton messageButton = new JButton("Send message");
        messageButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        sendPanel.add(messageButton);
        messageButton.addMouseListener(new ToMessageMouseListener(model.getId()));
        this.panel.add(sendPanel);

        JPanel buttonPanel = initBackButton();
        this.panel.add(buttonPanel);
    }

    /**
     * Инициализируем нижнию панель с дисциплинами
     *
     * @return
     */
    private JPanel initDownPanel() {
        JPanel downPanel = new JPanel();
        downPanel.setMinimumSize(new Dimension(MainFrame.WIDTH, 200));
        downPanel.setMaximumSize(new Dimension(MainFrame.WIDTH, 200));
        downPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        downPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        downPanel.add(new DisciplinesListView(model.getId()).getJpanel());
        return downPanel;
    }

    /**
     * Инициализируем кнопку назад
     *
     * @return
     */
    private JPanel initBackButton() {
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Back");
        button.setAlignmentX(Component.RIGHT_ALIGNMENT);
        buttonPanel.add(button);
        button.setVisible(true);
        button.addMouseListener(new TeacherPreviousMouseListener());
        buttonPanel.setVisible(true);
        return buttonPanel;
    }

    public JPanel getPanel() {
        return panel;
    }
}
