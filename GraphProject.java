import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GraphProject {
    public static void writeScript(NetworkNode[] nodes,int distance, int width, int height){ //this function writes the script file that plots the points with new values, couldn't statically import a json file so I injected the data instead
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("templateVisualGraph.js"));
            FileWriter fileWriter = new FileWriter("visualGraph.js");
            String injectValues = "const distance ="+distance+"\nconst width = "+width+"\nconst height = "+height+"\nconst list_of_nodes = [\n";
            for(NetworkNode n : nodes){
                injectValues+=("{val: " + n.id+ ", x: "+n.x +", y: " +n.y+"},\n");
            }
            injectValues+="];";
            fileWriter.write(injectValues);
            String line = fileReader.readLine();
            while(line!=null){
                fileWriter.write(line+"\n");
                line = fileReader.readLine();
            }
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int graphWidth = 1000;
        int graphHeight = 1000;
        int nodeCount = 400;
        int distance = 90;
        // Setting static values for the NetworkNode class
        NetworkNode.distance = distance;
        NetworkNode.graphWidth = graphWidth;
        NetworkNode.graphHeight = graphHeight;
        NetworkNode.minPackets = 1;
        NetworkNode.maxPackets = 100;

        //generating the nodes
        NetworkNode[] nodes = new NetworkNode[nodeCount];
        for(int i=0;i<nodeCount;i++){
            NetworkNode n = new NetworkNode(0,0,0);
            nodes[i] = n;
        }
        Matrix m = new Matrix(nodes,distance);
        AdjacencyList a = new AdjacencyList(nodes,distance);

        m.printSolution(m.BFS());
        a.printSolution(a.BFS());
        m.printSolution(m.DFS());
        a.printSolution(a.DFS());
        writeScript(nodes,distance,graphWidth,graphHeight);//this re-writes the js script file so we can plot the new values
        File htmlFile = new File("visualizeGraph.html");//here we open the html file to visualize the graph
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
