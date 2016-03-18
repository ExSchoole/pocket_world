package org.exschool.pocketworld.chat.service;


import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.chat.model.MessageStatus;
import org.exschool.pocketworld.chat.model.UserRelation;
import org.exschool.pocketworld.dao.Dao;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("chatService")
@Transactional
public class ChatServiceImpl implements ChatService{

    @Autowired
    private Dao dao;

    @Override
    public List<Message> getAllByPlayerName(String playerName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);

        detachedCriteria.add(Restrictions.or(Restrictions.eq("sender", playerName),
                                             Restrictions.eq("recipient", playerName)));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public List<Message> getAllMessagesBetweenTwoPlayers(String playerName1, String playerName2){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);

        detachedCriteria.add(Restrictions.or(
                Restrictions.and(Restrictions.eq("sender", playerName1),
                                 Restrictions.eq("recipient", playerName2)),
                Restrictions.and(Restrictions.eq("sender", playerName2),
                                 Restrictions.eq("recipient", playerName1))));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public List<Message> getAllNewMessagesBetweenTwoPlayers(String playerName1, String playerName2){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);

        detachedCriteria.add(Restrictions.and(Restrictions.eq("sender", playerName2),
                                              Restrictions.eq("recipient", playerName1),
                                              Restrictions.eq("status", MessageStatus.NEW)));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public List<Message> getAllNewMessages(String playerName){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Message.class);

        detachedCriteria.add(Restrictions.and(
                                            Restrictions.eq("recipient", playerName),
                                            Restrictions.eq("status", MessageStatus.NEW)));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public List<UserRelation> getAllRelationsByPlayerName(String playerName){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserRelation.class);
        detachedCriteria.add(Restrictions.eq("playername1", playerName));

        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public UserRelation saveRelation(String playerName1, String playerName2){
        UserRelation entity = new UserRelation(playerName1, playerName2);

        return dao.save(entity);
    }

    @Override
    public Message save(Message entity) {
        return dao.save(entity);
    }

    @Override
    public Collection<Message> saveAll(Collection<Message> entities){
        return dao.saveAll(entities);
    }
}
