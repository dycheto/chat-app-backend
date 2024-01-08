package com.dycheto.chatapp.service;

import com.dycheto.chatapp.entity.User;
import com.dycheto.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User findUserById(Long id) {

        Optional<User> user = userRepository.findById(id);

        return user.get();

    }

    @Transactional
    public void deleteById(Long id) throws Exception {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
           throw new IllegalAccessException("There is no such user with the given Id");
        }
    }
}
