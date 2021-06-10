import java.util.Arrays;
import java.util.Random;

public class Measurements {
    public static void main(String[] args) {
        Random rand = new Random();
        int n;
        Graph graph;
        Graph.Node[] nodes;
        int[] maxDegree = new int[16];
        int node1;
        int node2;
        for (int i = 6; i < 22; i++) {
            System.out.println(i);
            n = (int) Math.pow(2, i);
            nodes = new Graph.Node[n];
            for (int j = 0; j < n; j++) {
                nodes[j] = new Graph.Node(j+1, 1);
            }
            graph = new Graph(nodes);
            while (graph.getNumNodes() < n) {
                node1 = rand.nextInt(n-1) + 1;
                node2 = rand.nextInt(n-1) + 1;
                graph.addEdge(node1, node2);
                maxDegree[i-6] = graph.getNeighborhoodWeight(graph.maxNeighborhoodWeight().getId());
            }
        }
        System.out.println(Arrays.toString(maxDegree));
    }
}
