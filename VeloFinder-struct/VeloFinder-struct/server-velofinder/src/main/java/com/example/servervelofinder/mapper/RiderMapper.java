package com.example.servervelofinder.mapper;

import com.example.servervelofinder.DTO.RiderDTO;
import com.example.servervelofinder.DTO.SignUpDto;
import com.example.servervelofinder.entities.Rider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RiderMapper {

    RiderDTO toRiderDTO (Rider rider);

    @Mapping(target = "token" , ignore = true)
    RiderDTO toRiderDTONoToken(Rider rider);

    @Mapping(target = "password" , ignore = true)
    Rider signUpDTOToRider(SignUpDto signUpDto);


}
