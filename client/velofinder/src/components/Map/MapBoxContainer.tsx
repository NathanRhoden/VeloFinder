// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import { useRef, useEffect, useState} from "react";
import MAPBOXTOKEN from "../../keys/Token";

mapboxgl.accessToken = MAPBOXTOKEN;

const MapBoxContainer = (props : any) => {
  const [lat, setLat] = useState(0);
  const [lng, setLng] = useState(0);
  const [zoom, setZoom] = useState(5);

  const [initalised, setInitalised] = useState(false);
  const [convertedData, setConvertedData] = useState<any>("");
  const [dataUploaded , setDataUploaded] = useState(false);

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
      
    if (map.current && !dataUploaded) return;
  
    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/mapbox/streets-v12",
      center: [-0.118092, 51.509865],
      zoom: zoom,
    });
    
    if (dataUploaded) {
      map.current.on("load", () => {
        map.current.addSource("route", {
          'type': "geojson",
          'data': {
            'type': "Feature",
            'properties': {},
            'geometry': {
              'type': 'LineString',
              'coordinates': convertedData.coordinates,
            }
          },
        });
        map.current.addLayer({
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
    };

  }, [convertedData]);
  
  
  return (
    <div>
      <div ref={mapContainer} className="map-container"></div>
    </div>
  );
};

export default MapBoxContainer;
