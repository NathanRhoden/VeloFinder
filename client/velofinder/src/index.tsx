import React from "react";
import ReactDOM from "react-dom/client";
import { AuthProvider } from "react-auth-kit";
import "./index.css";
import "bootstrap/dist/css/bootstrap.min.css";

import {
  BrowserRouter,
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";

import App from "./App";

/*
const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
  },
  {
    path: "/createride",
    element: <CreateRide />,
  },
  {
    path: "/test",
    element: <Test />,
  },
  {
    path: "/createprofile",
    element: <RiderProfileCreation />,
  },
  {
    path: "/login",
    element: <LoginForm error={false} />,
  },
  {
    path: "/loginerror",
    element: <LoginForm error={true} />,
  },
  {
    path: "/ridercreate",
    element: <RiderProfileCreation />,
  },
  {
    path: "/signup",
    element: <AccountCreation />,
  },
]);
*/

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <AuthProvider
    authType={"localstorage"}
    authName={"_auth"}
    cookieDomain={window.location.hostname}
    cookieSecure={window.location.protocol === "https:"}
  >
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </AuthProvider>
);
