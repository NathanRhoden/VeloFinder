import { useState, useEffect } from "react";
import Map from "../components/Map/Map";
import Accordion from "react-bootstrap/Accordion";
import { request } from "../helpers/axios_request";
import { type } from "os";

export default function ProfilePage() {
  const [routeData, setRouteData] = useState();
  const [dataLoaded, setDataLoaded] = useState(false);
  const [allUserRideData, setAllUserRideData] = useState<any[]>([]);
  const [selectedRideId, setSelectedRideId] = useState<number>();
  
  //ALL RIDE DATA FOR LOGGED IN USER
  const data = allUserRideData;

  console.log(data);
  console.log(selectedRideId);

  const listItems = data.map((ride) => (
    <Accordion.Item eventKey={ride.id}>
      <Accordion.Header onClick={() => { setSelectedRideId(ride.id) }}>{ride.eventName}</Accordion.Header>
      <Accordion.Body>
        {ride.experience} {ride.distance}
      </Accordion.Body>
    </Accordion.Item>
  ));

  useEffect(() => {
    request("GET", "create-ride/all", {})
      .then((response) => {
        if (response.status === 200) {
          setAllUserRideData(response.data);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  return (
    <div>
      <Map />
      <div>
        <Accordion >
          <ul>{listItems}</ul>
        </Accordion>
      </div>
    </div>
  );


}






