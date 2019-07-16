package controller;

import model.TeacherPageModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.TeacherPageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Контроллер для страницы с преподавателем
 */

public class TeacherPageController {

    private TeacherPageModel model;
    private TeacherPageView view;

    public TeacherPageController(TeacherPageModel model, TeacherPageView view) {
        this.model = model;
        this.view = view;
        init();
    }

    /**
     * Инициализируем основную часть гуи, по поулчении фотографии, прогружаем и её.
     */

    public void init() {
        view.initGui();
        model.getImage().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.body() != null) { // Чтение картинки из ответа сервера
                        BufferedImage image = ImageIO.read(response.body().byteStream());
                        view.initPortrait(image.getScaledInstance(200,200,5));
                    }
                } catch(IOException e) {
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
