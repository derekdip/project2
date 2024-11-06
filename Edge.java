public class Edge {
    public NetworkNode start;
    public NetworkNode end;
    public double weight;
    public Edge(NetworkNode start, NetworkNode end){
        this.start = start;
        this.end = end;
        this.weight = NetworkNode.weightOfEdgeInPetajoules(start,end);
    }
}
