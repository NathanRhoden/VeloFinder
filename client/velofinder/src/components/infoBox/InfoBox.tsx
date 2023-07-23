import "../infoBox/InfoBox.css"

export default function InfoBox(props: any) {
    
    console.log(props.ride);

    return (
        <div className="infobox-body">
            <h5>{props.ride.eventName}</h5>
            <h5> Distance : {props.ride.distance}</h5>
            <h5>Rider experience : {props.ride.experience}</h5>
            <h5>Start Date : {props.ride.startDate}</h5>
            <h5> Start Time : {props.ride.startTime}</h5>
        </div>
    )
}