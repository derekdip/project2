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
    private static double transmissionEnergyInPetajoules(NetworkNode startNode, NetworkNode endNode){ //returns -1 if the distance is past the distance
        double distance = Math.sqrt(Math.pow(endNode.x-startNode.x,2)+Math.pow(endNode.y-startNode.y,2));//meters
        if(distance>NetworkNode.distance){
            return -1; //invalid node comparison, distance out of range
        }
        int payload = startNode.packets*NetworkNode.packetSizeInBits; //bits
        int electric = 100; //nJ/bit
        electric = electric*1000;//pJ/bit
        int amp  = 100; //pJ/bit/m^2
        double transmissionEnergyInPetajoules = (electric * payload) + amp * payload * Math.pow(distance,2);
        return transmissionEnergyInPetajoules;
    }
    private double receivingEnergyPetajoules(NetworkNode node){
        int electric = 100; //nJ/bit
        int payload = node.packets*NetworkNode.packetSizeInBits; //bits
        electric = electric*1000;//pJ/bit
        return payload*electric;
    }
    public static double weightOfEdgeInPetajoules(NetworkNode start, NetworkNode end){
        return transmissionEnergyInPetajoules(start,end) + end.receivingEnergyPetajoules(start);
    }
}
