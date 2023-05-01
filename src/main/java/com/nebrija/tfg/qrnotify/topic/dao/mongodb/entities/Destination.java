package com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Destination {
    @Field("company")
    private String company;
    @Field("area")
    private String area;
    @Field("store")
    private String store;
}
