interface Cluster {
  type: string;
  properties?: {
    eventName: string;
    id: number;
  };
  geometry: {
    coordinates: string[],
    type: string;
  };
}

export default Cluster;
