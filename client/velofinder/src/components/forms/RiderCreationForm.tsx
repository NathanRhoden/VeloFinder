import { FormEvent, useState } from "react";
import '../forms/RiderCreationForm.css'

export default function RiderCreationForm() {
  
  type RiderFormInputs = {
    firstname: string;
    lastname: string;
    dob: string;
    experience: string;
  };

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleChange = (e: any) => {
    setUsername(e.target.value);
    console.log(username);
  };

  return (
    <div className="form-body">
      <form className="form" action="http://localhost:8080/signup" method="post">
        <label>
          firstname:
          <input onChange={handleChange} type="text" name="firstname" />
        </label>
        <label>
          lastname:
          <input type="text" name="lastname" />
        </label>
        <label>
          D.O.B:
          <input type="date" name="dob" />
        </label>
        <label>Experience:</label>
        <select name="experience">
          <option value="BEGINNER">BEGINNER</option>
          <option value="INTERMEDIATE">INTERMEDIATE</option>
          <option value="ADVANCED">ADVANCED</option>
        </select>
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}
