package com.nathanrhoden.velofinder.DTO;

import com.nathanrhoden.velofinder.entities.routedata.RouteData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDataDTO {

    private byte[] routeData;

    public RouteDataDTO(byte[] routeData) {
        this.routeData = routeData;
    }

    public RouteDataDTO() {
    }

    public static RouteData toEntity(RouteDataDTO routeDataDTO){
        RouteData routeData = new RouteData();

        routeData.setRouteData(routeDataDTO.getRouteData());

        return routeData;
    }
}
