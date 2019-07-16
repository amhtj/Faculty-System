package model;

import model.rest.service.Service;
import retrofit2.Call;

import java.util.List;

public class DisciplineListModel {

    private int id;

    public DisciplineListModel(int id) {
        this.id = id;
    }

    public Call<List<SubjectModel>> getSubjects() {
        return Service.getInstance().getSubjectApi().getSubjects(id);
    }


}
