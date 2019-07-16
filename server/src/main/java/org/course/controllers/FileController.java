package org.course.controllers;

import org.course.config.Settings;
import org.course.dao.FilesDao;
import org.course.models.FileModel;
import org.course.payload.FileGetPayload;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private final static String API_FILE = "/file/";
    private final static String API_FILE_DOWNLOAD = "/file/download";

    private FilesDao filesDao = new FilesDao();

    /**
     * Получаем всю мета информацию о всех файлах
     *
     * @param teacherId - ид препода которому принадлежат файлы
     * @return
     */
    @RequestMapping(value = API_FILE, method = RequestMethod.GET)
    public List<FileGetPayload> getFiles(@RequestParam int teacherId) {
        final List<FileModel> fileModels = filesDao.selectByIdRequest(teacherId);
        return fileModels.stream().map(it -> {
            FileGetPayload payload = new FileGetPayload();
            payload.setPath(it.getPath());
            payload.setName(it.getName());
            return payload;
        }).collect(Collectors.toList());
    }

    @RequestMapping(value = API_FILE_DOWNLOAD, method = RequestMethod.GET)
    public byte[] getFile(@RequestParam String path) {
        final String replaceAll = path.replace("/", "\\");
        final byte[] bytes = readFile(replaceAll);
        return bytes;
    }

    private byte[] readFile(String path) {
        FileInputStream reader;
        byte[] array = null;
        try {
            reader = new FileInputStream(path);
            array = new byte[reader.available()];
            reader.read(array);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * загружаем файл и вставляем мета данные в бд
     *
     * @param teacherId - ид учителя которому принадлежит файл
     * @param name      - имя препода
     * @param body      - тело файла
     * @return - мета информация о файле
     */
    @RequestMapping(value = API_FILE, method = RequestMethod.POST)
    public FileGetPayload loadFile(@RequestParam("teacherId") int teacherId, @RequestParam("name") String name,
                                   @RequestBody byte[] body) {
        FileModel model = new FileModel();
        model.setName(name);
        model.setPath(Settings.filePath + "\\" + name);
        model.setTeacherId(teacherId);
        writeFile(body, name);
        final String insert = filesDao.insert(model);
        FileGetPayload payload = new FileGetPayload();
        payload.setPath(Settings.filePath + name);
        payload.setName(name);
        return payload;
    }

    private void writeFile(byte[] bytes, String name) {
        FileOutputStream writer;
        byte[] array = null;
        try {
            File file = new File(Settings.filePath + "\\" + name);
            file.createNewFile();
            writer = new FileOutputStream(file);
            writer.write(bytes);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
