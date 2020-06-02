/**
 * 
 */
package networks_graphs;

import java.util.ArrayList;

/**
 * @author neeraj
 *
 */
public class MaxBWDijkstrasHeapImplementation {
    public static int[] status;
    public static int[] dad;
    public static int[] bw;
    public static MaxHeap heap;

    public static int INTREE = 2;
    public static int FRINGE = 1;
    public static int UNSEEN = 0;

    public static int MaxBWFinderWithHeap(Graph graph, int source, int destination) {
        status = new int[graph.vertices()];
        dad = new int[graph.vertices()];
        bw = new int[graph.vertices()];
        heap = new MaxHeap(graph.vertices());

        for (int i = 0; i < graph.vertices(); i++) {
            status[i] = UNSEEN;
            bw[i] = Integer.MAX_VALUE;
        }

        //Update info of source
        status[source] = INTREE;
        dad[source] = -1;

        //Update vertices adjacent to source
        ArrayList<Edge> verticesToSource = graph.getAdj()[source];
        for (Edge edge : verticesToSource) {
            int w = edge.diffVertex(source);
            status[w] = FRINGE;
            dad[w] = source;
            bw[w] = edge.getWeight();
            heap.insert(w, bw[w]);
        }

        //Dijsktra algorithm to find destination
        int count = 0;
        while (status[destination] != INTREE) {
            int maxIndex = heap.maximum();

            status[maxIndex] = INTREE;
            heap.delete(1);

            ArrayList<Edge> verticesToV = graph.getAdj()[maxIndex];
            for (Edge edge : verticesToV) {
                int w = edge.diffVertex(maxIndex);
                if (status[w] == UNSEEN) {
                    dad[w] = maxIndex;
                    status[w] = FRINGE;
                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                    heap.insert(w, bw[w]);

                } else if(status[w] == FRINGE && bw[w] < Math.min(bw[maxIndex], edge.getWeight())) {
                    dad[w] = maxIndex;

                    int i = 1;
                    while (i <= heap.getheapSize()) {
                        if (heap.getIndex(i) == w) {
                            heap.delete(i);
                        }
                        i++;
                    }

                    bw[w] = Math.min(bw[maxIndex], edge.getWeight());
                    heap.insert(w, bw[w]);
                }
            }
        }
        return bw[destination];
    }

    public static void main(String[] args) {
        int source = 0, destination = 1000;

        Graph graph = GraphGenerator.sparseGraphGenerator(5000);
        long startTime = System.currentTimeMillis();
        int maxSparse = MaxBWFinderWithHeap(graph, source, destination);
        System.out.println("Maximum BW for this Sparse Graph is:" + maxSparse);
        long endTime = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime - startTime));
        System.out.println();

        
        Graph graph2 = GraphGenerator.denseGraphGenerator(5000);
        long startTime2 = System.currentTimeMillis();
        int maxSparse2 = MaxBWFinderWithHeap(graph2, source, destination);
        System.out.println("Maximum BW for this Dense Graph is:" + maxSparse2);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime2 - startTime2));
    }
}

