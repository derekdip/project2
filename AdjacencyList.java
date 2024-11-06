import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

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
    public ArrayList<List<NetworkNode>> getMST() {
        boolean[] inMST = new boolean[nodes.length]; //track each node exists inMST; initializes inMst with amt of nodes to false
        ArrayList<List<NetworkNode>> allMSTs = new ArrayList<>(); //ArrayList of List NetworkNodes; As long as there is another component then ArrayList grows by one
        //ArrayList<Double> cost = new ArrayList<>();
        //double costOfAllWeights = 0;

        for (int i = 0; i < nodes.length; i++) {
           
            if (inMST[i]) continue;	 // If the node is already in an MST, skip it

            PriorityQueue<NetworkNode> minHeap = new PriorityQueue<>(Comparator.comparingDouble(n -> NetworkNode.weightOfEdgeInPetajoules(n, n)));
            
            

            List<NetworkNode> mst = new ArrayList<>(); // MST for the current component
            minHeap.add(nodes[i]);
            
            while (!minHeap.isEmpty()) {
                NetworkNode currentNode = minHeap.poll();

                if (inMST[currentNode.id]) continue; // Skip if already in MST

                // Include the current node in the MST
                inMST[currentNode.id] = true;
                mst.add(currentNode);

                // Update the priority queue with the edges of the newly added node
                for (var entry : nodeList[currentNode.id].entrySet()) {
                    NetworkNode neighbor = entry.getValue();
                    double edgeWeight = NetworkNode.weightOfEdgeInPetajoules(currentNode, neighbor);
                   // costOfAllWeights += edgeWeight;
                    //cost.a
                    

                    if (!inMST[neighbor.id] && edgeWeight >= 0) {
                        minHeap.add(neighbor);
                    }
                }
            }

            // Add the MST for the current component to the list of all MSTs
            allMSTs.add(mst);
        }

        return allMSTs; // Return the list of MSTs for all components
    }
   

	public void printMST() 
    {
		ArrayList<List<NetworkNode>> mstComponents = getMST();
		for (int i = 0; i < mstComponents.size(); i++) {
		    System.out.println("MST for component " + (i + 1) + ":");
		    for (NetworkNode node : mstComponents.get(i)) {
		        System.out.print(node.id + " ");
		    }
		    System.out.println();
		}

    	
    	
    }

}