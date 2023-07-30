import "../infoBox/InfoBox.css"

export default function InfoBox(props: any) {
    
    console.log(props.ride);

    return (
        <div className="infobox-body">
            <h5>{props.ride.eventName}</h5>
            <h5> Distance : {props.ride.distance} miles</h5>
            <h5>Rider experience : {props.ride.experience}</h5>
            <h5>Start Date : {props.ride.startDate[2]}-{props.ride.startDate[1]}-{props.ride.startDate[0]}</h5>
            <h5> Start Time : {props.ride.startTime[0]}:{props.ride.startTime[1]}</h5>
        </div>
    )
}