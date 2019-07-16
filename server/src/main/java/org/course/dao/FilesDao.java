package org.course.dao;
import org.course.config.Settings;
import org.course.models.FileModel;

import java.sql.*;
import java.util.*;

public class FilesDao {

    private static final String SELECT_BY_ID_REQUEST = "select id,path, teacher_id,name from Files where teacher_id=?";
    private static final String INSERT_REQUEST = "insert into Files(path,teacher_id, name) values(?,?, ?)";
    private static Connection con;

    private enum Fields {
        id, path, teacher_id, name
    }

    public FilesDao() {
        try {
            // Установка соединения между MySQL & JBDC
            con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public List<FileModel> selectByIdRequest(int teacherId) {
        List<FileModel> result = new LinkedList<>();
        try (final PreparedStatement statement = con.prepareStatement(SELECT_BY_ID_REQUEST)) {
            statement.setInt(1, teacherId);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                FileModel model = initModel(resultSet);
                result.add(model);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private FileModel initModel(ResultSet resultSet) throws SQLException {
        FileModel result = new FileModel();
        result.setId(resultSet.getInt(Fields.id.name()));
        result.setPath(resultSet.getString(Fields.path.name()));
        result.setName(resultSet.getString(Fields.name.name()));
        result.setTeacherId(resultSet.getInt(Fields.teacher_id.name()));
        return result;
    }

    public String insert(FileModel model) {
        try (final PreparedStatement statement = con.prepareStatement(INSERT_REQUEST)) {
            statement.setString(1, model.getPath());
            statement.setInt(2, model.getTeacherId());
            statement.setString(3, model.getName());
            return String.format("Success %d", statement.executeUpdate());
        } catch (SQLException e) {
            return String.format("Error %s", e.getMessage());
        }
    }
}
