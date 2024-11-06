import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dykstra {
    public double sum = 0;
    public PriorityQueue<Edge> pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
        public int compare(Edge e1, Edge e2){
            return (int) (e1.weight - e2.weight);
        }
    });
    public Path searchForPath(Collection<NetworkNode> networkGroup,HashMap<Integer, NetworkNode>[] networkGroups, NetworkNode start, NetworkNode end){
        HashMap<NetworkNode, Path> completedPath = new HashMap<>();
        HashMap<NetworkNode, Boolean> visited = new HashMap<>();
        Edge startingEdge = new Edge(start, start);
        pq.add(startingEdge); //add the edge as the start of the path
        visited.put(start, true);
        completedPath.put(start, new Path(startingEdge)); //instead of backtracking to figure out the path we store the path sequentially and update it as we go
        while(!pq.isEmpty()){
            Edge currentEdge = pq.poll(); //pop the smallest edge
            for(NetworkNode n : networkGroups[currentEdge.end.id].values()){ //the neighbors of the current node
                // if(visited.containsKey(n)){
                //     //if we have already visited this node then we need to compare the costs of the two paths
                //     Path currentPath = completedPath.get(n); //the path we have already stored
                //     Path newPath = completedPath.get(currentEdge.end); //the path we are currently considering
                //     //compare the two path weights and update the path if the new path is shorter
                //     if(currentPath.weightOfPath > newPath.weightOfPath + currentEdge.weight){
                //         currentPath.weightOfPath = newPath.weightOfPath + currentEdge.weight;
                //         currentPath.pathEnd.next = new LLNode<Edge>(currentEdge);
                //         currentPath.pathEnd = currentPath.pathEnd.next;
                //     }
                //     continue;
                // }
                if(end.id == n.id){ //if we have reached the end we can return the path
                    Path newPath = completedPath.get(currentEdge.end);
                    newPath.weightOfPath += currentEdge.weight;
                    newPath.pathEnd.next = new LLNode<Edge>(new Edge(currentEdge.end, n));
                    newPath.pathEnd = newPath.pathEnd.next;
                    completedPath.put(end, newPath);
                    break;
                }
                if(visited.get(n)!=null){ //if nodes aren't visited we add a new edge to the pq
                    Edge e = new Edge(currentEdge.end, n);
                    visited.put(n, true);
                    Path newPath = completedPath.get(currentEdge.end);
                    newPath.weightOfPath += currentEdge.weight;
                    newPath.pathEnd.next = new LLNode<Edge>(new Edge(currentEdge.end, n));
                    newPath.pathEnd = newPath.pathEnd.next;
                    completedPath.put(n, newPath);
                    pq.add(e);
                }
                else{//if the node is already visited we need to compare the two paths
                    Edge e = new Edge(currentEdge.end, n);
                    visited.put(n, true);
                    Path newPath = completedPath.get(currentEdge.end);
                    newPath.weightOfPath += currentEdge.weight;
                    newPath.pathEnd.next = new LLNode<Edge>(new Edge(currentEdge.end, n));
                    newPath.pathEnd = newPath.pathEnd.next;
                    completedPath.put(n, newPath);
                    pq.add(e);
                }
            }
        }
        return completedPath.get(end);//there is only one optimal path to the 'end' so we can just return it
    }
    public Dykstra(HashMap<Integer, NetworkNode>[] networkGroups, NetworkNode[] nodes){ //sorry for the redundancy of nodes and networkGroups, I would have to refactor a lot of code to shorten this and might lead to more bugs
        for (int i = 0; i < networkGroups.length; i++) { //for each group in the whole network
            NetworkNode rendezvousPoint =nodes[i]; //the rendezvous point is random kinda, its random on the graph but not random in the group its the one with the smallest id in the group
            Collection<NetworkNode> networkGroup = networkGroups[i].values(); //get the nodes in the current group
            for (NetworkNode node : networkGroup) { // add up the weights of the paths from each node to the rendezvous point
                NetworkNode target = node;
                Path optimalPath = searchForPath(networkGroup,networkGroups, target, rendezvousPoint);
                sum+=optimalPath.weightOfPath;
            }
            System.out.println("The total energy used by the group is: "+sum);
        }
    
    }
}
