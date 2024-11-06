public class Path{
    public NetworkNode startPath;
    public NetworkNode endPath;
    public double weightOfPath;
    public LLNode<Edge> pathStart;
    public LLNode<Edge> pathEnd;
    public Path(Edge e){
        this.weightOfPath = e.weight;
        LLNode<Edge> temp = new LLNode<Edge>(e);
        this.pathStart = temp;
        this.pathEnd = temp;
    }
    public void addEdgeToPath(Edge e){
        LLNode<Edge> current = pathEnd;
        current.next = new LLNode<Edge>(e);  
        this.pathEnd = current.next;
    }
}