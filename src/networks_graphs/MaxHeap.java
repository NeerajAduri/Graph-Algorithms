/**
 * 
 */
package networks_graphs;

/**
 * @author neeraj
 *
 */
public class MaxHeap {

    private static int[] vertices;
    private static int[] weight;
    private static int heapSize;

    public MaxHeap(int edgeNumber) {
        vertices = new int[edgeNumber + 1];
        weight = new int[edgeNumber + 1];
        heapSize = 0;
    }

    public static int getheapSize() {
        return heapSize;
    }

    public static int getIndex(int index) {
        return  vertices[index];
    }

    public static void insert(int vertex, int bw) {
        heapSize++;
        //System.out.println(heapSize);
        vertices[heapSize] = vertex;
        weight[heapSize] = bw;
        if(heapSize==1) {return;}
        int curent = heapSize;
        while (weight[curent] > weight[curent/2]) {
        	if(curent==1) {break;}
            swap(curent, curent/2); 
            curent = curent/2;
        } 
        
    }

    public static int maximum() {
        return vertices[1];
    }

    public static void delete(int index) {
        vertices[index] = vertices[heapSize];
        weight[index] = weight[heapSize];
        heapSize--;
        heapify(index);
    }

    public static void heapify(int k) {
    	//System.out.println("Heapify k weight:"+k);
        int l = 2 * k;
        int r = 2 * k + 1;
        int largest = k;
        if (l <= heapSize && weight[l] > weight[k]) {
            largest = l;
        }
        if (r <= heapSize && weight[r] > weight[largest]) {
            largest = r;
        }
        if (largest != k) {
            swap(largest, k);
            heapify(largest);
        }
    }

    
    public static void swap(int pos1, int pos2) {
        int temp1 = vertices[pos1];
        vertices[pos1] = vertices[pos2];
        vertices[pos2] = temp1;
        int temp2 = weight[pos1];
        weight[pos1] = weight[pos2];
        weight[pos2] = temp2;
    }

    public static void main(String[] args) {
        MaxHeap newHeap = new MaxHeap(10);
        int max;
        newHeap.insert(1,23);
        max = newHeap.maximum();
        for(int i=1;i<=heapSize;i++) {
        	System.out.println(i+" "+vertices[i]+" "+weight[i]);
        }
        System.out.println("TEST " + max+" heapSize"+heapSize);
        newHeap.insert(3,100);
        max = newHeap.maximum();
        for(int i=1;i<=heapSize;i++) {
        	System.out.println(i+" "+vertices[i]+" "+weight[i]);
        }
        System.out.println("TEST " + max+" heapSize"+heapSize);
        newHeap.insert(6,121);
        max = newHeap.maximum();
        for(int i=1;i<=heapSize;i++) {
        	System.out.println(i+" "+vertices[i]+" "+weight[i]);
        }
        System.out.println("TEST " + max+" heapSize"+heapSize);
        //newHeap.delete(0);
        newHeap.insert(4,290);
        max = newHeap.maximum();
        for(int i=1;i<=heapSize;i++) {
        	System.out.println(i+" "+vertices[i]+" "+weight[i]);
        }
        System.out.println("TEST " + max+" heapSize"+heapSize);
        
        newHeap.insert(5,280);
        newHeap.delete(1);
        max = newHeap.maximum();
        for(int i=1;i<=heapSize;i++) {
        	System.out.println(i+" "+vertices[i]+" "+weight[i]);
        }
        System.out.println("TEST " + max+" heapSize"+heapSize);
    }
}

