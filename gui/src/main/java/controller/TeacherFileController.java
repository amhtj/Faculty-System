package controller;

import model.TeacherFileModel;
import model.rest.model.FileGetPayload;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherFileView;

import java.util.*;

public class TeacherFileController {

    private TeacherFileView view;
    private TeacherFileModel model;

    public TeacherFileController(TeacherFileView view, TeacherFileModel model) {
        this.view = view;
        this.model = model;
        init();
    }

    public void init() {
        view.initGui();
        model.getFiles().enqueue(new Callback<List<FileGetPayload>>() {
            @Override
            public void onResponse(Call<List<FileGetPayload>> call, Response<List<FileGetPayload>> response) {
                if (response.body() != null) {
                    model.setListFiles(response.body());
                    view.initFiles(response.body());
                } else {
                    view.initFiles(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<FileGetPayload>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
