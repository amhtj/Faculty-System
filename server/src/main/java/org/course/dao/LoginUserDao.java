package org.course.dao;
import org.course.config.Settings;
import org.course.models.LoginUser;

import java.sql.*;

public class LoginUserDao {

    // Запросы
    private static final String SELECT_REQUEST = "Select * From Users where id = ?";
    private static final String INSERT_REQUEST = "Insert Into Users (login, password, isTeacher) values (?, ?, ?)";
    private final static String SELECT_REQUEST_BY_LOGIN = "Select * from Users where login = ? and password = ?";

    private static Connection con; // Переменная JDBC для установки и управления соединением
    private enum Fields { // Набор логически связанных констант
        id, login, password, isTeacher
    }

    public LoginUserDao() {
        try {
            // Соединение JDBC & MySQL
            con = DriverManager.getConnection(Settings.URL, Settings.USER, Settings.PASSWORD);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public LoginUser select(int id) {
        LoginUser loginUser = null;
        try (final PreparedStatement statement = con.prepareStatement(SELECT_REQUEST)) { // Абстракция для помещения параметров,
                                                                                         // выполняем запрос
            statement.setInt(1, id); // Получение результата и чтение из него
            final ResultSet resultSet = statement.executeQuery(); // выборка
            if (resultSet.next()) { // Проверка, есть ли результат
                loginUser = initLoginUser(resultSet);
            }
            resultSet.close(); // Закрываем результат. Соединение закрывается автоматически
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginUser;
    }

    public LoginUser selectByLoginAndPassword(String login, String password) {
        LoginUser loginUser = null;
        try (final PreparedStatement statement = con.prepareStatement(SELECT_REQUEST_BY_LOGIN)) {
            statement.setString(1, login);
            statement.setString(2, password);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loginUser = initLoginUser(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginUser;
    }

    private LoginUser initLoginUser(ResultSet resultSet) throws SQLException {
        LoginUser loginUser = new LoginUser();
        loginUser.setId(resultSet.getInt(Fields.id.name()));
        loginUser.setLogin(resultSet.getString(Fields.login.name()));
        loginUser.setPassword(resultSet.getString(Fields.password.name()));
        loginUser.setTeacher(resultSet.getBoolean(Fields.isTeacher.name()));
        return loginUser;
    }

    public String insert(LoginUser loginUser) {
        try (final PreparedStatement statement = con.prepareStatement(INSERT_REQUEST)) {
            statement.setString(1, loginUser.getLogin());
            statement.setString(2, loginUser.getPassword());
            statement.setBoolean(3, loginUser.isTeacher());
            return String.format("Success %d", statement.executeUpdate());
        } catch (SQLException e) {
            return String.format("Error %s", e.getMessage());
        }
    }
}

