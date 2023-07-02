import { useState, useEffect , useRef } from "react";
import { requestMultiPart } from "../../helpers/axios_request";
import { gpx } from "@tmcw/togeojson";
import { DOMParser } from "@xmldom/xmldom";
import { GeoJSONSource } from "mapbox-gl";

import { request } from "../../helpers/axios_request";

export default function FileUploadComponent({ id }: any) {
  const [file, setFile] = useState("");
  const [fileData, setFileData] = useState("");

  const createdRideId = useRef(id);

  const handleFileChange = (e: any) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleUploadClick = () => {
    if (!file) {
      return;
    } else {

      const givenData = new DOMParser().parseFromString(fileData, "utf8");
      const convertedData: any = gpx(givenData);

      let formData = new FormData();

      formData.append("file", file);
      formData.append("id", id.toString());
      formData.append("lat", convertedData.features[0].geometry.coordinates[0][1]);
      formData.append("lng", convertedData.features[0].geometry.coordinates[0][0]);
      

      requestMultiPart("POST", "/create-ride/gpx", formData).then(
        (response) => {
          console.log(response);
        }
      );
      

      //TO-DO ADD STARTING COORDINATES FOR MAP CLUSTER DATA

      const data = new DOMParser().parseFromString(fileData, "utf8");
      const converted: any = gpx(data);

      console.log(
        converted.features[0].geometry.coordinates[0][1] +
          " " +
          converted.features[0].geometry.coordinates[0][0]
      );
    }
  };
  useEffect(() => {
    let fileReader: any,
      isCancel = false;

    if (file) {
      fileReader = new FileReader();
      fileReader.onload = (e: any) => {
        const { result } = e.target;
        if (result && !isCancel) {
          setFileData(result);
        }
      };

      fileReader.readAsText(file);
    }

    return () => {
      isCancel = true;
      if (fileReader && fileReader.readyState === 1) {
        fileReader.abort();
      }
    };
  }, [file]);

  return (
    <div className="App">
      <h1>GPX FILE READER</h1>
      <input type="file" onChange={handleFileChange}></input>
      <button onClick={handleUploadClick}>Upload</button>
    </div>
  );
}
