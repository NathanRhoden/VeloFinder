package com.example.servervelofinder.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RideDataDTO {

    private byte[] routeData;
    private int startingLat;
    private int startingLng;

}
