package com.dycheto.chatapp.repository;

import com.dycheto.chatapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT c FROM ChatRoom c LEFT JOIN FETCH c.users WHERE c.id = :id")
    Optional<ChatRoom> getChatRoomAndUsers(@Param("id") Long id);


}
