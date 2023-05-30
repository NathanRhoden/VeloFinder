/* eslint-disable react-hooks/rules-of-hooks */
import { useState, useEffect } from "react";
import { gpx } from "@tmcw/togeojson";
import { DOMParser } from "@xmldom/xmldom";

export default function GpxUploadForm({onUpload} : any ) {

    
    const [file, setFile] = useState("");
    const [fileData, setFileData] = useState("");
    const [dataUploaded, setDataUploaded] = useState(false);
    
    const handleFileChange = (e: any) => {
        if (e.target.files) {
          setFile(e.target.files[0]);
        }
    };
    
    const handleUploadClick = () => {
  
        if (!file) {
          return;
        }
        const data = new DOMParser().parseFromString(fileData, "utf8");
        const converted = gpx(data);
        onUpload(converted.features[0].geometry);
        //console.log(converted.features[0].geometry);
        
        setDataUploaded(true);
      
    
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