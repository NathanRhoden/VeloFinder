import { Routes, Route, Outlet, Link } from "react-router-dom";
import MapBoxContainer from "../Map/MapBoxContainer";
import LandingMap from "../Map/LandingMap";
import "../Map/MapBoxContainer.css"
import { useState ,useEffect } from "react";
import "../layout/Layout.css";
import { request } from "../../helpers/axios_request";

export default function LayoutComponent() {

  const [rideData, setRideData] = useState<any>();

  const fetchCreatedRidesData = () => {
    request(
      'GET', 'create-ride/cluster', {}
    )
    .then(res => setRideData(res.data))
    .catch(err => console.error(err));

  }

  
  useEffect(() => { 
    fetchCreatedRidesData();
  },[])
  
  

   
  return (
    <div>
      <div className="main">
        <LandingMap />
      </div>
    </div>
  );
}
