/**
 * 
 */
package networks_graphs;
import java.util.ArrayList;

/**
 * @author neeraj
 *
 */
public class Graph {

    private int vertices;
    private int edge;
    ArrayList<Edge>[] adj;

    public Graph(int vertices) {
        //Initialize an empty graph with vertices and 0 edges.
        this.vertices = vertices;
        this.edge = 0;
        adj = (ArrayList<Edge>[])new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public ArrayList<Edge>[] getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edge>[] adj) {
        this.adj = adj;
    }

    public boolean edgeExists (int u,int v) {
		for(Edge e: adj(u)) {
			if(e.getEnd()==v) {
				return true;
			}
		}
    	return false;
    	
    }
    
    public int vertices() {
        return vertices;
    }

    public int edge() {
        return edge;
    }

    public void newEdge(int start, int end, int weight) {
        Edge edge1 = new Edge(start, end, weight);
        adj[start].add(edge1);
        Edge edge2 = new Edge(start, end, weight);
        adj[end].add(edge2);
        edge += 2;
    }

    public Iterable<Edge> adj(int start) {
        return adj[start];
    }

    public static int degree(Graph G, int start) {
        int degree = 0;
        for (Edge edge : G.adj(start)) {
            degree++;
        }
        return degree;
    }
}
