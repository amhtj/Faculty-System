package org.course.dao;
import org.course.config.Settings;
import org.course.models.Messages;
import java.sql.*;
import java.util.*;

public class MessageDao {

    private static final String SELECT_ALL_REQUEST = "Select * From Messages where userId=? and teacherId =?";
    private static final String SELECT_REQUEST = "Select * From Messages where id = ?";
    private static final String INSERT_REQUEST =
            "Insert Into Messages (text, date, userId, teacherId) values (?, ?, ?, ?)";
    private static final String UPDATE_REQUEST =
            "Update Messages set text = ?, date = ?, userId = ?, teacherId = ? where id = ?";
    private static final String DELETE_REQUEST = "Delete From Messages where id = ?";

    private static Connection con;

    private enum Fields {
        id, text, date, userId, teacherId
    }

    public MessageDao() {
        try {
            con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public List<Messages> selectAll(int userId, int teacherId) { // Запрос на сообщение от пользователя конкретному преподавателю
        List<Messages> result = new LinkedList<>();
        try (final PreparedStatement statement = con.prepareStatement(SELECT_ALL_REQUEST)) {
            statement.setInt(1, userId);
            statement.setInt(2, teacherId);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Messages teacher = initMessage(resultSet);
                result.add(teacher);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Messages select(int id) {
        Messages teacher = null;
        try (final PreparedStatement statement = con.prepareStatement(SELECT_REQUEST)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery(); // Выборка
            if (resultSet.next()) {
                teacher = initMessage(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    private Messages initMessage(ResultSet resultSet) throws SQLException {
        Messages messages = new Messages();
        messages.setId(resultSet.getInt(Fields.id.name()));
        messages.setDate(resultSet.getTimestamp(Fields.date.name()));
        messages.setText(resultSet.getString(Fields.text.name()));
        messages.setUserId(resultSet.getInt(Fields.userId.name()));
        messages.setTeacherId(resultSet.getInt(Fields.teacherId.name()));
        return messages;
    }

    public String insert(Messages messages) {
        try (final PreparedStatement statement = con.prepareStatement(INSERT_REQUEST)) {
            statement.setString(1, messages.getText());
            statement.setTimestamp(2, messages.getDate());
            statement.setInt(3, messages.getUserId());
            statement.setInt(4, messages.getTeacherId());
            return String.format("Success %d", statement.executeUpdate());
        } catch (SQLException e) {
            return String.format("Error %s", e.getMessage());
        }
    }
}
