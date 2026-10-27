package com.example.demo.Service;

import com.example.demo.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    User getUserByCredentials(String username, String password);

}
