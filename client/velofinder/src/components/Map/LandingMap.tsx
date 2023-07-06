// eslint-disable-next-line import/no-webpack-loader-syntax
import mapboxgl from "mapbox-gl";
import "./MapBoxContainer.css";
import "mapbox-gl/dist/mapbox-gl.css";
import Cluster from "../../types/RideDataCluster";
import { useRef, useEffect, useState } from "react";
import MAPBOXTOKEN from "../../keys/Token";

mapboxgl.accessToken = MAPBOXTOKEN;

export default function LandingMap(props: any) {
  const [zoom, setZoom] = useState(5);
  const [createdRideList, setCreatedRideList] = useState([]);

  const mapContainer = useRef<any>(null);
  const map = useRef<any>(null);

  useEffect(() => {
    if (map.current) return;

    map.current = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/mapbox/streets-v12",
      center: [-0.118092, 51.509865],
      zoom: zoom,
    });

    map.current.on("load", () => {
      map.current.addSource("Cluster data", {
        type: "geojson",
        data: props.clusterData,
        cluster: true,
        clusterMaxZoom: 14,
        clusterRadius: 50,
      });

      map.current.addLayer({
        id: "clusters",
        type: "circle",
        source: "Cluster data",
        filter: ["has", "point_count"],
        paint: {
          "circle-color": [
            "step",
            ["get", "point_count"],
            "#51bbd6",
            100,
            "#f1f075",
            750,
            "#f28cb1",
          ],
          "circle-radius": [
            "step",
            ["get", "point_count"],
            20,
            100,
            30,
            750,
            40,
          ],
        },
      });
      map.current.addLayer({
        id: "cluster-count",
        type: "symbol",
        source: "Cluster data",
        filter: ["has", "point_count"],
        layout: {
          "text-field": ["get", "point_count_abbreviated"],
          "text-font": ["DIN Offc Pro Medium", "Arial Unicode MS Bold"],
          "text-size": 11,
        },
      });
      map.current.addLayer({
        id: "unclustered-point",
        type: "circle",
        source: "Cluster data",
        filter: ["!", ["has", "point_count"]],
        paint: {
          "circle-color": "#11b4da",
          "circle-radius": 7,
          "circle-stroke-width": 1,
          "circle-stroke-color": "#fff",
        },
      });
      
      // inspect a cluster on click
      map.current.on("click", "clusters", (e: any) => {
        const features = map.current.queryRenderedFeatures(e.point, {
          layers: ["clusters"],
        });
        const clusterId = features[0].properties.cluster_id;
        map.current
          .getSource("Cluster data")
          .getClusterExpansionZoom(clusterId, (err: any, zoom: number) => {
            if (err) return;

            map.current.easeTo({
              center: features[0].geometry.coordinates,
              zoom: zoom,
            });
          });
      });
       
      map.current.on("click", "unclustered-point", (e: any) => {
        const coordinates = e.features[0].geometry.coordinates.slice();
        const eventName = e.features[0].properties.eventName;
        
  

        // Ensure that if the map is zoomed out such that
        // multiple copies of the feature are visible, the
        // popup appears over the copy being pointed to.
        while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
          coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
        }

        new mapboxgl.Popup()
          .setLngLat(coordinates)
          .setHTML(`${eventName}`)
          .addTo(map.current);
      });

      map.current.on("mouseenter", "clusters", () => {
        map.current.getCanvas().style.cursor = "pointer";
      });
      map.current.on("mouseleave", "clusters", () => {
        map.current.getCanvas().style.cursor = "";
      });
    });
  }, []);

  return (
    <div>
      <div ref={mapContainer} className="map-container"></div>
    </div>
  );
}
