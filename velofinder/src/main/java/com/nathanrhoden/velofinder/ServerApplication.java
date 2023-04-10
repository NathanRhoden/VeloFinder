package com.nathanrhoden.velofinder;

import com.nathanrhoden.velofinder.entities.rider.Details;
import com.nathanrhoden.velofinder.entities.rider.EXPERIENCE;
import com.nathanrhoden.velofinder.entities.rider.Rider;
import com.nathanrhoden.velofinder.repository.CreatedRideRepository;
import com.nathanrhoden.velofinder.repository.DetailsRepository;
import com.nathanrhoden.velofinder.repository.RiderRepository;
import com.nathanrhoden.velofinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class ServerApplication {


	RiderRepository riderRepository;
	CreatedRideRepository createdRideRepository;
	RouteRepository routeRepository;
	DetailsRepository detailsRepository;
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

	}

	@Autowired
	public ServerApplication(RiderRepository riderRepository, CreatedRideRepository createdRideRepository, RouteRepository routeRepository,
							 DetailsRepository detailsRepository, PasswordEncoder passwordEncoder) {
		this.riderRepository = riderRepository;
		this.createdRideRepository = createdRideRepository;
		this.routeRepository = routeRepository;
		this.detailsRepository = detailsRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	CommandLineRunner commandLineRunner(DetailsRepository detailsRepository){
		return args -> {

			var rider = new Rider("nate" , "rhoden", LocalDate.now(), EXPERIENCE.ADVANCED);
			var dets = new Details(1L , rider , "user", passwordEncoder.encode("pass"));



			detailsRepository.save(dets);
		};
	}

}
