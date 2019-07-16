package org.course.controllers;
import org.course.dao.SubjectDao;
import org.course.models.Subject;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController // Маркировка класса как контроллера, а также данная аннотация отражает, что  методы можно вовзращать
                // в виде объектов формата json

public class SubjectController {

    private final static String API_SUBJECT = "/subjects/";
    private final static String API_SUBJECT_ALL = "/subjects/all";
    private SubjectDao subjectDao = new SubjectDao();

    // Используем @RequestParam для извлечения параметров запроса
    // Используем @RequestMapping: указываем, какой запрос принимать

    @RequestMapping(value = API_SUBJECT, method = RequestMethod.GET)
    public Subject getSubject(@RequestParam int id) {
        return subjectDao.select(id);
    }

    @RequestMapping(value = API_SUBJECT_ALL, method = RequestMethod.GET)
    public List<Subject> getSubjects(@RequestParam int id) {
        return subjectDao.selectByTeacherId(id);
    }

    @RequestMapping(value = API_SUBJECT, method = RequestMethod.POST)
    public String createSubject(@RequestBody Subject body) {
        return subjectDao.insert(body);
    }

}
