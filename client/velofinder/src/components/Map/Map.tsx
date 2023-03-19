// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import { useState, useEffect , useRef } from "react";

import MAPBOXTOKEN from "../../keys/Token";

export default function Map({routeData , hasData} : any){

    mapboxgl.accessToken = MAPBOXTOKEN;

    const [lat, setLat] = useState(0);
    const [lng, setLng] = useState(0);
    const [zoom, setZoom] = useState(9);
    const [map , setMap] = useState<any>(null);
    const [dataLoaded , setDataLoaded] = useState(false);

    const getUserLocation = () => {
        async function success(pos : any) {
          const crd = await pos.coords;
            
          console.log('LOCATION');
          
          setLat(crd.latitude);
          setLng(crd.longitude);
        }
        //TODO: This must be able to catch errors // user did not  give permission to access this location
        navigator.geolocation.getCurrentPosition(success);
      };
    
    const mapContainer = useRef<any>(null);

    useEffect(() => {
        
        console.log(routeData);

        const newMap  = new mapboxgl.Map({
          container: mapContainer.current,
          style: "mapbox://styles/mapbox/streets-v12",
          center: [-0.118092, 51.509865],
          zoom: zoom,
        });        
        
        if (hasData) {
          newMap.on("load", () => {
            newMap.addSource("route", {
              'type': "geojson",
              'data': {
                'type': "Feature",
                'properties': {},
                'geometry': {
                  'type': 'LineString',
                  'coordinates': routeData.coordinates
                }
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
            })
              ;
          })

          const coordinates = routeData.coordinates;
          const bounds = new mapboxgl.LngLatBounds(
            coordinates[0],
            coordinates[0]
          )
          for(const coords of coordinates) {
            bounds.extend(coords);
        }
         newMap.fitBounds( bounds , {
            padding : 20
         })
          
        };

        setMap(newMap);
        
        
        
      }, [hasData]);

      return (
        <div>
          <div ref={mapContainer} className="map-container"></div>
          <button onClick={() => setDataLoaded(true)}>ShowData</button>
        </div>
      );
      


}