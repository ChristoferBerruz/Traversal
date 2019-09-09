import java.util.*;
import java.io.FileReader;

public class Program {
    public static void main(String[] args) {
        String fname = "test.txt";
        EdgeList edgeList = getEdgeList(fname);
        Traversal traversal = new Traversal(edgeList);
        ArrayList<Integer> result = traversal.dfs(3);
        System.out.println(result);
        traversal.clear();
        ArrayList<Integer> result1 = traversal.bfs(3);
        System.out.println(result1);
    }

    /**
     * Generates an EdgeList given a file name
     * @param fname is in the following format:
     *              n_nodes
     *              node_0 edge_0 ... edge_j
     *              .
     *              .
     *              .
     *              node_(n_nodes - 1) edge_0 ... edge_m
     * @return EdgeList
     */
    public static EdgeList getEdgeList(String fname){
        Scanner in = new Scanner(System.in);
        try {
            in = new Scanner(new FileReader(fname));
        } catch(Exception e){
            System.out.println(e);
        }
        int n_nodes = Integer.parseInt(in.nextLine().trim()); // First line guaranteed to be n_nodes
        EdgeList edgeList = new EdgeList(n_nodes);
        for(int i = 0; i < n_nodes; i++){
            String line = in.nextLine();
            String[] tokens = line.split(" ");
            processLine(tokens, edgeList);
        }
        return edgeList;
    }

    /**
     * Processes lines of the text file and tries to modify the EdgeList
     * @param tokens array of strings from a line of text
     * @param edgeList edgeList intended to be updated
     */
    public static void processLine(String[] tokens, EdgeList edgeList) {
        if (tokens.length > 0) {
            int node = Integer.parseInt(tokens[0]);
            int n_edges = tokens.length - 1; // Guaranteed to be greater or equal to 0
            if (n_edges > 0) { //There exists edges
                for (int j = 0; j < n_edges; j++) {
                    try {
                        edgeList.addEdge(node, Integer.parseInt(tokens[j + 1]));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}

