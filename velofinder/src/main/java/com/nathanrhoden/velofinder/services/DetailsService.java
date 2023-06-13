package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.DTO.CredentialsDto;
import com.nathanrhoden.velofinder.services.entities.rider.Details;
import com.nathanrhoden.velofinder.exceptions.AppException;
import com.nathanrhoden.velofinder.mapper.RiderMapper;
import com.nathanrhoden.velofinder.repository.DetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;


@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final DetailsRepository detailsRepository;
    private final RiderMapper riderMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = detailsRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void saveUserDetails(Details details){
         if(detailsRepository.findByUsername(details.getUsername()) != null){
             System.out.println("USER NAME ALREADY EXISTS!");
         }
         else{
             detailsRepository.save(details);
         }
    }


    public CredentialsDto register(CredentialsDto credentialsDto){

        Details optionalDetails = detailsRepository.findByUsername(credentialsDto.getUsername());

        if(optionalDetails != null){
            throw new AppException("Username already exists" , HttpStatus.BAD_REQUEST);
        }

        Details details = riderMapper.signUpToDetails(credentialsDto);

        details.setPassword(passwordEncoder.encode(CharBuffer.wrap(credentialsDto.getPassword())));

        return credentialsDto;

    }
}
