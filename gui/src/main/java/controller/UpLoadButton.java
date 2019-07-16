package controller;

import model.TeacherFileModel;
import model.rest.model.FileGetPayload;
import model.rest.model.FilePostPayload;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherFileView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Загружаем файл на сервер
 */

public class UpLoadButton implements MouseListener {

    private TeacherFileModel model;
    private TeacherFileView view;


    public UpLoadButton(TeacherFileModel model, TeacherFileView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            FilePostPayload filePostPayload = new FilePostPayload();
            final String name = selectedFile.getAbsolutePath().substring(selectedFile.
                    getAbsolutePath().lastIndexOf("\\") + 1);
            filePostPayload.setName(name);
            byte[] bytes = readFile(selectedFile);

            RequestBody body = new RequestBody() {
                @Override
                public MediaType contentType() {
                    return MediaType.parse(name.substring(name.lastIndexOf(".") + 1));
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    sink.write(bytes);
                }
            };

            model.uploadFile(name, model.getTeacherId(), body).enqueue(new Callback<FileGetPayload>() {
                @Override
                public void onResponse(Call<FileGetPayload> call, Response<FileGetPayload> response) {
                    if (response.body() != null) {
                        model.getListFiles().add(response.body());
                        view.initFiles(model.getListFiles());
                    }
                }

                @Override
                public void onFailure(Call<FileGetPayload> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private byte[] readFile(File selectedFile) {
        FileInputStream reader;
        byte[] array = null;
        try {
            reader = new FileInputStream(selectedFile);
            array = new byte[reader.available()];
            reader.read(array);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
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
