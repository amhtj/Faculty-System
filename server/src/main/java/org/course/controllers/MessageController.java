package org.course.controllers;

import org.course.dao.LoginUserDao;
import org.course.dao.MessageDao;
import org.course.dao.TeacherDao;
import org.course.models.LoginUser;
import org.course.models.Messages;
import org.course.models.Teacher;
import org.course.payload.Message;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private final static String API_MESSAGES = "/messages";
    private final static String API_MESSAGES_ALL = "/messages/all";
    private MessageDao messageDao = new MessageDao();
    private LoginUserDao loginUserDao = new LoginUserDao();
    private TeacherDao teacherDao = new TeacherDao();

    @RequestMapping(value = API_MESSAGES, method = RequestMethod.GET)
    public Messages getMessage(@RequestParam int id) {
        return messageDao.select(id);
    }

    /**
     * Выдергиваем сообщения от первого пользователя ко второму
     * и от второго к первому
     * сортируем их по дате
     *
     * @param userId
     * @param teacherId
     * @return
     */
    @RequestMapping(value = API_MESSAGES_ALL, method = RequestMethod.GET)
    public List<Message> getMessages(@RequestParam int userId, @RequestParam int teacherId) {
        String userOne = loginUserDao.select(userId).getLogin();
        Teacher teacher = teacherDao.select(teacherId);
        LoginUser secondUser = loginUserDao.select(teacher.getUserId());
        String userTwo = secondUser.getLogin();
        List<Message> userOneToSecond =
                messageDao.selectAll(userId, secondUser.getId()).stream().map(it -> { // Выбираем сообщ. от первого ко второму
                    Message message = new Message();
                    message.setId(it.getId());
                    message.setText(it.getText());
                    message.setUserName(userOne);
                    message.setDate(it.getDate().toString());
                    return message;
                }).collect(Collectors.toList()); // Собираем поток в список

        List<Message> userTwoToOne = messageDao.selectAll(secondUser.getId(), userId).stream().map(it -> {
            Message message = new Message();
            message.setId(it.getId());
            message.setText(it.getText());
            message.setUserName(userTwo);
            message.setDate(it.getDate().toString());
            return message;
        }).collect(Collectors.toList());

        userOneToSecond.addAll(userTwoToOne); // Объединение сообщений
        userOneToSecond.sort(Comparator.comparing(Message::getDate)); // Сортировка по дате
        return userOneToSecond;
    }

    @RequestMapping(value = API_MESSAGES, method = RequestMethod.POST)
    public Message createMessage(@RequestBody Messages body) {
        body.setDate(Timestamp.valueOf(LocalDateTime.now()));

        String userName = loginUserDao.select(body.getUserId()).getLogin();
        Teacher teacher = teacherDao.select(body.getTeacherId());
        body.setTeacherId(teacher.getUserId());

        messageDao.insert(body);
        final Message message = new Message();
        message.setText(body.getText());
        message.setUserId(body.getUserId());
        message.setDate(body.getDate().toString());
        message.setUserName(userName);
        return message;
    }

}
