package com.nebrija.tfg.qrnotify.topic.mappers;


import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Destination;
import com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities.Topic;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiDestination;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicRequestDto;
import com.nebrija.tfg.qrnotify.topic.model.api.ApiTopicResponseDto;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TopicMapper {

    @Mapping(expression = "java(toDto(topic.getDestination()))", target = "destinations")
    ApiTopicResponseDto toDto(Topic topic);

    Topic toEntity(ApiTopicRequestDto apiTopicRequestDto);

    List<ApiTopicResponseDto> fromEntitiesToDtos(List<Topic> topics);

    ApiDestination toDto(Destination destination);

    Destination map(ApiDestination apiDestination);

    default String map(ObjectId value) {
        if (value != null) {
            return value.toHexString();
        }
        return null;
    }

    default ObjectId map(String value) {
        if (value != null) {
            return new ObjectId(value);
        }
        return null;
    }
}
