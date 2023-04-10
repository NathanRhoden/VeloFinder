import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Root from './routes/Root';
import CreateRide from './routes/CreateRide';
import RiderProfileCreation from './routes/RiderProfileCreation';
import MapBoxContainer from './components/Map/MapBoxContainer';
import GpxUploadForm from './components/gpxUploadForm/GpxUploadForm';
import LoginForm from './routes/Login';
import Test from './routes/test';


import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";



const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />
  },
  {
    path: "/createride",
    element: <CreateRide />
  },
  {
    path: "/test",
    element: <Test />
  },
  {
    path: "/createprofile",
    element: <RiderProfileCreation />
  },
  {
    path: "/login",
    element: <LoginForm/>
  },





 
]);

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
     <RouterProvider router={router} />
  </React.StrictMode>
);
