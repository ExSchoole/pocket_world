package org.exschool.pocketworld.chat.service;


import org.exschool.pocketworld.chat.model.Message;

import java.util.List;

public interface ChatService {

    List<Message> getAllByRecipient(String recipient);

    Message save(Message entity);
}
