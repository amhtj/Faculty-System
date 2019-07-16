package org.course.controllers;
import org.course.dao.TeacherDao;
import org.course.models.Teacher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
public class TeacherController {

    // Пути запросов

    private final static String API_TEACHER = "/teachers/";
    private final static String API_TEACHER_IMAGE = "/teachers/image";
    private final static String API_TEACHER_ALL = "/teachers/all";
    private TeacherDao teacherDao = new TeacherDao();

    //

    @RequestMapping(value = API_TEACHER, method = RequestMethod.GET)
    public Teacher getTeacher(@RequestParam int id) {
        return teacherDao.select(id);
    }

    @RequestMapping(value = API_TEACHER_ALL, method = RequestMethod.GET)
    public List<Teacher> getTeachers() {
        return teacherDao.selectAll();
    }

    @RequestMapping(value = API_TEACHER, method = RequestMethod.POST)
    public String createTeacher(@RequestBody Teacher body) {
        return teacherDao.insert(body);
    }

    @RequestMapping(value = API_TEACHER_IMAGE, method = RequestMethod.GET)
    public void getImageAsByteArray(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
        final String path = teacherDao.select(id).getImagePath();
        FileInputStream stream = new FileInputStream(path);
        byte[] bytes = readAllBytes(stream); // Чтение картинки из потока
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.getOutputStream().write(bytes);
    }

    private byte[] readAllBytes(FileInputStream in) throws IOException {
        byte[] targetArray = new byte[in.available()]; // Получаем количество байт и выделяем массив с этим количеством
        in.read(targetArray);
        in.close();
        return targetArray;
    }
}
