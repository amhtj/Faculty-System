package controller;
import model.TeacherDecoratorModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Контроллер для фотографии с учителем на сетке
 */

public class TeacherController {

    private TeacherDecoratorModel model;
    private TeacherView view;

    public TeacherController(TeacherDecoratorModel model, TeacherView view) {
        this.model = model;
        this.view = view;
        init();
    }

    /**
     * Отображаем гуи
     * Далее получаем картнку, по ходу получения картинки отображаем её на гуи
     */

    public void init() {
        view.init();
        model.getImage().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() != null) { // Чтение картинки из ответа сервера
                        BufferedImage image = ImageIO.read(response.body().byteStream());
                        view.initPortrait(image.getScaledInstance(200, 200, 5));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) { // Неправильный ответ от сервера и т.д.
                t.printStackTrace();
            }
        });
    }


}
