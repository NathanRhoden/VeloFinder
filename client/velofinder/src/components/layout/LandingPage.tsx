import LandingMap from "../Map/LandingMap";
import "../Map/MapBoxContainer.css"
import { useState ,useEffect } from "react";
import "../layout/Layout.css";
import { request } from "../../helpers/axios_request";
import Cluster from "../../types/RideDataCluster";
import StartingPoint from "../../types/StartingPoint";
import axios from "axios";


export default function LandingPage() {

  const [rideData, setRideData] = useState<Cluster[]>([]);
  const [clusterData, setClusterData] = useState<any>({});
  const [isLoading, setIsLoading] = useState<boolean>(true); 
  
  const clusterDataTransformed: any = {
    features : [],
  }

  const fetchClusters =  async () : Promise<any>   => {
    request(
      'GET', 'create-ride/cluster', {}
    )
      .then(response => {
        response.data.forEach((element: StartingPoint) => {
          
          let cluster: Cluster = {
            "type": "Feature",
            "properties": {
              "id": element.id,
              "eventName": element.eventName,

            },
            "geometry": {
              
              "coordinates": [element.lat, element.lng],
              "type": "Point",
              
            }
          }
          
          clusterDataTransformed.features.push(cluster);
          

        })
        
    
      })
  }

  useEffect(() => { 
    axios.get('http://localhost:8080/create-ride/cluster')
      .then((response) => { 
        response.data.forEach((element: StartingPoint) => {
          
          let cluster: Cluster = {
            "type": "Feature",
            "properties": {
              "id": element.id,
              "eventName": element.eventName,

            },
            "geometry": {
              
              "coordinates": [element.lng, element.lat],
              "type": "Point",
              
            }
          }
          
          clusterDataTransformed.features.push(cluster);
        })
      })
      .finally(() => { 
        setClusterData(clusterDataTransformed);
        setIsLoading(false);
        
      })
   
  }, []);

  

  const testData = 
        {
          features: [
            {
              "type": "Feature",
              "properties": {},
              "geometry": {
                "coordinates": ['-0.16381585768422724', '51.533729375466834'],
                "type": "Point"
              }
            },
          ],
        }
      ;
  
  
  
  if(isLoading) { 
    return (
      <div>LOADING ...</div>
    )
  }
  
   
  return (
    <div>
      <div className="main">
        <LandingMap clusterData={clusterData} />
      </div>
    </div>
  );
}
