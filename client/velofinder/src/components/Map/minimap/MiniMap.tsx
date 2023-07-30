// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "mapbox-gl/dist/mapbox-gl.css";
import { useState, useEffect, useRef } from "react";


export default function MiniMap(props: any) {

    const [map, setMap] = useState<any>(null);

    const mapContainer = useRef<any>(null);

    
    useEffect(() => {
        const newMap = new mapboxgl.Map({
            container: mapContainer.current,
            style: "mapbox://styles/mapbox/streets-v12",
            center: [-0.118092, 51.509865],
            zoom: 9,
        });

        console.log(props.hasSelectedCluster)
        
        if (props.hasSelectedCluster && props.gpxData !== null) {

            
        
        console.log(props.gpxData)

        newMap.on("load", () => {
            newMap.addSource("route", {
            type: "geojson",
            data: {
                type: "Feature",
                properties: {},
                geometry: {
                type: "LineString",
                coordinates: props.gpxData.coordinates
                },
            },
            });
            newMap.addLayer({
            id: "route",
            type: "line",
            source: "route",
            layout: {
                "line-join": "round",
                "line-cap": "round",
            },
            paint: {
                "line-color": "#888",
                "line-width": 5,
            },
            });
        });
        const coordinates = props.gpxData.coordinates;
        const bounds = new mapboxgl.LngLatBounds(coordinates[0], coordinates[0]);
        for (const coords of coordinates) {
          bounds.extend(coords);
        }
        newMap.fitBounds(bounds, {
          padding: 10,
        });
                   
       
    }
    setMap(newMap)
      
    }, [props.gpxData])
    
    
    
    
    return (
        <div>
            <div ref={mapContainer} className="map-container"></div>
        </div>
    )
    
}