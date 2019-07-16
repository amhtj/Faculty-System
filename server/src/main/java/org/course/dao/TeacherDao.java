package org.course.dao;
import org.course.config.Settings;
import org.course.models.Teacher;
import java.sql.*;
import java.util.*;

public class TeacherDao {
    private static final String SELECT_ALL_REQUEST = "Select * From Teachers";
    private static final String SELECT_REQUEST = "Select * From Teachers where id = ?";
    private static final String INSERT_REQUEST =
            "Insert Into Teachers (name, number, email, location, degree, visit_date) values (?, ?, ?, ?, ?, ?)";

    private static Connection con;

    private enum Fields {
        id, name,degree,number, email, location, visit_date, imagePath,userId
    }

    public TeacherDao() {
        try {
            con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public List<Teacher> selectAll() {
        List<Teacher> result = new LinkedList<>();
        try (final Statement statement = con.createStatement()){
            final ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUEST);

            while (resultSet.next()) {
                Teacher teacher = initTeacher(resultSet);
                result.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Teacher select(int id) {
        Teacher teacher = null;
        try(final PreparedStatement statement = con.prepareStatement(SELECT_REQUEST)) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery(); // Выборка
            if (resultSet.next()) {
                teacher = initTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    private Teacher initTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setImagePath(resultSet.getString(Fields.imagePath.name()));
        teacher.setId(resultSet.getInt(Fields.id.name()));
        teacher.setName(resultSet.getString(Fields.name.name()));
        teacher.setNumber(resultSet.getString(Fields.number.name()));
        teacher.setDegree(resultSet.getString(Fields.degree.name()));
        teacher.setLocation(resultSet.getString(Fields.location.name()));
        teacher.setEmail(resultSet.getString(Fields.email.name()));
        teacher.setVisitDate(resultSet.getDate(Fields.visit_date.name()));
        teacher.setUserId(resultSet.getInt(Fields.userId.name()));
        return teacher;
    }

    public String insert(Teacher teacher) {
        try (final PreparedStatement statement = con.prepareStatement(INSERT_REQUEST)){
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getNumber());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getLocation());
            statement.setString(5, teacher.getDegree());
            statement.setDate(6, teacher.getVisitDate());
            return String.format("Success %d", statement.executeUpdate());
        } catch (SQLException e) {
            return String.format("Error %s", e.getMessage());
        }
    }
}