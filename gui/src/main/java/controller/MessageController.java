package controller;
import model.MessageModel;
import model.rest.model.Messages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.MessageView;
import java.util.List;

/**
 * Контроллер для сообщений
 */

public class MessageController {

    private MessageView view;
    private MessageModel model;

    public MessageController(MessageView view, MessageModel model) {
        this.view = view;
        this.model = model;
        init();
    }

    /**
     * Инициализируем гуи, делаем запрос на сообщения, в случае успеха, прогружаем их на экране
     */

    private void init() {
        view.init();
        model.getMessages().enqueue(new Callback<List<Messages>>() {
            @Override
            public void onResponse(Call<List<Messages>> call, Response<List<Messages>> response) {
                if (response.body() != null) {
                    model.setCurrentMessages(response.body());
                    view.initMessages(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Messages>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
