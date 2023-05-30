import { FormEvent, useState, useEffect } from "react";
import { request } from "../../../helpers/axios_request";
import "../groupridecreationform/GroupRide.css"

export default function GroupRideCreationForm() {
  const [formData, setFormData] = useState({
    eventName: "",
    distance: "",
    experience: "",
    startDate: "",
    startTime: "",
  });

  const [convertedData, setConvertedData] = useState(null);
  
  
  const onSubmit = (e: FormEvent) => {
    e.preventDefault();

    console.log(formData);

    request("POST", "/create-ride", formData);
  };

  useEffect(() => {
    if (convertedData != null) {
      
    } 
   },[convertedData]);



  return (
    <div className="form-body">
      <form className="form" onSubmit={onSubmit}>
        <label>Event Name : </label>
        <input
          type="text"
          name="firstName"
          onChange={(e) =>
            setFormData({ ...formData, eventName: e.target.value })
          }
        ></input>
        <label>Start Time: </label>
        <input
          type="time"
          name="startTime"
          onChange={(e) =>
            setFormData({ ...formData, startTime: e.target.value })
          }
        ></input>
        <label>Ride Experience Level : </label>
        <select name="experience" onChange={(e) =>
          setFormData({ ...formData, experience: e.target.value })}
        >
          <option value="BEGINNER">BEGINNER</option>
          <option value="INTERMEDIATE">INTERMEDIATE</option>
          <option value="ADVANCED">ADVANCED</option>
        </select>
        <label>Start Date: </label>
        <input
          type="date"
          name="startDate"
          onChange={(e) =>
            setFormData({ ...formData, startDate: e.target.value })
          }
        ></input>
        <label>Distance:</label>
        <input
          type="password"
          name="startDate"
          onChange={(e) =>
            setFormData({ ...formData, distance: e.target.value })
          }
        ></input>
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}
