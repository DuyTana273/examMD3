package com.demo.demo.repository.users;

import com.demo.demo.model.Users;
import com.demo.demo.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepo implements IUserRepo{

    // Các câu truy vấn SQL
    private static final String INSERT_USER = "INSERT INTO Users (username, password, fullName, email, phone, userRole) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Users";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE userID = ?";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM Users WHERE username = ? AND password = ?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";
    private static final String SEARCH_USERS = "SELECT * FROM Users WHERE 1=1 " +
            "AND (username LIKE ? OR fullName LIKE ? OR email LIKE ? OR userRole LIKE ?)";
    private static final String UPDATE_USER = "UPDATE Users SET username = ?, password = ?, fullName = ?, email = ?, userRole = ? WHERE userID = ?";
    private static final String DELETE_USER_BY_USERNAME = "DELETE FROM Users WHERE username = ?";

    // Create
    @Override
    public void addUser(Users users) {
        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());
            preparedStatement.setString(3, users.getFullName());
            preparedStatement.setString(4, users.getEmail());
            preparedStatement.setString(5, users.getUserRole());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find
    @Override
    public Optional<Users> findUserByID(int userID) {
        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Users> findAllUsers() {
        List<Users> users = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Optional<Users> findUserByUsernameAndPassword(String username, String password) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Users> searchUsers(String searchKeyword) {
        List<Users> users = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USERS)) {
            String search = "%" + searchKeyword + "%";
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(mapUsers(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void updateUser(Users users) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());
            preparedStatement.setString(3, users.getFullName());
            preparedStatement.setString(4, users.getEmail());
            preparedStatement.setString(5, users.getUserRole());
            preparedStatement.setInt(6, users.getUserID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /// Delete
    @Override
    public boolean deleteUserByUsername(String username) {
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);

            int rows = preparedStatement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Ánh Xạ RS -> Users
    @Override
    public Users mapUsers(ResultSet rs) throws SQLException {
        return new Users(
                rs.getInt("userID"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("email"),
                rs.getString("userRole")
        );
    }
}
