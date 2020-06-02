/**
 * 
 */
package networks_graphs;

import java.util.ArrayList;


/**
 * @author neeraj
 *
 */
public class MaxHeapKruskals {

    private static Edge[] maxHeap;
    private static int heapSize;

    public MaxHeapKruskals(int edgeNumber) {
        maxHeap = new Edge[edgeNumber];
        heapSize = 0;
    }

    public static int getheapSize() {
        return heapSize;
    }

    public static Edge[] getMaxHeap() {
        return maxHeap;
    }

    public static void insert(Edge edge) {
        heapSize++;
        maxHeap[heapSize] = edge;
        if(heapSize==1) {return;}
        int curent = heapSize;
        while (curent!=1 && (maxHeap[curent].getWeight() > maxHeap[curent/2].getWeight())) {
        	//if(curent==1) {break;}
            swap(curent, curent/2); 
            curent = curent/2;
        } 
    }

    public static Edge maximum() {
        return maxHeap[1];
    }

    public static void delete(int index) {
        maxHeap[index] = maxHeap[heapSize];
        maxHeap[heapSize] = null;
        heapSize--;
        heapify(index);
    }

    public static void heapify(int index) {
        int l = 2 * index;
        int r = 2 * index + 1;
        int largest = index;
        if (l <= heapSize && maxHeap[l].getWeight() > maxHeap[index].getWeight()) {
            largest = l;
        }
        if (r <= heapSize && maxHeap[r].getWeight() > maxHeap[largest].getWeight()) {
            largest = r;
        }
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    public static void swap(int pos1, int pos2) {
        Edge tempHeap = maxHeap[pos1];
        maxHeap[pos1] = maxHeap[pos2];
        maxHeap[pos2] = tempHeap;
    }

    public static void main(String[] args) {
        Graph graph = GraphGenerator.sparseGraphGenerator(5000);
        MaxHeapKruskals heap = new MaxHeapKruskals(graph.edge() + 1);
        for (int v = 0; v < graph.vertices(); v++) {
            ArrayList<Edge> edgeList = graph.getAdj()[v];
            for (Edge edge : edgeList) {
                heap.insert(edge);
            }
        }
        System.out.println("TEST heap number" + heap.getheapSize());
        for (int i = 0; i < 5; i++) {
            heap.delete(1);
        }
    }
}
