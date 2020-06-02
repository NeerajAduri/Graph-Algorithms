/**
 * 
 */
package networks_graphs;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author neeraj
 *
 */
public class GraphGenerator {

    private static int TotalVertices = 5000;
    private static int DegreeOfUndirectedGraph = 6;
    private static int Percentage = 20;

    public static Graph denseGraphGenerator (int TotalVertices) {
        Graph graph = new Graph(TotalVertices);
        Random randomGenerator = new Random();
        //Generate Vertices in Undirected Dense Graph
        int count = 0;
        for (int i = 0; i < TotalVertices; i++) {
            for (int j = i + 1; j < TotalVertices; j++) {
                int randomProbability = randomGenerator.nextInt(100) + 1;
                int randomWeight = randomGenerator.nextInt(100) + 1;
                if (randomProbability <= Percentage) {
                    graph.newEdge(i, j, randomWeight);
                    count++;
                }
            }
        }
        System.out.println("Dense Graph with " + count + " edges has been created");
        return graph;
    }

    public static Graph sparseGraphGenerator(int TotalVertices) {
        Graph graph = new Graph(TotalVertices);
        Random randomGenerator = new Random();
        int[] degree = new int[TotalVertices];
        for (int i = 0; i < TotalVertices; i++) {
            degree[i] = 0;
        }

        int numberOfEdge = 0;
        while (numberOfEdge < (TotalVertices * DegreeOfUndirectedGraph) / 2) {
            int i = randomGenerator.nextInt(TotalVertices);
            int j = randomGenerator.nextInt(TotalVertices);
            int weight = randomGenerator.nextInt(100) + 1;
            if (degree[i] < 6 && degree[j] < 6 && i != j) {
                Edge edge = new Edge(i ,j, weight);
                if (!graph.adj[i].contains(edge)) {
                    graph.newEdge(i, j ,weight);
                    degree[i]++;
                    degree[j]++;
                    numberOfEdge++;
                }
            }
        }

        System.out.println("Sparse Graph with " + numberOfEdge + " edges has been created");
        return graph;
    }

    public static void main(String[] args) {
        Graph graph = sparseGraphGenerator(TotalVertices);
        Graph denseGraph = denseGraphGenerator(TotalVertices);
    }
}

