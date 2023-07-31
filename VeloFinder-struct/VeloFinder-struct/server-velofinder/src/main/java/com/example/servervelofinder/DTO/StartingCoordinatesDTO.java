package com.example.servervelofinder.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StartingCoordinatesDTO {

    private Long id;
    private String lng;
    private String lat;
    private String eventName;

}
