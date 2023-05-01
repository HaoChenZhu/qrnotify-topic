package com.nebrija.tfg.qrnotify.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.nebrija.tfg.qrnotify.topic"})
@EnableFeignClients
public class QrnotifyTopicApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrnotifyTopicApplication.class, args);
	}

}
