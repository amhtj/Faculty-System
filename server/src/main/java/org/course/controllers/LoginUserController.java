package org.course.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.course.dao.LoginUserDao;
import org.course.models.LoginUser;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class LoginUserController {

    private final static String API_LOGIN = "/login/";
    private final static String API_VERIFIED = "/login/verified";
    private final static String API_REGISTER_VERIFIED = "/register/verified";

    private LoginUserDao loginUserDao = new LoginUserDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = API_LOGIN, method = RequestMethod.GET)
    public LoginUser getUser(@RequestParam int id) {
        return loginUserDao.select(id);
    }

    @RequestMapping(value = API_LOGIN, method = RequestMethod.POST)
    public String createUser(@RequestBody LoginUser body) {
        return loginUserDao.insert(body);
    }

    @RequestMapping(value = API_VERIFIED, method = RequestMethod.POST)
    public String verifiedUser(@RequestBody LoginUser body)
            throws JsonProcessingException { // Поиск подходящих логинов и паролей
        LoginUser loginUser = loginUserDao.selectByLoginAndPassword(body.getLogin(), body.getPassword());
        Map<String, Object> map = new HashMap<>();
        if (loginUser != null) {
            map.put("isVerified", true);
            map.put("id", loginUser.getId());
            map.put("isTeacher", loginUser.isTeacher());
        } else {
            map.put("isVerified", false);
        }
        return objectMapper.writeValueAsString(map);
    }

    @RequestMapping(value = API_REGISTER_VERIFIED, method = RequestMethod.POST)
    public String registerVerify(@RequestBody LoginUser body) throws JsonProcessingException {
        LoginUser loginUser = loginUserDao.selectByLoginAndPassword(body.getLogin(), body.getPassword());
        Map<String, Object> map = new HashMap<>();
        if (loginUser != null) {
            map.put("isVerified", false);
        } else {
            loginUserDao.insert(body);
            final LoginUser user = loginUserDao.selectByLoginAndPassword(body.getLogin(), body.getPassword());
            map.put("isVerified", true);
            map.put("id", user.getId());
            map.put("isTeacher", user.isTeacher());
        }
        return objectMapper.writeValueAsString(map);
    }
}
