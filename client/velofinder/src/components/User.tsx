import { request } from "../helpers/axios_request";
import { useEffect } from "react";

import { useState } from 'react';


export default function User() {
  
  const [data, setData] = useState<any>();

  console.log(data);

  const userData = async () => {
    let user = await request("GET", "/rider", {})
      .then(res => setData(res.data))
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    userData();
  } , [])

  return (
    <div>
      {data != undefined && <div>Hello {data.firstName}</div>}
    </div>
  )
}
