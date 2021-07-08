package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try  {

            Statement statement = Util.getConnection().createStatement();
            Util.getConnection().setAutoCommit(false);
            statement.execute("CREATE TABLE `task`.`users` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));;");
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {

            }
        }
    }

    public void dropUsersTable() {
        try {

            Statement statement = Util.getConnection().createStatement();
            Util.getConnection().setAutoCommit(false);
            statement.execute("DROP TABLE `users`");
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {

            String sql =("INSERT INTO users (name,lastName,age) VALUES (?,?,?)");
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql);
            Util.getConnection().setAutoCommit(false);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.execute();
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {

            String sql = ("DELETE FROM `users` WHERE id = (?)");
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql);
            Util.getConnection().setAutoCommit(false);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            Util.getConnection().commit();

        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {

            Statement statement = Util.getConnection().createStatement();
            Util.getConnection().setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `users`;");

            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),resultSet.getString(3),resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                users.add(user);


            }
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {

            Statement statement  = Util.getConnection().createStatement();
            Util.getConnection().setAutoCommit(false);
            statement.execute("TRUNCATE TABLE `users`");
            Util.getConnection().commit();
        } catch (SQLException throwables) {
            try {
                Util.getConnection().rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
