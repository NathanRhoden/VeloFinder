import mapboxgl from "mapbox-gl"; // eslint-disable-line import/no-webpack-loader-syntax
import "mapbox-gl/dist/mapbox-gl.css";
import { useState , useEffect, useRef } from "react";
import { gpx } from "@tmcw/togeojson";
import { DOMParser } from "@xmldom/xmldom";

import MapBoxContainer from '../components/Map/MapBoxContainer'
import GpxUploadForm from "../components/forms/gpxUploadForm/GpxUploadForm";


export default function CreateRide() {
    
    const mapContainer = useRef<any>(null);
    const map = useRef<any>(null);
    const [zoom, setZoom] = useState(9);
  
    const [file, setFile] = useState("");
    const [fileData, setFileData] = useState("");
    const [convertedData, setConvertedData] = useState<any>("");
    const [dataUploaded , setDataUploaded] = useState(false);
  
    const handleFileChange = (e: any) => {
      if (e.target.files) {
        setFile(e.target.files[0]);
      }
    };
  
    const handleUploadClick = () => {
    
      if (!file) {
        return;
      }
      const data = new DOMParser().parseFromString(fileData, "utf8");
      const converted = gpx(data);
      setConvertedData(converted.features[0].geometry);
      console.log(converted.features[0].geometry);
      
      setDataUploaded(true);
  
    };
    
    useEffect(() => {
     
      let fileReader: any,
        isCancel = false;
  
      if (file) {
        fileReader = new FileReader();
        fileReader.onload = (e: any) => {
          const { result } = e.target;
          if (result && !isCancel) {
            setFileData(result);
          }
        };
  
        fileReader.readAsText(file);
      }
  
      return () => {
        isCancel = true;
        if (fileReader && fileReader.readyState === 1) {
          fileReader.abort();
        }
      };
    }, [file]);
    
  
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
      <div className="App">
        <div ref={mapContainer} className="map-container"></div>
        <h1>GPX FILE READER</h1>
        <input type="file" onChange={handleFileChange}></input>
        <button onClick={handleUploadClick}>Upload</button>
      </div>
    );
}
