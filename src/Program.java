import java.util.*;
import java.io.FileReader;

public class Program {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        int user_Choice = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Program 1: Graphs");
        String fname = "test.txt";
        EdgeList edgeList = getEdgeList(fname);
        Traversal traversal = new Traversal(edgeList);
        do {
            System.out.println("\n"
                    + "1. Read and Print the graph" + "\n"
                    + "2. DFS(Depth First Search) " + "\n"
                    + "3. BFS(Breadth First Search) " + "\n"
                    + "4. Exit\n");
            user_Choice = in.nextInt();
            switch (user_Choice) {
                case 1:
                    System.out.println(edgeList);
                    break;
                case 2:
                    System.out.println("Starting vertex: ");
                    int vertex = in.nextInt();
                    if (-1 < vertex && vertex < edgeList.n_nodes) {
                        ArrayList<Integer> result = traversal.dfs(vertex);
                        System.out.println(result);
                        traversal.clear();
                    } else {
                        System.out.println("Not a valid vertex!");
                    }
                    break;
                case 3:
                    System.out.println("Starting vertex: ");
                    int vertex1 = in.nextInt();
                    if (-1 < vertex1 && vertex1 < edgeList.n_nodes) {
                        ArrayList<Integer> result = traversal.bfs(vertex1);
                        System.out.println(result);
                        traversal.clear();
                    } else {
                        System.out.println("Not a valid vertex!");
                    }
                    break;
                case 4:
                    System.out.println("Thank You and have a great day!!!");
            }
        }while (user_Choice !=  4);
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
            try{
                Integer n = Integer.parseInt(tokens[0]);
                processLine(i, tokens, edgeList);
            }catch (Exception e){

            }
        }
        return edgeList;
    }

    /**
     * Processes lines of the text file and tries to modify the EdgeList
     * @param tokens array of strings from a line of text
     * @param edgeList edgeList intended to be updated
     */
    public static void processLine(int i, String[] tokens, EdgeList edgeList) {
        if (tokens.length > 0) {
            int n_edges = tokens.length; // Guaranteed to be greater or equal to 0
            for (int j = 0; j < n_edges; j++) {
                try {
                    edgeList.addEdge(i, Integer.parseInt(tokens[j]));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}

