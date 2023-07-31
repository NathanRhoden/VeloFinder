package com.example.servervelofinder;

import com.example.servervelofinder.entities.CreatedRide;
import com.example.servervelofinder.entities.Rider;
import com.example.servervelofinder.entities.RouteData;
import com.example.servervelofinder.exceptions.AppException;
import com.example.servervelofinder.repository.CreatedRideRepository;
import com.example.servervelofinder.repository.RiderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootApplication
@RequiredArgsConstructor
public class ServerVelofinderApplication {

	private final RiderRepository riderRepository;
	private final CreatedRideRepository createdRideRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerVelofinderApplication.class, args);
	}




}
