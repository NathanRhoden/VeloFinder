import React from "react";
import "./App.css";
import { Routes, Route, Outlet, Link } from "react-router-dom";
import { AuthProvider } from "react-auth-kit";
import LoginForm from "./routes/Login";
import RiderProfileCreation from "./routes/RiderProfileCreation";
import GroupRideCreationForm from "./components/forms/groupridecreationform/GroupRideCreationForm";
import LayoutComponent from "./components/layout/LandingPage";
import RideRoute from "./routes/RideRoute";
import TopNavBar from "./components/navbar/TopNavbar";
import ProfilePage from "./routes/ProfilePage";
import LandingPage from "./components/layout/LandingPage";

export default function App() {
  return (
    <div className="App">
      <AuthProvider authType={"localstorage"} authName={"_auth"}>
        <TopNavBar />
        <Routes>
          <Route path="/" element={<LandingPage />}></Route>
          <Route path="login" element={<LoginForm error={false} />} />
          <Route path="/ridercreate" element={<RiderProfileCreation />} />
          <Route path="/group" element={<GroupRideCreationForm />} />
          <Route path="/user" element={<ProfilePage />} />
          <Route path="/ride" element={<RideRoute />} />
        </Routes>
      </AuthProvider>
    </div>
  );
}
