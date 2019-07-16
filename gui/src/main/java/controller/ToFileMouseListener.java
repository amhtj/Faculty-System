package controller;

import model.TeacherPageModel;
import view.MainFrame;
import view.TeacherFileView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToFileMouseListener implements MouseListener {

    private TeacherPageModel model;

    public ToFileMouseListener(TeacherPageModel model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TeacherFileView view = new TeacherFileView(model.getId());
        MainFrame.replacePanel(view.getJPanel());
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
