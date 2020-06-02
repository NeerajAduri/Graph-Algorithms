/**
 * 
 */
package networks_graphs;

import java.util.Random;

/**
 * @author neeraj
 *
 */
public class TestingPart4 {
    private static int TotalVertices = 5000;
    private static int NumberOfGraphs = 5;
    private static int NumberOfSourceToDestination = 5;

   public static void main(String[] args) {
        // five test cases
        for (int i = 1; i <= NumberOfGraphs; i++) {
            System.out.println("Test Case for Graphs: " + i);
            long startTime1 = System.currentTimeMillis();
            System.out.println("______________________________________________SPARSE GRAPH___________________________________________________________");
            
            Graph graph1 = GraphGenerator.sparseGraphGenerator(TotalVertices);
            long graphGeneratedTime1 = System.currentTimeMillis();
            System.out.println("Sparse Graph G is generated in a time: " + (graphGeneratedTime1 - startTime1));
            System.out.println(" ");
            for (int j = 1; j <= NumberOfSourceToDestination; j++) {
                System.out.println("Test Case for vertices: " + j);

                long startTimeNew = System.currentTimeMillis();
                Random randomGenerator = new Random();
                int source = randomGenerator.nextInt(TotalVertices);
                int destination = -1;
                while (true) {
                    destination = randomGenerator.nextInt(TotalVertices);
                    if (source != destination) break;
                }
                System.out.println("Source: " + source + " Destination: " + destination);
                int bw1 = MaxBWDijkstras.MaxBWFinder(graph1, source, destination);
                long MaxBWRunTime1 = System.currentTimeMillis();
                System.out.println("Dijkstrats without heap takes a time of : " + (MaxBWRunTime1 - startTimeNew));
                int bw2 =MaxBWDijkstrasHeapImplementation.MaxBWFinderWithHeap(graph1, source, destination);
                long MaxBWRunTime2 = System.currentTimeMillis();
                System.out.println("Dijkstrats with heap takes a time of    : " + (MaxBWRunTime2 - MaxBWRunTime1));
                int bw3 =MaxBWKruskals.MaxBWFinderKruskals(graph1, source, destination);
                long MaxBWRunTime3 = System.currentTimeMillis();
                System.out.println("Kruskals with heap takes a time of      : " + (MaxBWRunTime3 - MaxBWRunTime2));
                System.out.println("Max Bandwidth of this Graph is: " + bw1);
                System.out.println(" ");
                }
            
            System.out.println("______________________________________________DENSE GRAPH___________________________________________________________");
            
            long startTime2 = System.currentTimeMillis();
            Graph graph2 = GraphGenerator.denseGraphGenerator(TotalVertices);
            long graphGeneratedTime2 = System.currentTimeMillis();

            System.out.println("Dense undirected graph generation time: " + (graphGeneratedTime2 - startTime2));
            System.out.println(" ");
            for (int j = 1; j <= NumberOfSourceToDestination; j++) {
                // five pairs of sources and destinations
                System.out.println("Test Case for vertices: " + j);

                long startTimeNew = System.currentTimeMillis();
                Random randomGenerator = new Random();
                int source = randomGenerator.nextInt(TotalVertices);
                int destination = -1;
                while (true) {
                    destination = randomGenerator.nextInt(TotalVertices);
                  if (source != destination) break;
                }
                System.out.println("Source: " + source + " Destination: " + destination);
                int bw1 = MaxBWDijkstras.MaxBWFinder(graph2, source, destination);
                long MaxBWRunTime1 = System.currentTimeMillis();
                System.out.println("Dijkstrats without heap takes a time of : " + (MaxBWRunTime1 - startTimeNew));
                MaxBWDijkstrasHeapImplementation.MaxBWFinderWithHeap(graph2, source, destination);
                long MaxBWRunTime2 = System.currentTimeMillis();
                System.out.println("Dijkstrats with heap takes a time of    : " + (MaxBWRunTime2 - MaxBWRunTime1));
                MaxBWKruskals.MaxBWFinderKruskals(graph2, source, destination);
                long MaxBWRunTime3 = System.currentTimeMillis();
                System.out.println("Kruskals with heap takes a time of      : " + (MaxBWRunTime3 - MaxBWRunTime2));
                System.out.println(" ");
            }
        }
    }
}

