package com.nathanrhoden.velofinder;

import com.nathanrhoden.velofinder.entities.GeoJsonRoute;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.hibernate.engine.jdbc.ClobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.nathanrhoden.velofinder.entities.rider.EXPERIENCE.*;

@SpringBootApplication
public class ServerApplication {


	RiderRepository riderRepository;
	CreatedRideRepository createdRideRepository;
	RouteRepository routeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

	@Autowired
	public ServerApplication(RiderRepository riderRepository, CreatedRideRepository createdRideRepository, RouteRepository routeRepository) {
		this.riderRepository = riderRepository;
		this.createdRideRepository = createdRideRepository;
		this.routeRepository = routeRepository;
	}

	@Bean
	CommandLineRunner commandLineRunner(RiderRepository riderRepository){
		return args -> {
			Rider testRider = new Rider("Nathan" , "Rhoden" , LocalDate.of(1994 , 11 , 20) ,
					INTERMEDIATE);

			Rider froome = new Rider("Chris", "Froome", LocalDate.of(1980 , 04, 11), ADVANCED);

			CreatedRide regents_park_chaingang = new CreatedRide(LocalDate.of(2023 , 01, 25) , LocalTime.of(15 , 00) ,
					60, "Regents Park chaingang" , ADVANCED , testRider);
			CreatedRide richmond = new CreatedRide(LocalDate.of(2023 , 01, 25) , LocalTime.of(12 , 30),
					60, "Richmond Park laps" , ADVANCED , testRider);
			CreatedRide london = new CreatedRide(LocalDate.of(2023 , 01, 25) , LocalTime.of(9, 30),
					25, "London Loop" , BEGINNER , testRider);


			riderRepository.save(testRider);
			riderRepository.save(froome);
			createdRideRepository.save(regents_park_chaingang);
			createdRideRepository.save(richmond);
			createdRideRepository.save(london);

			GeoJsonRoute londonRoute = new GeoJsonRoute(ClobProxy.generateProxy("This is the route"));

			routeRepository.save(londonRoute);


		};
	}
}
