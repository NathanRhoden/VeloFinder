import { FormEvent, useState } from "react";
import { request, setAuthHeader } from "../../../helpers/axios_request";

import '../../forms/ridercreationform/RiderCreationForm.css'

export default function RiderCreationForm() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    username: "",
    password: "",
    experience: ""
  });

  const onSubmit = (e: FormEvent) => {
    e.preventDefault();

    console.log(formData);

    request("POST", "/register", formData)
      .then((response) => {
        setAuthHeader(response.data.token)
      }).catch((error) => { 
        setAuthHeader('null');
      })
  };

  return (
    <div className="form-body">
      <form className="form" onSubmit={onSubmit}>
        <label>FirstName : </label>
        <input
          type="text"
          name="firstName"
          onChange={(e) =>
            setFormData({ ...formData, firstName: e.target.value })
          }
        ></input>
        <label>LastName : </label>
        <input
          type="text"
          name="lastName"
          onChange={(e) =>
            setFormData({ ...formData, lastName: e.target.value })
          }
        ></input>
        <label>Username : </label>
        <input
          type="text"
          name="username"
          onChange={(e) =>
            setFormData({ ...formData, username: e.target.value })
          }
        ></input>
        <label>Password : </label>
        <input
          type="password"
          name="password"
          onChange={(e) =>
            setFormData({ ...formData, password: e.target.value })
          }
        ></input>
        <label>Experience:</label>
        <label>Ride Experience Level : </label>
        <select name="experience" onChange={(e) =>
          setFormData({ ...formData, experience: e.target.value })}
        >
          <option value="BEGINNER">BEGINNER</option>
          <option value="INTERMEDIATE">INTERMEDIATE</option>
          <option value="ADVANCED">ADVANCED</option>
        </select>
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}
