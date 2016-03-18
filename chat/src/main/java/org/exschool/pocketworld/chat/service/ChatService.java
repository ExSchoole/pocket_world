package org.exschool.pocketworld.chat.service;


import org.exschool.pocketworld.chat.model.Message;
import org.exschool.pocketworld.chat.model.UserRelation;

import java.util.List;

public interface ChatService {

    List<Message> getAllByPlayerName(String playerName);

    Message save(Message entity);

    List<Message> getAllBetweenTwoPlayers(String playerName1, String playerName2);

    List<UserRelation> getAllRelationsByPlayerName(String playerName);

    UserRelation saveRelation(String playerName1, String playerName2);
}
