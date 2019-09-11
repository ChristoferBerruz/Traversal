/**
 * This program is user input driven. We might develop the File Chooser implementation.
 * In the meantime, the file has to be in the current folder of the project.
 *
 * Please note that vertex start from 1 to n inclusive.
 */

import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Program {
    public static Scanner user;
    public static Scanner in;
    public static EdgeList edgeList;
    public static Traversal traversal;
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
        int user_Choice = 0;
        user = new Scanner(System.in);
        System.out.println("Program 1: Graphs");
        System.out.println("-----------------");
        System.out.print("Please enter a filename: ");
        String fname = user.nextLine().trim();
        edgeList = getEdgeList(fname);
        traversal = new Traversal(edgeList);
        do {
            System.out.println(getMenu());
            user_Choice = user.nextInt();
            switch (user_Choice) {
                case 1:
                    System.out.println(edgeList.toString());
                    break;
                case 2:
                    DFS();
                    break;
                case 3:
                    BFS();
                    break;
                case 4:
                    System.out.println("Thank You and have a great day!!!");
                    break;
            }
        }while (user_Choice !=  4);
    }

    /**
     * Generates an EdgeList given a file name
     * @param fname is in the following format:
     *              n_nodes
     *              edge_1 ... edge_j
     *              .
     *              .
     *              .
     *              edge_1 ... edge_m
     * @return EdgeList
     */
    public static EdgeList getEdgeList(String fname){
        in = new Scanner(System.in);
        try{
            in = new Scanner(new FileReader(fname));
        }catch(Exception e){
            System.out.println(e);
        }
        int n_nodes = Integer.parseInt(in.nextLine().trim()); // First line guaranteed to be n_nodes
        EdgeList edgeList = new EdgeList(n_nodes);
        for(int i = 1; i < n_nodes+1; i++){
            String line = in.nextLine();
            String[] tokens = line.split(" ");
            try{
                Integer.parseInt(tokens[0]); // This raises an exception when char is newline
                processLine(i, tokens, edgeList);
            }catch(Exception e){

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


    public static String getMenu(){
        String menu = "\n"
                + "1. Read and Print the graph" + "\n"
                + "2. DFS(Depth First Search) " + "\n"
                + "3. BFS(Breadth First Search) " + "\n"
                + "4. Exit\n";
        return menu;
    }

    public static void DFS(){
        System.out.println("Starting vertex: ");
        int vertex = user.nextInt();
        if (0 < vertex && vertex < edgeList.n_nodes) {
            ArrayList<Integer> result = traversal.dfs(vertex);
            System.out.println(result);
            traversal.clear();
        } else {
            System.out.println("Not a valid vertex!");
        }
    }

    public static void BFS(){
        System.out.println("Starting vertex: ");
        int vertex1 = user.nextInt();
        if (0 < vertex1 && vertex1 < edgeList.n_nodes) {
            ArrayList<Integer> result = traversal.bfs(vertex1);
            System.out.println(result);
            traversal.clear();
        } else {
            System.out.println("Not a valid vertex!");
        }
    }
}

