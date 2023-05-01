package com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "topics")
@Data
@NoArgsConstructor
@SuperBuilder
public class Topic extends AuditableEntityListener {
    @MongoId
    private ObjectId id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("publish_name")
    private String publishName;

    @Field("destination")
    private Destination destination;

}
