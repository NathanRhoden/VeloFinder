import MapBoxContainer from "../components/Map/MapBoxContainer";

export default function Root() {
    const fetchData = async () => {
        fetch('http://localhost:8080/api/v1/createdride/all')
            .then(data => data.json())
            .then(res => console.log(res));
    }
    fetchData();
    
    return (
        <div>
            <MapBoxContainer />
        </div>
    )
}