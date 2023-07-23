import LandingMap from "../Map/LandingMap";
import "../Map/MapBoxContainer.css";
import { useState, useEffect } from "react";
import Cluster from "../../types/RideDataCluster";
import "../layout/landingPage.css";
import StartingPoint from "../../types/StartingPoint";
import axios from "axios";
import InfoBox from "../infoBox/InfoBox";

export default function LandingPage() {
  const [clusterData, setClusterData] = useState<any>({});
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [selectedRide, setSelectedRide] = useState<any>('');
  const [rideData, setRideData] = useState();

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

  useEffect(() => { 
    if (selectedRide) {
      axios.get( `http://localhost:8080/create-ride/ride/${selectedRide}` )
        .then((response) => {setRideData(response.data)})
        
    }
  } ,[selectedRide ])

  if (isLoading) {
    return <div>LOADING ...</div>;
  }

  return (
    <div className="body">
      <div className="full-screen-div ">
        <LandingMap clusterData={clusterData} setSelectedRide={setSelectedRide} />
      </div>
      <div className="info-box">
        {rideData ? <InfoBox ride={rideData}  /> : <h1>Select GroupRide</h1> }
      </div>
    </div>
  );
}
