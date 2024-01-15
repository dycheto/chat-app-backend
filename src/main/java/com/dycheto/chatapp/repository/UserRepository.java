package com.dycheto.chatapp.repository;

import com.dycheto.chatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.chatRooms WHERE u.username = :username")
    Optional<User> findByUsernameWithChatrooms(String username);
}
