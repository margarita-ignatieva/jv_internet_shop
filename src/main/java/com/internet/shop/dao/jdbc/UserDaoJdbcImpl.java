package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.interfaces.UserDao;
import com.internet.shop.exceptions.DataProcessingException;
import com.internet.shop.library.Dao;
import com.internet.shop.model.Role;
import com.internet.shop.model.User;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        String query = "SELECT * FROM users WHERE deleted = false AND user_login = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, login);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                statement.close();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get user with login " + login, e);
        }
        user.setRoles(getRoles(user.getUserId()));
        return Optional.of(user);
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users(user_name, user_login, user_password) VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to add the user " + user.getName(), e);
        }
        addRoles(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        User user = null;
        String query = "SELECT * FROM users WHERE deleted = false AND user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
                statement.close();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get the user with id: " + id, e);
        }
        user.setRoles(getRoles(user.getUserId()));
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users WHERE deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
            for (User user : users) {
                Long id = user.getUserId();
                Set<Role> roles = getRoles(id);
                user.setRoles(roles);
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get users from database ", e);
        }
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET user_name = ?, user_login = ?, user_password = ? "
                + "WHERE user_id = ? "
                + "AND deleted = false";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to update the user with id: "
                    + user.getUserId(), e);
        }
        deleteRoles(user.getUserId());
        return addRoles(user);
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE users SET deleted = true WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete user with Id = " + id, e);
        }
    }

    private User addRoles(User user) {
        String query = "INSERT INTO user_roles(user_id, role_id) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            for (Role role : user.getRoles()) {
                statement.setLong(1, user.getUserId());
                statement.setLong(2, role.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to add user " + user.getName()
                    + " roles ", e);
        }
        user.setRoles(getRoles(user.getUserId()));
        return user;
    }

    private Set<Role> getRoles(Long userId) {
        String query = "SELECT roles.role_id, role_name FROM roles JOIN user_roles"
                               + "ON user_roles.role_id = roles.role_id WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.getGeneratedKeys();
            Set<Role> roles = new HashSet<>();
            while (resultSet.next()) {
                roles.add(Role.of(resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Failed to get roles of user with id "
                    + userId, e);
        }
    }

    private boolean deleteRoles(Long userId) {
        String query = "DELETE FROM user_roles WHERE user_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userId);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete roles of user with Id"
                    + userId, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("user_id");
        String name = resultSet.getString("user_name");
        String login = resultSet.getString("user_login");
        String password = resultSet.getString("user_password");
        Set<Role> roles = new HashSet<>();
        return new User(id, name, login, password, roles);
    }
}
