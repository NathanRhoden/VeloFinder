// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import { useRef, useEffect, useState, ReactPropTypes } from "react";
import MAPBOXTOKEN from "../../keys/Token";

mapboxgl.accessToken = MAPBOXTOKEN;

const MapBoxContainer = (props : any) => {
  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [zoom, setZoom] = useState(5);

  const [initalised, setInitalised] = useState(false);

  const getUserLocation = () => {
    async function success(pos : any) {
      const crd = await pos.coords;

      setLat(crd.latitude);
      setLng(crd.longitude);
    }
    //TODO: This must be able to catch errors // user did not  give permission to access this location
    navigator.geolocation.getCurrentPosition(success);
  };

  const mapContainer = useRef<any>(null);
  const map  = useRef <any> (null);

  useEffect(() => {
    getUserLocation();

    if (map.current) return;

    if (lat !== 0 && lng !== 0) {
      setInitalised(true);
    }

    if (initalised) {
      map.current = new mapboxgl.Map({
        container: mapContainer.current,
        style: "mapbox://styles/mapbox/streets-v12",
        center: [lng, lat],
        zoom: zoom,
      });
    }
  }, [lat, lng, initalised, zoom]);

  return (
    <div>
      <div ref={mapContainer} className="map-container"></div>
    </div>
  );
};

export default MapBoxContainer;
