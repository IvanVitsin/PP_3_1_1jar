package com.example.pp_3_1_1jar.dao;

import com.example.pp_3_1_1jar.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUserById(int id);
    void updateUserById(int id, User user);
    User getUserById(int id);
}
