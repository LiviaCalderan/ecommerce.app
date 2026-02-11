package com.app.ecommerce.service;

import com.app.ecommerce.repositories.UserRepository;
import com.app.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<User> fetchUser(UUID id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(UUID id, User userUpdate) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(userUpdate.getFirstName());
                    existingUser.setSurname(userUpdate.getSurname());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
