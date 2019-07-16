package controller;
import model.TeacherGridModel;
import model.TeacherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherGridView;
import java.util.List;

/**
 * Контроллер для сетки с преподавателями
 */

public class TeacherGridController {

    private TeacherGridModel model;
    private TeacherGridView view;

    public TeacherGridController(TeacherGridModel model, TeacherGridView view) {
        this.model = model;
        this.view = view;
        init();
    }

    /**
     * Иниуциализируем основную часть гуи и делаем запрос к серверу
     * По получению ответа, прогружаем преподавателей.
     */

    public void init() { // Чтение преподавателей
        view.initGui();
        model.getTeachers().enqueue(new Callback<List<TeacherModel>>() {
            @Override
            public void onResponse(Call<List<TeacherModel>> call, Response<List<TeacherModel>> response) {
                if (response.body() != null) {
                    view.feelGrid(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TeacherModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
