package com.nathanrhoden.velofinder;

import com.nathanrhoden.velofinder.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class ServerApplication {

	@Autowired
	RiderRepository riderRepository;
	CreatedRideRepository createdRideRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

	public ServerApplication(RiderRepository riderRepository, CreatedRideRepository createdRideRepository) {
		this.riderRepository = riderRepository;
		this.createdRideRepository = createdRideRepository;
	}

	@Bean
	CommandLineRunner commandLineRunner(RiderRepository riderRepository){
		return args -> {
			Rider testRider = new Rider("Nathan" , "Rhoden" , LocalDate.of(1994 , 11 , 20) ,
					EXPERIENCE.INTERMEDIATE);

			Rider froome = new Rider("Chris", "Froome", LocalDate.of(1980 , 04, 11), EXPERIENCE.ADVANCED);

			CreatedRide regents_park_chaingang = new CreatedRide(LocalDate.of(2023 , 01, 25) , LocalTime.of(15 , 00) ,
					60, "Regents Park chaingang" , EXPERIENCE.ADVANCED , testRider);
			CreatedRide richmond = new CreatedRide(LocalDate.of(2023 , 01, 25) , LocalTime.of(12 , 30),
					60, "Richmond Park laps" , EXPERIENCE.ADVANCED , testRider);


			riderRepository.save(testRider);
			riderRepository.save(froome);
			createdRideRepository.save(regents_park_chaingang);
			createdRideRepository.save(richmond);

		};
	}
}
