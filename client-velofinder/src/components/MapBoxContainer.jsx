import MAPBOXTOKEN from "../keys/Token.js";
// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "!mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import { useRef, useEffect, useState } from "react";

mapboxgl.accessToken = MAPBOXTOKEN;

const MapBoxContainer = (props) => {
  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [zoom, setZoom] = useState(6);

  const [initalised, setInitalised] = useState(false);

  const getUserLocation = () => {
    async function success(pos) {
      const crd = await pos.coords;

      setLat(crd.latitude);
      setLng(crd.longitude);
    }
    //TODO: This must be able to catch errors // user did not  give permission to access this location
    navigator.geolocation.getCurrentPosition(success);
  };

  const mapContainer = useRef(null);
  const map = useRef(null);

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
