package controller;

import view.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Обрабатывам кнопку нажатия назад
 */

public class TeacherPreviousMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.previousPanel();
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
