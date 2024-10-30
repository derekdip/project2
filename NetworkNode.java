public class NetworkNode{ //network node
    public int k; 
    public int x;
    public int y;
    public int id;
    public static int graphWidth;
    public static int graphHeight;
    public static int distance;
    public static int minPackets;
    public static int maxPackets;
    public static int count=0;
    public NetworkNode(int x, int y,int id){
        this.x = (int)(Math.random()*graphWidth);
        this.y = (int)(Math.random()*graphHeight);
        this.id = count++;
    }
    public String toString(){
        return "("+x + " " + y+")";
    }
    public double getWeight(NetworkNode node){ //returns -1 if the distance is past the distance
        double distance = Math.sqrt(Math.pow(this.x-node.x,2)+Math.pow(this.y-node.y,2));
        if(distance < distance){
            return -1;
        }
        //double transmissionEnergy = 
        //double receivingEnergy = 100 *k ; 
        return Math.sqrt(Math.pow(this.x-node.x,2)+Math.pow(this.y-node.y,2));
    }
}
