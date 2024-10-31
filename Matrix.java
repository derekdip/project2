import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Matrix{
    public NetworkNode[][] matrix;
    public NetworkNode[] nodes;
    public int distance;
    public Matrix( NetworkNode[] nodes, int distance){
        matrix = new NetworkNode[nodes.length][nodes.length];
        this.nodes = nodes;
        this.distance = distance;
        for(int i=0; i < nodes.length; i++){
            for(int j=0; j < nodes.length; j++){
                matrix[i][j] = null;
                if(i==j){
                    matrix[i][j] = nodes[i];
                }
            }
        }
        for(NetworkNode n : nodes){
            insert(n);
        }
    }
    public void insert(NetworkNode n){
        for(int i = 0; i < matrix.length; i++){
            if(nodes[i]!=null && getDistance(nodes[i],n)<distance){
                matrix[i][n.id] = n;
                matrix[n.id][i] = nodes[i];
               
            }
        }
    }
    public void printMatrix(){
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[i].length; j++){
                if(matrix[i][j]!=null){
                    System.out.printf("%3d ", matrix[i][j].id);
                }
                else{
                    System.out.printf("%3d ", 0);
                }
            }
            System.out.println();
        }
    }
    public ArrayList<LLNode> DFS(){
        HashMap<Integer, NetworkNode> visited = new HashMap<>();
        ArrayList<LLNode> solution = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            if(visited.containsKey(matrix[i][i].id)){
                continue;
            }
            visited.put(matrix[i][i].id, matrix[i][i]);
            LLNode linkedListHead = new LLNode(matrix[i][i].id);
            LLNode tail = linkedListHead;
            DFSHelper(matrix[i],visited,tail);
            solution.add(linkedListHead);
        }
        return solution;
    }
    private LLNode DFSHelper(NetworkNode[] row,HashMap<Integer, NetworkNode> visited, LLNode tail){
        for(int i = 0; i < row.length; i++){
            if(row[i]!=null && !visited.containsKey(row[i].id)){
                visited.put(row[i].id, row[i]);
                tail.next = new LLNode(row[i].id);
                tail = DFSHelper(matrix[row[i].id], visited, tail.next);
            }
        }
        return tail;
    }
    public ArrayList<LLNode> BFS(){ 
        HashMap<Integer, NetworkNode> visited = new HashMap<>();
        ArrayList<LLNode> solution = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++){
            if(visited.containsKey(matrix[i][i].id)){
                continue;
            }
            visited.put(matrix[i][i].id, matrix[i][i]);
            LLNode linkedListHead = new LLNode(matrix[i][i].id);
            LLNode tail = linkedListHead;
            BFSHelper(matrix[i],visited,tail);
            solution.add(linkedListHead);
        }
        return solution;
    }
    public LLNode BFSHelper(NetworkNode[] row,HashMap<Integer, NetworkNode> visited, LLNode tail){ //gotta love recursion
        ArrayList<Integer> foundNeighborIDs = new ArrayList<>();
        for(int i = 0; i < row.length; i++){
            if(row[i]!=null && !visited.containsKey(row[i].id)){
                visited.put(row[i].id, row[i]);
                tail.next = new LLNode(row[i].id);
                tail = tail.next;
                foundNeighborIDs.add(row[i].id);
            }
        }
        for(int id : foundNeighborIDs){
            tail = BFSHelper(matrix[id], visited, tail);
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
    private static double getDistance(NetworkNode n1, NetworkNode n2){
        Random rand = new Random();
        int data = rand.nextInt(101)
        return Math.sqrt(Math.pow(n1.x-n2.x,2) + Math.pow(n1.y-n2.y,2));
        //int sender = 100 nJ/bit * data + 100 pJ/bit/m^2 * data * transmission^2
        //int reciever = 100 nJ/bit * data 
        

    }
}
