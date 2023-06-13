package com.nathanrhoden.velofinder;

import com.nathanrhoden.velofinder.services.entities.rider.Details;
import com.nathanrhoden.velofinder.services.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.DetailsRepository;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import com.nathanrhoden.velofinder.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

	}


