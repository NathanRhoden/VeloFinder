import MAPBOXTOKEN from "../keys/Token.js";
// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "!mapbox-gl";
import './MapBoxContainer.css'
import 'mapbox-gl/dist/mapbox-gl.css';
import { useRef, useEffect, useState } from "react";

mapboxgl.accessToken = MAPBOXTOKEN;

const MapBoxContainer = () => {
  
  const mapContainer = useRef(null);
  const map = useRef(null);

  const [lng, setLng] = useState(-122.486052);
  const [lat, setLat] = useState(37.830348);
  const [zoom, setZoom] = useState(14);

  useEffect(() => {
    if (map.current) return;

    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/mapbox/streets-v12",
      center: [lng, lat],
      zoom: zoom,
    });

  })



  return (
      <div ref={mapContainer} className="map-container"></div>
  );
};

export default MapBoxContainer;
