package com.nathanrhoden.velofinder.services;

import com.nathanrhoden.velofinder.entities.rider.Details;
import com.nathanrhoden.velofinder.repository.DetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DetailsService implements UserDetailsService {

    private final DetailsRepository detailsRepository;

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
}
