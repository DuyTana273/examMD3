package com.demo.demo.service;

import com.demo.demo.model.Users;
import com.demo.demo.repository.users.IUserRepo;
import com.demo.demo.repository.users.UsersRepo;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final IUserRepo iUserRepo = new UsersRepo();

    @Override
    public void addUser(Users users) {
        iUserRepo.addUser(users);
    }

    @Override
    public Optional<Users> findUserByID(int userID) {
        return iUserRepo.findUserByID(userID);
    }

    @Override
    public List<Users> findAllUsers() {
        return iUserRepo.findAllUsers();
    }

    @Override
    public Optional<Users> findUserByUsernameAndPassword(String username, String password) {
        return iUserRepo.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<Users>  findByUsername(String username) {
        return iUserRepo.findByUsername(username);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return iUserRepo.findByEmail(email);
    }

    @Override
    public List<Users> searchUsers(String searchKeyword) {
        return iUserRepo.searchUsers(searchKeyword);
    }

    @Override
    public void updateUser(Users users) {
        iUserRepo.updateUser(users);
    }

    @Override
    public boolean deleteUserByUsername(String username) {
        return iUserRepo.deleteUserByUsername(username);
    }
}
