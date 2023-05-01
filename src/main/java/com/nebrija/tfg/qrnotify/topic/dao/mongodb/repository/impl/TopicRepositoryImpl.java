package com.nebrija.tfg.qrnotify.topic.dao.mongodb.repository.impl;


import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Topic;
import com.nebrija.tfg.qrnotify.topic.dao.mongodb.repository.TopicRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepositoryImpl implements TopicRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Topic> findAll() {
        List<Topic> topics = mongoTemplate.findAll(Topic.class);
        return topics;
    }

    @Override
    public Topic findBy_id(String _id) {
        ObjectId id = new ObjectId(_id);
        Topic topic = mongoTemplate.findById(id, Topic.class);
        return topic;
    }

    @Override
    public void save(Topic topic) {
        mongoTemplate.save(topic);
    }

    @Override
    public void deleteBy_id(String _id) {
        ObjectId id = new ObjectId(_id);
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Topic.class);
    }


}
