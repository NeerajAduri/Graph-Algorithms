package networks_graphs;

/**
 * @author neeraj
 *
 */

public class Edge {
    private int start;
    private int end;
    private int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public int getOneVertex() {
        return start;
    }

    public int diffVertex(int v) {
        if (v == start) {
            return end;
        } else if (v == end) {
            return start;
        } else throw new IllegalArgumentException("Invalid end");
    }
}