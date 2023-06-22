import { gpx } from "@tmcw/togeojson";
import { DOMParser } from "@xmldom/xmldom";


export default function convertGpxFile(data : string) {
    const DOMDATA = new DOMParser().parseFromString(data, "utf8");
    const converted = gpx(DOMDATA);

    return converted.features[0].geometry;
}