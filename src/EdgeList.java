import java.util.ArrayList;
import java.util.Collections;
public class EdgeList {
    ArrayList<Integer>[] nodes;
    int n_nodes;
    public EdgeList(int n_nodes){
        //Creating an array of ArrayLists
        this.nodes = new ArrayList[n_nodes];
        this.n_nodes = n_nodes;
        //Initializing each location
        for(int i = 0; i < n_nodes; i++){
            nodes[i] = new ArrayList<Integer>();
        }
    }
    public EdgeList(){
        this.nodes = new ArrayList[10];
        this.n_nodes = 10;
        for(int i = 0; i < n_nodes; i++){
            nodes[i] = new ArrayList<Integer>();
        }
    }

    /**
     * Adding edges to nodes in the EdgeList
     * @param node 0 <= node < n_nodes for valid indexing
     * @param edge 0<= edge < n_nodes for valid insertion
     * @throws Exception when either the node or the edge does not exist in the graph
     */
    public void addEdge(int node, Integer edge) throws Exception{
        if(node < this.n_nodes & edge < this.n_nodes){
            if(this.nodes[node].size() > 0){
                int n_elements = nodes[node].size();
                for(int i = 0; i < n_elements; i++){
                    if(edge < nodes[node].get(i)){
                        nodes[node].add(i,edge);
                    }else if(i == n_elements - 1){
                        nodes[node].add(edge);
                    }
                }
            }else {
                this.nodes[node].add(edge);
            }

        }
        else{
            throw new Exception("This node or edge does not exist in the graph");
        }
    }
    public String toString(){
        StringBuilder edgelist = new StringBuilder();
        for(int i = 0; i < n_nodes; i++){
            edgelist.append(i + " -> ");
            int n_edges = nodes[i].size();
            for(int j = 0; j < n_edges; j++){
                edgelist.append(nodes[i].get(j) + " ");
            }
            edgelist.append('\n');
        }
        return edgelist.toString();
    }
}

