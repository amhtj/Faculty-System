package model.rest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.rest.serializable.DateDeserializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.sql.Date;
import java.sql.Timestamp;

public class Service {

    private static Service instance;
    private static final String BASE_URL = "http://localhost:7070";
    private Retrofit retrofit;
    private UserApi userApi;
    private TeacherApi teacherApi;
    private SubjectApi subjectApi;
    private MessageApi messageApi;
    private FileApi fileApi;


    private Service() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public UserApi getUserApi() {
        if (userApi == null) {
            userApi = retrofit.create(UserApi.class);
        }
        return userApi;
    }

    public TeacherApi getTeacherApi() {
        if (teacherApi == null) {
            teacherApi = retrofit.create(TeacherApi.class);
        }
        return teacherApi;
    }

    public SubjectApi getSubjectApi() {
        if (subjectApi == null) {
            subjectApi = retrofit.create(SubjectApi.class);
        }
        return subjectApi;
    }

    public MessageApi getMessageApi() { // Регистрация API в ретрофите
        if (messageApi == null) {
            messageApi = retrofit.create(MessageApi.class);
        }
        return messageApi;
    }

    public FileApi getFileApi() {
        if (fileApi == null) {
            fileApi = retrofit.create(FileApi.class);
        }
        return fileApi;
    }
}
