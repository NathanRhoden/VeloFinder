import { FormEvent, useState } from "react";
import LoginError from "../../../types/LoginError";
import MessageBox from "../../messagebox/MessageBox";
import { request, } from "../../../helpers/axios_request";


import { useSignIn , useAuthHeader } from "react-auth-kit";

export default function LoginFormComponent(failure: LoginError) {
  
  const [formData, setFormData] = useState({ username: "", password: "" })
  const [loginSuccess, setLoginSuccess] = useState<boolean>(false);
  

  const signIn = useSignIn();
  const authHeader = useAuthHeader();
 


  const onSubmit = (e: FormEvent) => {
    e.preventDefault();
    
    console.log(formData);

    request(
      "POST",
      "/login",
      formData
    ).then((res) => {
      if (res.status === 200) {
        
        setLoginSuccess(true); 
        

       
        if (signIn(
          {
            token: res.data.token,
            expiresIn: 1000,
            tokenType: "Bearer ",
            authState: res.data.authUserState
          })) { }
      }
    })
      .catch((err) => { console.log(err); });
    }
      
  
    
  
  return (
    <div className="form-body">
      <h1>LOGIN</h1>
      {authHeader()}
      <form className="form" onSubmit={onSubmit}>
        <label>
          username:
          <input
            type={"text"}
            onChange={(e) =>
              setFormData({ ...formData, username: e.target.value })
            }
            name="username"
          />
        </label>

        <label>
          password:
          <input
            type={"password"}
            onChange={(e) =>
              setFormData({ ...formData, password: e.target.value })
            }
            name="password"
          />
        </label>
        <input type="submit" />
      </form>
      {failure.error && <MessageBox text={"Username or password is Invalid"} />}
    </div>
  );
}
