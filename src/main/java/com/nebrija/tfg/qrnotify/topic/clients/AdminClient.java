package com.nebrija.tfg.qrnotify.topic.clients;

import com.nebrija.tfg.qrnotify.topic.config.FeignClientConfiguration;
import com.nebrija.tfg.qrnotify.topic.models.AdminResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "qrnotify-admin", url = "http://localhost:8081" , configuration = FeignClientConfiguration.class)
public interface AdminClient {

    @GetMapping("/nebrija/qrnotify-admin/user")
    List<AdminResponseDTO> getAdmin();

}
