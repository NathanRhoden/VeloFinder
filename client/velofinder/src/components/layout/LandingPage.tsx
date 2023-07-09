import LandingMap from "../Map/LandingMap";
import "../Map/MapBoxContainer.css";
import { useState, useEffect } from "react";
import Cluster from "../../types/RideDataCluster";
import "../layout/landingPage.css";
import StartingPoint from "../../types/StartingPoint";
import axios from "axios";

export default function LandingPage() {
  const [clusterData, setClusterData] = useState<any>({});
  const [isLoading, setIsLoading] = useState<boolean>(true);

  const clusterDataTransformed: any = {
    features: [],
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/create-ride/cluster")
      .then((response) => {
        response.data.forEach((element: StartingPoint) => {
          let cluster: Cluster = {
            type: "Feature",
            properties: {
              id: element.id,
              eventName: element.eventName,
            },
            geometry: {
              coordinates: [element.lng, element.lat],
              type: "Point",
            },
          };

          clusterDataTransformed.features.push(cluster);
        });
      })
      .finally(() => {
        setClusterData(clusterDataTransformed);
        setIsLoading(false);
      });
  }, []);

  if (isLoading) {
    return <div>LOADING ...</div>;
  }

  return (
    <div className="body">
      <div className="full-screen-div ">
        <LandingMap clusterData={clusterData} />
      </div>
    </div>
  );
}
