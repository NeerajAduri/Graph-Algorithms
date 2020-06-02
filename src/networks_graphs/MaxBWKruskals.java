/**
 * 
 */
package networks_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author neeraj
 *
 */
public class MaxBWKruskals {

    private static MaxHeapKruskals heap;
    private static int[] dad;
    private static int[] rank;
    private static Graph newGraph;
    private static int[] color;
    private static int[] dadBFS;
    private static int[] bw;

    private static int BLACK = 2;
    private static int GREY = 1;
    private static int WHITE = 0;

    private static void sortEdgesInDecreasingOrder(Graph graph) {
        heap = new MaxHeapKruskals(graph.edge() + 1);
        for (int v = 0; v < graph.vertices(); v++) {
            ArrayList<Edge> edgeList = graph.getAdj()[v];
            for (Edge edge : edgeList) {
                heap.insert(edge);
            }
        }
    }

    public static int find(int vertex) {
        int v = vertex;
        while (dad[v] != v) {
            v = dad[v];
        }
        return v;
    }

    public static void union(int r1, int r2) {
        if (rank[r1] > rank[r2]) {
            dad[r2] = r1;
        } else if (rank[r1] < rank[r2]) {
            dad[r1] = r2;
        } else {
            dad[r1] = r2;
            rank[r1]++;
        }
    }

    public static int MaxBWFinderKruskals(Graph graph, int source, int destination) {
        // Sort all edges in decreasing order first
        sortEdgesInDecreasingOrder(graph);
        dad = new int[graph.vertices()];
        rank = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            dad[i] = i;
            rank[i] = 1;
        }

        newGraph = new Graph(graph.vertices());
        for (int e = 0; e < graph.edge(); e++) {
            Edge edge = heap.maximum();
            int U = edge.getStart();
            int V = edge.getEnd();
            int R1 = find(U);
            int R2 = find(V);
            if (R1 != R2) {
                newGraph.newEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
                union(R1, R2);
            }
            heap.delete(1);
        }
        
        color = new int[newGraph.vertices()];
        dadBFS = new int[newGraph.vertices()];
        bw = new int[newGraph.vertices()];
        for (int v = 0; v < newGraph.vertices(); v++) {
            color[v] = WHITE;
            dadBFS[v] = -1;
            bw[v] = Integer.MAX_VALUE;
        }
        color[source] = GREY;
        dad[source] = -1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (color[destination] != BLACK && !queue.isEmpty()) {
            int u = queue.poll();
            ArrayList<Edge> uEdgeList = newGraph.getAdj()[u];
            for (Edge edge : uEdgeList) {
                int v = edge.diffVertex(u);
                if (color[v] == WHITE) {
                    color[v] = GREY;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                    dadBFS[v] = u;
                    queue.offer(v);
                } else if (color[v] == GREY && bw[v] < Math.min(bw[u], edge.getWeight())) {
                    dadBFS[v] = u;
                    bw[v] = Math.min(bw[u], edge.getWeight());
                }
            }
            color[u] = BLACK;
        }
        return bw[destination];
    }

    public static void main(String[] args) {
        int source = 0, destination = 1000;
        Graph graph = GraphGenerator.sparseGraphGenerator(5000);
        long startTime = System.currentTimeMillis();
        int maxSparse = MaxBWFinderKruskals(graph, source, destination);
        System.out.println("Maximum BW for this Sparse Graph is:" + maxSparse);
        long endTime = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime - startTime));
        System.out.println();
        
        Graph graph2 = GraphGenerator.denseGraphGenerator(5000);
        long startTime2 = System.currentTimeMillis();
        int maxSparse2 = MaxBWFinderKruskals(graph2, source, destination);
        System.out.println("Maximum BW for this Dense Graph is:" + maxSparse2);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime2 - startTime2));
    }
}
