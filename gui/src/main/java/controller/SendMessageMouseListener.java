package controller;
import model.MessageModel;
import model.rest.model.Messages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import view.MainFrame;
import view.MessageView;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SendMessageMouseListener implements MouseListener {

    private MessageView view;
    private MessageModel model;

    public SendMessageMouseListener(MessageModel model, MessageView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        final Messages messages = new Messages();
        messages.setText(view.getMessageField().getText());
        messages.setUserId(MainFrame.userId);
        messages.setTeacherId(model.getTeacherId());
        model.sendMessages(messages).enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                if (response.body() != null) {
                    model.getCurrentMessages().add(response.body());
                    view.initMessages(model.getCurrentMessages());
                }
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                t.printStackTrace();
            }
        });
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
