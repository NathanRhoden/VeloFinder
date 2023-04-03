/* eslint-disable react-hooks/rules-of-hooks */
import GpxUploadForm from "../components/gpxUploadForm/GpxUploadForm";
import MapOptions from "../types/MapOptions";
import Map from "../components/Map/Map";
import {useState , useEffect } from 'react';

export default function Test(){
    
    const [dataUploaded , setDataUploaded] = useState(false);
    const [hasData , setHasData] = useState(false);
    const [convertedData , setConvertedData] = useState(null);
    
    const options = {
        lat : -0.118092,
        lng : 51.509865 , 
        zoom :5
    }

    useEffect(() => {
        console.log(convertedData);
        if(convertedData){
            setHasData(true);
        }
        
    } , [convertedData])

    return(
        <div>
            <Map routeData={convertedData} hasData={hasData} />
            <GpxUploadForm onUpload={setConvertedData}/>
        </div>
    )
}