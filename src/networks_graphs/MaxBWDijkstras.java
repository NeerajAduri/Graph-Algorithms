/**
 * 
 */
package networks_graphs;

import java.util.ArrayList;


/**
 * @author neeraj
 *
 */
public class MaxBWDijkstras {
    public static int[] status;
    public static int[] dad;
    public static int[] weight;

    public static int INTREE = 2;
    public static int FRINGE = 1;
    public static int UNSEEN = 0;

    public static int MaxBWFinder(Graph graph, int source, int destination) {
        status = new int[graph.vertices()];
        dad = new int[graph.vertices()];
        weight = new int[graph.vertices()];

        for (int i = 0; i < graph.vertices(); i++) {
            status[i] = UNSEEN;
            weight[i] = Integer.MAX_VALUE;
        }

        //Update info of source
        status[source] = INTREE;
        dad[source] = -1;
        weight[source] = 0;
        //Update vertices adjacent to source
        ArrayList<Edge> verticesToSource = graph.getAdj()[source];
        for (Edge edge : verticesToSource) {
            int w = edge.diffVertex(source);
            status[w] = FRINGE;
            dad[w] = source;
            weight[w] = edge.getWeight();
        }

        //Dijsktra algorithm to find destination
        int count = 0;
        while (status[destination] != INTREE) {
            count++;
            //pick a fringe v with max weight[v], that is maxIndex
            int maxBandwidth = Integer.MIN_VALUE, maxIndex = -1;
            for (int i = 0; i < graph.vertices(); i++) {
                if (status[i] == FRINGE) {
                    if (weight[i] > maxBandwidth) {
                        maxBandwidth = weight[i];
                        maxIndex = i;
                    }
                }
            }
            status[maxIndex] = INTREE;

            ArrayList<Edge> verticesToV = graph.getAdj()[maxIndex];
            for (Edge edge : verticesToV) {
                int w = edge.diffVertex(maxIndex);
                if (status[w] == UNSEEN) {
                    dad[w] = maxIndex;
                    status[w] = FRINGE;
                    weight[w] = Math.min(weight[maxIndex], edge.getWeight());
                    //System.out.println(weight[w]+"  US  "+weight[maxIndex]+"  "+ edge.getWeight()+" "+maxIndex);
                     
                } else if(status[w] == FRINGE && weight[w] < Math.min(weight[maxIndex], edge.getWeight())) {
                    dad[w] = maxIndex;
                    weight[w] = Math.min(weight[maxIndex], edge.getWeight());
                    //System.out.println(weight[w]+" FR "+weight[maxIndex]+"  "+ edge.getWeight()+" "+maxIndex);
                }
            }
        }
        return weight[destination];
    }

    public static void main(String[] args) {
        int source = 0, destination = 2000;
        Graph graph = GraphGenerator.sparseGraphGenerator(5000);
        long startTime = System.currentTimeMillis();
        int maxSparse = MaxBWFinder(graph, source, destination);
        System.out.println("Maximum BW for this Sparse Graph is:" + maxSparse);
        long endTime = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime - startTime));
        System.out.println();

        
        Graph graph2 = GraphGenerator.denseGraphGenerator(5000);
        long startTime2 = System.currentTimeMillis();
        int maxSparse2 = MaxBWFinder(graph2, source, destination);
        System.out.println("Maximum BW for this Dense Graph is:" + maxSparse2);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Algorithm Running Time:"+ (endTime2 - startTime2));
    }
}

