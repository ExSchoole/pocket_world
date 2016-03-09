package org.exschool.pocketworld.chat.service;


import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChatServiceImpl implements ChatService{

    @Autowired
    private Dao dao;

    @Override
    public List<Message> getAllByRecipient(String recipient) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class)
                .add(Property.forName("recipient").eq(recipient));
        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public Message save(Message entity) {
        return dao.save(entity);
    }
}
