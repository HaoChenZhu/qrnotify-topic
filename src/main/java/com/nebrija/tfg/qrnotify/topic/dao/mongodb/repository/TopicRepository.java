package com.nebrija.tfg.qrnotify.topic.dao.mongodb.repository;



import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Topic;

import java.util.List;

public interface TopicRepository {

    List<Topic> findAll();

    Topic findBy_id(String _id);

    void save(Topic topic);

    void deleteBy_id(String _id);

    Topic findByOwner(String owner);

}
