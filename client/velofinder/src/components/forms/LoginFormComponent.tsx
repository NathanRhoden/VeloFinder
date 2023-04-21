import { FormEvent, useState } from "react";

export default function LoginFormComponent() {
    
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');

    const handleChange = (e : any) => {
        setUsername(e.target.value);
        console.log(username);
        
    }



  return (
    <div>
      <form action='http://localhost:8080/login' method="post">
        <label>
          username:
          <input onChange={handleChange} type="text" name="username" />
        </label>
        <label>
          password:
          <input type="password" name="password" />
        </label>
        <input type="submit" value="Submit" />
      </form>
    </div>
  );
}
