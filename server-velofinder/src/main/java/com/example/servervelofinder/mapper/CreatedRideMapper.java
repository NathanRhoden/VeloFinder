package com.example.servervelofinder.mapper;

import com.example.servervelofinder.DTO.CreatedRideDTO;
import com.example.servervelofinder.entities.CreatedRide;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreatedRideMapper {

    CreatedRideDTO toCreatedRideDTO(CreatedRide createdRide);


    void updateCreatedRideList(CreatedRideDTO createdRideDTO , @MappingTarget CreatedRide createdRide);

}
