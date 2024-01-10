package com.dycheto.chatapp.service;

import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.repository.ChatRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {


    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }

    @Transactional
    public void save(ChatRoom chatRoom){

        chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomByName(String name){
        Optional<ChatRoom> chatRoom = chatRoomRepository.getChatRoomByName(name);
        return chatRoom.get();
    }

    public List<ChatRoom> findAll(){
        return chatRoomRepository.findAll();
    }

    public ChatRoom findChatRoomById(Long id){

        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);

        return chatRoom.get();

    }

    @Transactional
    public void updateChatRoom(ChatRoom chatRoom){
        chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public void deleteChatRoomById(Long id) throws Exception{
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(id);

        if (chatRoomOptional.isPresent()) {
            chatRoomRepository.delete(chatRoomOptional.get());
        } else {
            throw new IllegalAccessException("There is no Chat room with the given Id");
        }
    }

    public ChatRoom getChatRoomAndUsers(Long id) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.getChatRoomAndUsers(id);
        return chatRoom.orElseThrow(() -> new EntityNotFoundException("ChatRoom not found with id: " + id));
    }

}
