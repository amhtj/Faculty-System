package controller;

import model.TeacherDecoratorModel;
import view.MainFrame;
import view.TeacherPageView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Обработка клика на фотографию преподавателя.
 */

public class TeacherMouseController implements MouseListener {
    private TeacherDecoratorModel model;

    public TeacherMouseController(TeacherDecoratorModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TeacherPageView view = new TeacherPageView(model.getId());
        MainFrame.replacePanel(view.getPanel());
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
