package model;

import model.rest.model.FileGetPayload;
import model.rest.service.Service;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

import java.util.List;

public class TeacherFileModel {

    private int teacherId;
    private List<FileGetPayload> listFiles;

    public TeacherFileModel(int teacherId) {
        this.teacherId = teacherId;
    }

    public Call<List<FileGetPayload>> getFiles() {
        return Service.getInstance().getFileApi().getFiles(teacherId);
    }

    public Call<FileGetPayload> uploadFile(String name, int teacherId, RequestBody body) {
        return Service.getInstance().getFileApi().uploadFile(teacherId, name, body);
    }

    public Call<ResponseBody> downloadFile(String path) {
        return Service.getInstance().getFileApi().downloadFile(path);
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setListFiles(List<FileGetPayload> listFiles) {
        this.listFiles = listFiles;
    }

    public List<FileGetPayload> getListFiles() {
        return listFiles;
    }
}
