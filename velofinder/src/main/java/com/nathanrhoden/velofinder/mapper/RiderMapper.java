package com.nathanrhoden.velofinder.mapper;

import com.nathanrhoden.velofinder.DTO.CredentialsDto;
import com.nathanrhoden.velofinder.DTO.RiderDTO;
import com.nathanrhoden.velofinder.DTO.RiderProfileDTO;
import com.nathanrhoden.velofinder.services.entities.rider.Details;
import com.nathanrhoden.velofinder.services.entities.rider.Rider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RiderMapper {

    RiderDTO toRiderDto(Rider rider);

    Rider riderProfileToRider(RiderProfileDTO riderProfileDTO);

    Details signUpToDetails(CredentialsDto credentialsDto);

}
