package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User outdatedUser = getUserById(user.getId());
        outdatedUser.setUserName(user.getUserName());
        outdatedUser.setRole(user.getRole());
        outdatedUser.setPassword(user.getPassword());
        return userRepository.save(outdatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }

        userRepository.deleteById(id);
    }

    @Override
    public User getUserByCredentials(String username, String password) {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Some of the credentials entered are incorrect"));

        if (!user.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Some of the credentials entered are incorrect");
        }

        return user;
    }
}
