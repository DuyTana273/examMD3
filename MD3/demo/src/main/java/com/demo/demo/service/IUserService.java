package com.demo.demo.service;

import com.demo.demo.model.Users;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    // Create
    void addUser(Users users);

    // Find
    Optional<Users> findUserByID(int userID);
    List<Users> findAllUsers();
    Optional<Users> findUserByUsernameAndPassword(String username, String password);
    Optional<Users>  findByUsername(String username);
    Optional<Users> findByEmail(String email);
    List<Users> searchUsers(String searchKeyword);

    // Update
    void updateUser(Users users);

    // Delete
    boolean deleteUserByUsername(String username);
}
