import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class AdjacencyList{
    public HashMap<Integer, NetworkNode>[] nodeList;
    public NetworkNode[] nodes;
    public int distance;
    public AdjacencyList(NetworkNode[] nodes, int distance){
        this.distance = distance;
        this.nodes = nodes;
        this.nodeList = new HashMap[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodeList[nodes[i].id] = new HashMap<>();
        }
        for ( int i = 0; i < nodes.length; i++) {
            int nodeID=i;
            for (int j = 0; j < nodes.length; j++) {
                if(i!=j && getDistance(nodes[i],nodes[j])<distance){
                    nodeList[nodeID].put(nodes[j].id,nodes[j]);
                }
            }
        }
    }
    public ArrayList<LLNode> DFS(){
        HashMap<Integer, NetworkNode> visited = new HashMap<>();
        ArrayList<LLNode> solution = new ArrayList<>();
        for(int i = 0; i < nodeList.length; i++){
            LLNode linkedListHead = null;
            LLNode tail = null;
            if(!visited.containsKey(i)){
                visited.put(i, nodeList[i].get(i));
                linkedListHead = new LLNode(nodes[i]);
                tail = linkedListHead;
                tail = DFSHelper(i, visited, tail);
                solution.add(linkedListHead);
            }
        
        }
        return solution;
    }
    public ArrayList<LLNode> BFS(){
        HashMap<Integer, NetworkNode> visited = new HashMap<>();
        ArrayList<LLNode> solution = new ArrayList<>();
        for(int i=0; i<nodeList.length; i++){
            if(visited.containsKey(i)){
                continue;
            }
            LLNode linkedListHead = new LLNode(nodes[i]);
            LLNode tail = linkedListHead;
            Collection<NetworkNode> currentRow = nodeList[i].values();
            visited.put(i, nodes[i]);
            tail = BFSHelper(currentRow, visited, tail);
            solution.add(linkedListHead);
        }
        return solution;
    }
    private LLNode BFSHelper(Collection<NetworkNode> currentRow,HashMap<Integer, NetworkNode> visited, LLNode tail){
        ArrayList<HashMap<Integer, NetworkNode>> nextRows = new ArrayList<>();
        for(NetworkNode n : currentRow){//search immediate neighbors
            if(!visited.containsKey(n.id)){
                visited.put(n.id, n);
                nextRows.add(nodeList[n.id]);
                tail.next = new LLNode(n);
                tail = tail.next;
            }
        }
        for(HashMap<Integer, NetworkNode> n : nextRows){ //search neighbors of neighbors
            Collection<NetworkNode> nextRowList = n.values();
            tail = BFSHelper(nextRowList, visited, tail);
        }
        return tail;
    }
    private LLNode DFSHelper(int index,HashMap<Integer, NetworkNode> visited, LLNode tail){
        for(NetworkNode n : nodeList[index].values()){
            if(!visited.containsKey(n.id)){
                visited.put(n.id, n);
                tail.next = new LLNode(n);
                tail = DFSHelper(n.id, visited, tail.next);
            }
        }
        return tail;
    }
    public void printSolution(ArrayList<LLNode> solution){
        if(solution.size()!=1){
            System.out.println("Graph is not fully connected");
        }else{
            System.out.println("Graph is fully connected");
        }
        System.out.println("There are "+solution.size()+" components");

        for(int i=0; i < solution.size(); i++){
            LLNode current = solution.get(i);
            while(current!=null){
                System.out.print(current.value + " -> ");
                current = current.next;
            }
            System.out.println();
        }
    }
    public void printAdjacencyList(){
        for (int i = 0; i < nodeList.length; i++) {
            System.out.print(i + ": ");
            for (NetworkNode n : nodeList[i].values()) {
                System.out.print(n.id + " ");
            }
            System.out.println();
        }
    }
    private static double getDistance(NetworkNode n1, NetworkNode n2){
        return Math.sqrt(Math.pow(n1.x-n2.x,2) + Math.pow(n1.y-n2.y,2));
    }

}