import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    ArrayList<Integer> result = new ArrayList<>();
    int[] visited;
    EdgeList edgeList;
    public Traversal(EdgeList edgeList){
        visited = new int[edgeList.n_nodes];
        this.edgeList = edgeList;
    }
    public ArrayList<Integer> bfs(int vertex){
        Queue<Integer> queue = new LinkedList<>();
        result.add(vertex);
        visited[vertex] = 1;
        queue.add(vertex);
        while(!queue.isEmpty()){
            queue.remove(vertex);
            ArrayList neighbors = edgeList.nodes[vertex];
            for(int i = 0; i< neighbors.size(); i++){
                int currentNeighbor = (int)neighbors.get(i);
                if(!isVisited(currentNeighbor)){
                    result.add(currentNeighbor);
                    visited[currentNeighbor] = 1;
                    queue.add(currentNeighbor);
                }
            }
            if(!queue.isEmpty()){
                vertex = queue.peek();
            }
        }
        return result;
    }


    public ArrayList<Integer> dfs(int vertex){
        //Assumes edgelist is sorted.
        result.add(vertex);
        visited[vertex] = 1;
        for (int i = 0; i < edgeList.nodes[vertex].size(); i++) {
            int closetNeighbor = edgeList.nodes[vertex].get(i);
            if(!isVisited(closetNeighbor)){
                dfs(closetNeighbor);
            }

        }
        return result;
    }


    public boolean isVisited(int closestNeighbor){
        if(this.visited[closestNeighbor] == 1){
            return true;
        }
        return false;
    }


    public void clear(){
        this.result = new ArrayList<Integer>();
        this.visited = new int[visited.length];
    }
}



