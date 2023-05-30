import { useState } from "react";
import LoginFormComponent from "../loginform/LoginFormComponent";
import LoginError from "../../../types/LoginError";
import RiderCreationForm from "../ridercreationform/RiderCreationForm";
import { request } from "../../../helpers/axios_request";

export default function FormSelector() {
  const [shownForm, setShownForm] = useState<string>("");
  const [loggedin, setLoggedIn] = useState<boolean>(false);

  const currentState = {
    loggedin,
    shownForm,
  };

  console.log(currentState);

  const errorMessage: LoginError = { error: false };

  return (
    <div>
      <button
        style={{ margin: "10px" }}
        className="btn btn-primary"
        onClick={() => setShownForm("login")}
      >
        Login
      </button>
      <button
        style={{ margin: "10px" }}
        className="btn btn-primary"
        onClick={() => setShownForm("register")}
      >
        Register
      </button>
      <button
        style={{ margin: "10px" }}
        className="btn btn-primary"
        onClick={() => setShownForm("logout")}
      >
        Logout
      </button>

      <section>
        {shownForm === "login" && (
          <LoginFormComponent error={errorMessage.error} />
        )}
        {shownForm === "register" && <RiderCreationForm />}
        {shownForm === "logout" && (
          <LoginFormComponent error={errorMessage.error} />
        )}
      </section>
    </div>
  );
}
