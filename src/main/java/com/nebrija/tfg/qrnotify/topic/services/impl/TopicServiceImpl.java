package com.nebrija.tfg.qrnotify.topic.services.impl;

import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Topic;
import com.nebrija.tfg.qrnotify.topic.dao.mongodb.repository.TopicRepository;
import com.nebrija.tfg.qrnotify.topic.exceptions.ApiRequestException;
import com.nebrija.tfg.qrnotify.topic.exceptions.ApiResourceNotFoundException;
import com.nebrija.tfg.qrnotify.topic.mappers.TopicMapper;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiDestination;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiUpdateTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.services.AuthUserService;
import com.nebrija.tfg.qrnotify.topic.services.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private AuthUserService authUserService;

    @Override
    public List<ApiTopicResponseDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        if(topics.isEmpty()) {
             throw new ApiResourceNotFoundException("No hay topics registrados");
        }
        return topicMapper.fromEntitiesToDtos(topics);
    }

    @Override
    public ApiTopicResponseDto getTopic(String identifier) {
        if(StringUtils.isBlank(identifier)) {
            throw new ApiRequestException("El identificador del topic no puede estar vacío");
        }
        Topic topic = topicRepository.findBy_id(identifier);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto createTopic(ApiTopicRequestDto apiTopicRequestDto) {
        String owner = authUserService.getCurrentUser();
        Topic existsTopic = topicRepository.findByOwner(owner);
        if (existsTopic != null) {
            return null;
        }
        if(apiTopicRequestDto.getDestination() == null) {
            throw new ApiRequestException("El destino del topic no puede estar vacío");
        }
        Topic topic = topicMapper.toEntity(apiTopicRequestDto);
        String publishName = buildQRNOTIFYTopic(apiTopicRequestDto.getDestination());
        topic.setPublishName(publishName);
        topic.setDestination(topicMapper.map(apiTopicRequestDto.getDestination()));
        topic.setOwner(authUserService.getCurrentUser());
        topicRepository.save(topic);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto updateTopic(String identifier, ApiUpdateTopicRequestDto apiTopicRequestDto) {
        if(StringUtils.isBlank(identifier)) {
            throw new ApiRequestException("El identificador del topic no puede estar vacío");
        }
        Topic topic = topicRepository.findBy_id(identifier);
        if (topic == null) {
            throw new ApiResourceNotFoundException("No se ha encontrado el topic con id: " + identifier);
        }
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
        if(StringUtils.isBlank(identifier)) {
            throw new ApiRequestException("El identificador del topic no puede estar vacío");
        }
        Topic topic = topicRepository.findBy_id(identifier);
        if (topic == null) return null;
        topicRepository.deleteBy_id(identifier);
        return topicMapper.toDto(topic);
    }

    @Override
    public ApiTopicResponseDto getTopicByOwner(String owner) {
        Topic topic = topicRepository.findByOwner(owner);
        if (topic != null) {
            return topicMapper.toDto(topic);
        }
        return null;
    }

    public String buildQRNOTIFYTopic(ApiDestination destination) {
        return QRNOTIFY_TOPIC + "/" + destination.getCompany() + "/" + destination.getArea() + "/" + destination.getStore();
    }
}
