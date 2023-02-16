package com.nathanrhoden.velofinder;

import com.nathanrhoden.velofinder.entities.RouteData;
import com.nathanrhoden.velofinder.entities.createdrides.CreatedRide;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
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
			Rider rider =  new Rider("N" , "R" , LocalDate.now()  , BEGINNER);
			RouteData routeData = new RouteData("Data of the route");
			CreatedRide createdRide = new CreatedRide(LocalDate.now(), LocalTime.now()
				, 10 , "Test event" , BEGINNER ,rider , routeData
			);

			riderRepository.save(rider);
			createdRideRepository.save(createdRide);
			routeRepository.save(routeData);
		};
	}
}
