import { gpx } from "@tmcw/togeojson";
import { DOMParser } from "@xmldom/xmldom";

export default function toGeoJson(gpxFile) {
  const source = `<xml xmlns="a">
        <child>test</child>
        <child/>
    </xml>`;
    
    
    //GPX FILE NEEDS TO BE AN XML DOM
    const dom = new xmldom.DOMParser().parseFromString(gpxFile, "utf8");
    const converted = gpx(dom)
    console.log(converted);
   

}
