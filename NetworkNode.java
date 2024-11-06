public class NetworkNode{ //network node
    public int k; 
    public int x;
    public int y;
    public int id;
    public int packets;
    public static int packetSizeInBits = 3200;
    public static int graphWidth;
    public static int graphHeight;
    public static int distance;
    public static int minPackets;
    public static int maxPackets;
    public static int count=0;
    public NetworkNode(int x, int y,int id){
        this.x = (int)(Math.random()*graphWidth);
        this.y = (int)(Math.random()*graphHeight);
        this.packets = (int)(Math.random()*(maxPackets-minPackets)) + minPackets;
        this.id = count++;
    }
    public String toString(){
        return "("+x + " " + y+")";
    }
    public static double transmissionEnergyInNJ(NetworkNode startNode, NetworkNode endNode){ //returns -1 if the distance is past the distance
        double distance = Math.sqrt(Math.pow(endNode.x-startNode.x,2)+Math.pow(endNode.y-startNode.y,2));//meters
        if(distance>NetworkNode.distance){
            return -1; //invalid node comparison, distance out of range
        }
        int payload = startNode.packets*NetworkNode.packetSizeInBits; //bits
        int electric = 100; //nJ/bit
        double amp  = .1; //nJ/bit/m^2
        double transmissionEnergyInPetajoules = (electric * payload) + amp * payload * Math.pow(distance,2);
        return transmissionEnergyInPetajoules;
    }
    public double receivingEnergyNJ(NetworkNode node){
        int electric = 100; //nJ/bit
        int payload = node.packets*NetworkNode.packetSizeInBits; //bits
        return payload*electric;
    }
    public static double weightOfEdgeInPetajoules(NetworkNode start, NetworkNode end){
        if(start.id == end.id){ //same node no energy used when transmitting to itself
            return 0;
        }
        return transmissionEnergyInNJ(start,end) + end.receivingEnergyNJ(start);
    }
}
