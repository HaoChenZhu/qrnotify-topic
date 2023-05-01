package com.nebrija.tfg.qrnotify.topic.services.impl;

import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Topic;
import com.nebrija.tfg.qrnotify.topic.dao.mongodb.repository.TopicRepository;
import com.nebrija.tfg.qrnotify.topic.mappers.TopicMapper;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiDestination;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiUpdateTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.services.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nebrija.tfg.qrnotify.topic.constants.Constants.*;

import java.util.List;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<ApiTopicResponseDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return topicMapper.fromEntitiesToDtos(topics);
    }

    @Override
    public ApiTopicResponseDto getTopic(String identifier) {
        Topic topic = topicRepository.findBy_id(identifier);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto createTopic(ApiTopicRequestDto apiTopicRequestDto) {
        Topic topic = topicMapper.toEntity(apiTopicRequestDto);
        String publishName = buildQRNOTIFYTopic(apiTopicRequestDto.getDestination());
        topic.setPublishName(publishName);
        topic.setDestination(topicMapper.map(apiTopicRequestDto.getDestination()));
        topicRepository.save(topic);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto updateTopic(String identifier, ApiUpdateTopicRequestDto apiTopicRequestDto) {
        Topic topic = topicRepository.findBy_id(identifier);
        topic.setName(apiTopicRequestDto.getName() != null ? apiTopicRequestDto.getName() : topic.getName());
        topic.setDescription(apiTopicRequestDto.getDescription() != null ? apiTopicRequestDto.getDescription() : topic.getDescription());
        if (apiTopicRequestDto.getDestination() != null) {
            topic.setDestination(topicMapper.map(apiTopicRequestDto.getDestination()));
            topic.setPublishName(buildQRNOTIFYTopic(apiTopicRequestDto.getDestination()));
        }
        topicRepository.save(topic);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto deleteTopic(String identifier) {
        Topic topic = topicRepository.findBy_id(identifier);
        if (topic == null) return null;
        topicRepository.deleteBy_id(identifier);
        return null;
    }

    public String buildQRNOTIFYTopic(ApiDestination destination) {
        return QRNOTIFY_TOPIC + "/" + destination.getCompany() + "/" + destination.getArea() + "/" + destination.getStore();
    }
}
