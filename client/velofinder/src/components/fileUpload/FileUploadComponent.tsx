import { useState, useEffect } from "react";
import { requestMultiPart } from "../../helpers/axios_request";

export default function FileUploadComponent({id} : any) {
  const [file, setFile] = useState("");
  const [fileData, setFileData] = useState("");

  const handleFileChange = (e: any) => {
    if (e.target.files) {
      setFile(e.target.files[0]);
    }
  };

  const handleUploadClick = () => {
    if (!file) {
      return;
    }
    else {
      let formData = new FormData();
      formData.append('file', file);
      formData.append('id',id.toString());
      
      requestMultiPart(
        'POST',
        '/create-ride/gpx',
        formData,
      ).then((response) => { console.log(response)})
      
      
      
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
