import { FormEvent, useState } from "react";
import LoginError from "../../types/LoginError";
import MessageBox from "../messagebox/MessageBox";

export default function LoginFormComponent( failure : LoginError) {

  console.log(failure.error);

  return (
    <div className="form-body">
      <h1>LOGIN</h1>
      <form className="form" action="http://localhost:8080/login" method="post">
        <label>
          username:
          <input type="text" name="username" />
        </label>
        
        <label>
          password:
          <input type="password" name="password" />
        </label>
        <input type="submit" value="Submit" />
      </form>
      {failure.error && <MessageBox text={"Username or password is Invalid"} />}
    </div>
  );
}
