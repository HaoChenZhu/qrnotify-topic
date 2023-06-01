package com.nebrija.tfg.qrnotify.topic.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminResponseDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("created_date")
    private String createdDate;

    @JsonProperty("modificated_date")
    private String modificatiedDate;

    @JsonProperty("deleted_date")
    private String deletedDate;
}
