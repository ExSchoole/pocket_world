package org.exschool.pocketworld.chat.service;


import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chatService")
@Transactional
public class ChatServiceImpl implements ChatService{

    @Autowired
    private Dao dao;

    @Override
    public List<Message> getAllByPlayerName(String playerName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);
        Criterion criteria1 = Restrictions.eq("sender", playerName);
        Criterion criteria2 = Restrictions.eq("recipient", playerName);

        detachedCriteria.add(Restrictions.or(criteria1,criteria2));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public Message save(Message entity) {
        return dao.save(entity);
    }
}
