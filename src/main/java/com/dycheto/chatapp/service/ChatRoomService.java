package com.dycheto.chatapp.service;

import com.dycheto.chatapp.entity.ChatRoom;
import com.dycheto.chatapp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
