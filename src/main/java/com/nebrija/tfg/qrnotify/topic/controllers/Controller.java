package com.nebrija.tfg.qrnotify.topic.controllers;


import com.nebrija.tfg.qrnotify.topic.clients.AdminClient;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiUpdateTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.models.AdminResponseDTO;
import com.nebrija.tfg.qrnotify.topic.services.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "${chen.base_path}")
@Slf4j
public class Controller implements TopicApi {
    @Autowired
    private AdminClient adminClient;

    @Autowired
    private TopicService topicService;

    @Override
    public ResponseEntity<ApiTopicResponseDto> getTopicByOwner(@ApiParam(value = "Topic owner",required=true) @PathVariable("name") String name) {
        ApiTopicResponseDto topic = topicService.getTopicByOwner(name);
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }

        @Override
    public ResponseEntity<ApiTopicResponseDto> deleteTopicById(@ApiParam(value = "Topic identifier", required = true) @PathVariable("identifier") String identifier) {
        ApiTopicResponseDto topic = topicService.deleteTopic(identifier);
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiTopicResponseDto> getTopicById(@ApiParam(value = "Topic identifier", required = true) @PathVariable("identifier") String identifier) {
        ApiTopicResponseDto topic = topicService.getTopic(identifier);
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ApiTopicResponseDto>> getTopics() {
        List <ApiTopicResponseDto> topics = topicService.getAllTopics();
        return new ResponseEntity<>(topics,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiTopicResponseDto> postTopic(@ApiParam(value = "request body create a new topic"  )  @Valid @RequestBody(required = false) ApiTopicRequestDto apiTopicRequestDto) {
        ApiTopicResponseDto topic = topicService.createTopic(apiTopicRequestDto);
        return new ResponseEntity<>(topic,HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<ApiTopicResponseDto> putTopic(@ApiParam(value = "Topic identifier",required=true) @PathVariable("identifier") String identifier,@ApiParam(value = "admin info"  )  @Valid @RequestBody(required = false) ApiUpdateTopicRequestDto apiUpdateTopicRequestDto) {
        ApiTopicResponseDto topic = topicService.updateTopic(identifier,apiUpdateTopicRequestDto);
        return new ResponseEntity<>(topic,HttpStatus.OK);
    }
}



