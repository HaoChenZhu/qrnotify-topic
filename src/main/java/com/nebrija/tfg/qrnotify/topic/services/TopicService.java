package com.nebrija.tfg.qrnotify.topic.services;

import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiUpdateTopicRequestDto;

import java.util.List;

public interface TopicService {
    List<ApiTopicResponseDto> getAllTopics();

    ApiTopicResponseDto getTopic(String identifier);

    ApiTopicResponseDto createTopic(ApiTopicRequestDto apiTopicRequestDto);

    ApiTopicResponseDto updateTopic(String identifier, ApiUpdateTopicRequestDto apiTopicRequestDto);

    ApiTopicResponseDto deleteTopic(String identifier);

    ApiTopicResponseDto getTopicByOwner(String owner);
}
