package com.nathanrhoden.velofinder.auth;

import com.nathanrhoden.velofinder.services.entities.rider.Details;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DetailsDTO {

    private String username;
    private String password;

    public static Details toEntity(String password , String username){

         Details d =  Details.builder()
                .username(username)
                .password(password)
                 .rider(new Rider())
                 .build();

         return d;

    }
}
