import MapBoxContainer from "../components/Map/MapBoxContainer"
import FileUploadForm from "../components/fileUploadForm/FileUploadForm"

export default function NewRide() {
    
    return (
        <div>
            UPLOAD RIDE AND SHOW GPX ROUTE
            <MapBoxContainer />
            <FileUploadForm />
        </div>
    )

}