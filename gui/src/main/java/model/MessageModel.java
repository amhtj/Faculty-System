package model;
import model.rest.model.Messages;
import model.rest.service.Service;
import retrofit2.Call;
import view.MainFrame;

import java.util.*;

public class MessageModel {

    private int teacherId;
    private List<Messages> currentMessages = new ArrayList<>();

    public MessageModel(int teacherId) {
        this.teacherId = teacherId;
    }

    public Call<List<Messages>> getMessages() {
        return Service.getInstance().getMessageApi().getMessages(MainFrame.userId, teacherId);
    }

    public Call<Messages> sendMessages(Messages messages) {
        return Service.getInstance().getMessageApi().createMessage(messages);
    }

    public List<Messages> getCurrentMessages() {
        return currentMessages;
    }

    public void setCurrentMessages(List<Messages> list) {
        currentMessages.addAll(list);
    }

    public int getTeacherId() {
        return teacherId;
    }
}
