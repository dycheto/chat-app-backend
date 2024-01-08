package com.dycheto.chatapp.service;

import com.dycheto.chatapp.entity.Message;
import com.dycheto.chatapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void save(Message message){
        messageRepository.save(message);
    }

    public Message findMessageById(Long id){

        Optional<Message> message = messageRepository.findById(id);

        return message.get();

    }


    @Transactional
    public void deleteMessageById(Long id) throws Exception{
        Optional<Message> messageOptional = messageRepository.findById(id);

        if (messageOptional.isPresent()) {
            messageRepository.delete(messageOptional.get());
        } else {
            throw new IllegalAccessException("There is no message with the given Id");
        }
    }
}
