package com.nathanrhoden.velofinder.auth;

import com.nathanrhoden.velofinder.entities.rider.Details;
import com.nathanrhoden.velofinder.services.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/signup")
public class DetailsController {

    private final DetailsService detailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public void createNewUserDetails(@RequestBody DetailsDTO details){

        String pass = passwordEncoder.encode(details.getPassword());
        detailsService.saveUserDetails(DetailsDTO.toEntity(pass , details.getUsername()));
    }


}
