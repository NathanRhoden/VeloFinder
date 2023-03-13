import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Root from './routes/Root';
import CreateRide from './routes/CreateRide';
import MapBoxContainer from './components/Map/MapBoxContainer';
import GpxUploadForm from './components/gpxUploadForm/GpxUploadForm';

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
    path: "/form",
    element: <GpxUploadForm />
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
