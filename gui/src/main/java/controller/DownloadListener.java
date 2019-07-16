package controller;

import model.TeacherFileModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Загружаем файл с сервера
 */

public class DownloadListener implements MouseListener {

    private TeacherFileModel model;
    private String path;
    private String name;

    public DownloadListener(TeacherFileModel model, String path, String name) {
        this.model = model;
        this.path = path;
        this.name = name;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Choose directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        final String s = path.replaceAll("\\\\", "/");
        final int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            model.downloadFile(s).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.body() != null) {
                        try {
                            File file = new File(fileChooser.getSelectedFile() + "/" + name);
                            file.getParentFile().mkdirs();
                            file.createNewFile();
                            FileOutputStream outputStream =
                                    new FileOutputStream(file);
                            final byte[] bytes = response.body().bytes();
                            outputStream.write(bytes);
                            outputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
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
