// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import { useRef, useEffect, useState} from "react";
import MAPBOXTOKEN from "../../keys/Token";

mapboxgl.accessToken = MAPBOXTOKEN;

export default function LandingMap() {
    
    const [zoom, setZoom] = useState(5);
    const [createdRideList , setCreatedRideList ] = useState([]);

    const mapContainer = useRef<any>(null);
    const map  = useRef <any> (null);
  

    useEffect(() => {

        if (map.current) return;

        map.current = new mapboxgl.Map({
            container: mapContainer.current,
            style: "mapbox://styles/mapbox/streets-v12",
            center: [-0.118092, 51.509865],
            zoom: zoom,
        });

        
    },[])
    
  

    return (<div>
        <div ref={mapContainer} className="map-container"></div>
    </div>)
}