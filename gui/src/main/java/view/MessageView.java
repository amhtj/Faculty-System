package view;

import controller.MessageController;
import controller.SendMessageMouseListener;
import controller.TeacherPreviousMouseListener;
import model.MessageModel;
import model.rest.model.Messages;

import javax.swing.*;

import java.awt.*;
import java.util.List;

/**
 * окно с сообщениями
 */
public class MessageView {

    private MessageModel model;
    private MessageController controller;
    private JPanel panel;
    private TextArea textArea;
    private TextField messageField;


    public MessageView(int teacherId) {
        this.model = new MessageModel(teacherId);
        this.controller = new MessageController(this, model);
    }

    public void init() {
        panel = new JPanel();
        BoxLayout verticalLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(verticalLayout);
        textArea = new TextArea();
        panel.add(textArea);

        final JPanel bottom = initBottomPanel();
        panel.add(bottom);
    }

    private JPanel initBottomPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(MainFrame.WIDTH, 200));
        panel.setMaximumSize(new Dimension(MainFrame.WIDTH, 300));
        BoxLayout horizontalLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        panel.setLayout(horizontalLayout);

        JButton backButton = new JButton("Назад");
        backButton.addMouseListener(new TeacherPreviousMouseListener());
        panel.add(backButton);

        messageField = new TextField();
        panel.add(messageField);

        JButton button = new JButton();
        button.addMouseListener(new SendMessageMouseListener(model, this));
        panel.add(button);
        return panel;
    }

    /**
     * Инцииализируем сообщения по приходу
     *
     * @param messages - сообщения пришедшие с сервера
     */
    public void initMessages(List<Messages> messages) {
        StringBuilder sb = new StringBuilder();
        messages.forEach(it -> {
            sb.append(formatMessage(it.getUserName(), it.getText(), it.getDate())).append("\n");
        });
        textArea.setText(sb.toString());
        textArea.revalidate();
    }

    public JPanel getPanel() {
        return panel;
    }

    private String formatMessage(String name, String text, String date) {
        return String.format("Name of user: %s         MESSAGE: %s        Date: %s", name, text, date);
    }

    public TextField getMessageField() {
        return messageField;
    }
}
