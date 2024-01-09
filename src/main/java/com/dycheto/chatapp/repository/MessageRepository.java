package com.dycheto.chatapp.repository;


import com.dycheto.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m LEFT JOIN FETCH m.chatRoom WHERE m.chatRoom.id = :id")
    Optional<List<Message>> findAllMessagesByChatRoomId(@Param("id") Long id);
}
