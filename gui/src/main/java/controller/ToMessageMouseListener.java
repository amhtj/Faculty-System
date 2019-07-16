package controller;

import view.MainFrame;
import view.MessageView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Обработка нажатия на кнопку сообщения
 */

public class ToMessageMouseListener implements MouseListener {

    private int teacherId;

    public ToMessageMouseListener(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MessageView messagePanel = new MessageView(teacherId);
        MainFrame.replacePanel(messagePanel.getPanel());
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
