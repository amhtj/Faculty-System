package controller;
import model.TeacherDescriptionModel;
import model.TeacherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherDescriptionView;

/**
 * Контроллер для описания преподователя
 */

public class TeacherDescriptionController {

    private TeacherDescriptionModel model;
    private TeacherDescriptionView view;

    public TeacherDescriptionController(TeacherDescriptionModel model, TeacherDescriptionView view) {
        this.model = model;
        this.view = view;
        init();
    }

    /**
     * Отображаем основную часть гуи
     * делаем запрос к серверу для поулчения информации о препод-е, в случае успепха, отображаем остальную информацию
     */

    public void init() {
        view.initGui();
        model.getTeacher().enqueue(new Callback<TeacherModel>() {
            @Override
            public void onResponse(Call<TeacherModel> call, Response<TeacherModel> response) {
                if (response.body() != null) {
                    view.initData(response.body());
                }
            }

            @Override
            public void onFailure(Call<TeacherModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
