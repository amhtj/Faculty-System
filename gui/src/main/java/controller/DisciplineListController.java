package controller;

import model.DisciplineListModel;
import model.SubjectModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.DisciplinesListView;

import java.util.List;

/**
 * Контроллер для списка дисциплин
 */

public class DisciplineListController {

    private DisciplineListModel model;
    private DisciplinesListView view;

    public DisciplineListController(DisciplineListModel model, DisciplinesListView view) {
        this.model = model;
        this.view = view;
        init();
    }

    /**
     * Инициализируем гуи, делаем запрос на количество предметов, в случае успеха
     * прогружаем дисциплины на экране
     */


    public void init() {
        view.initGui();
        model.getSubjects().enqueue(new Callback<List<SubjectModel>>() {
            @Override
            public void onResponse(Call<List<SubjectModel>> call, Response<List<SubjectModel>> response) {
                if (response.body() != null) {
                    view.initSubjects(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SubjectModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
