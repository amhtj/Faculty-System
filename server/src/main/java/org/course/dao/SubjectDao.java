package org.course.dao;
import org.course.config.Settings;
import org.course.models.Subject;
import org.course.models.Teacher;
import java.sql.*;
import java.util.*;

public class SubjectDao {
    private static final String SELECT_REQUEST = "Select * From Subjects where id = ?";
    private static final String INSERT_REQUEST = "Insert into Subjects (name, teacher_id, groups) values (?, ?, ?)";
    private final static String SELECT_BY_TEACHER_ID = "Select * from Subjects where teacher_id = ?";

    private static Connection con;

    private enum Fields {name, teacher_id, groups}

    public SubjectDao() {
        try {
            con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public List<Subject> selectByTeacherId(int teacherId) {
        List<Subject> result = new LinkedList<>();
        try (final PreparedStatement statement = con.prepareStatement(SELECT_BY_TEACHER_ID)) {
            statement.setInt(1, teacherId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Subject subject = initSubject(resultSet);
                result.add(subject);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Subject select(int id) {
        Subject subject = null;
        try (final PreparedStatement statement = con.prepareStatement(SELECT_REQUEST)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                subject = initSubject(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    private Subject initSubject(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setTeacherId(resultSet.getInt(SubjectDao.Fields.teacher_id.name()));
        subject.setName(resultSet.getString(SubjectDao.Fields.name.name()));
        subject.setGroups(resultSet.getString(SubjectDao.Fields.groups.name()));
        return subject;
    }

    public String insert(Subject subject) {
        try (final PreparedStatement statement = con.prepareStatement(INSERT_REQUEST)) {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getTeacherId());
            statement.setString(3, subject.getGroups());
            return String.format("Success %d", statement.executeUpdate());
        } catch (SQLException e) {
            return String.format("Error %s", e.getMessage());
        }
    }
}
