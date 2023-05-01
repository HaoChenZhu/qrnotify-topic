package com.nebrija.tfg.qrnotify.topic.dao.mongodb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public class AuditableEntityListener {

    @Field("created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Field("modificated_date")
    @LastModifiedDate
    private LocalDateTime modificatedDate;

    @Field("deleted_date")
    private LocalDateTime deletedDate;

    @Field("created_by")
    @CreatedBy
    private String createdBy;
}
