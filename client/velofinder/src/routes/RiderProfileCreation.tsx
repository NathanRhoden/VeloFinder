import { FormEventHandler, useState } from "react"



export default function RiderProfileCreation() {

    type RiderFormInputs = {
        firstname: string,
        lastname: string, 
        dob: string, 
        experience: string
        
    }

    const [name, setName] = useState("");
    
    // typing on RIGHT hand side of =
   const handleChange = (e: React.FormEvent<HTMLInputElement>): void => {
    //setName(e.target.value);
  };
    
    return (
        <div>
            <h1> RIDER CREATION FORM</h1>
            <input type="text" value={name} onChange={handleChange} />
        </div>
    )
}