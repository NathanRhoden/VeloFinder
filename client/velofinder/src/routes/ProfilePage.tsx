import { useState, useEffect } from "react";
import Map from "../components/Map/Map";
import Accordion from "react-bootstrap/Accordion";
import { request } from "../helpers/axios_request";
import convertGpxFile from "../helpers/gpxConvert";
import "../routeStyle/profilePage.css";
import "../components/layout/landingPage.css";

export default function ProfilePage() {
  const [allUserRideData, setAllUserRideData] = useState<any[]>([]);
  const [selectedRideId, setSelectedRideId] = useState<number>();

  const [loadedGPX, setLoadedGPX] = useState<any>();
  const [hasFetchedData, setHasFetchedData] = useState(false);

  //ALL RIDE DATA FOR LOGGED IN USER
  const data = allUserRideData;

  //ACCORDION OF USERRIDE DATA SETS ID STATE WHEN ACCORDION ITEM IS CLICKED
  const listItems = data.map((ride) => (
    <Accordion.Item eventKey={ride.id}>
      <Accordion.Header
        onClick={() => {
          setSelectedRideId(ride.id);
        }}
      >
        {ride.eventName}
      </Accordion.Header>
      <Accordion.Body>
        {ride.experience} {ride.distance}
      </Accordion.Body>
    </Accordion.Item>
  ));

  useEffect(() => {
    if (allUserRideData.length === 0) {
      request("POST", "create-ride/user", {})
        .then((response) => {
          if (response.status === 200) {
            setAllUserRideData(response.data);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);

  //LOADING GPX DATA ON TO MAP
  useEffect(() => {
    if (selectedRideId != null) {
      setHasFetchedData(false);
      request("GET", `create-ride/gpx/${selectedRideId}`, {})
        .then((response) => {
          setLoadedGPX(convertGpxFile(response.data));
        })
        .then(() => setHasFetchedData(true))
        .catch((error) => {
          console.log(error);
        });
    }
  }, [selectedRideId]);

  return (
    <div className="body">
      <div  className="full-screen-div">
        <Map routeData={loadedGPX} hasData={hasFetchedData} />
      </div>
      <div className="info-box">
        <Accordion>
          <ul>{listItems}</ul>
        </Accordion>
      </div>
    </div>
  );
}
