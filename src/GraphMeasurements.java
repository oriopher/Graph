import java.util.*;

public class GraphMeasurements {

    public static void main(String[] args) {
        for (int i = 6; i < 22; i++) {
            int n = (int) Math.pow(2, i);
            Graph g = buildGraph(n);
            int maxId = g.maxNeighborhoodWeight().getId();
            int maxRank = g.getNeighborhoodWeight(maxId) - 1;
            System.out.printf("i = %d, n = %d, rank = %d, prediction = %f%n", i, n, maxRank, i/Math.log(i));
        }
    }

    private static Graph buildGraph(int n) {
        Graph.Node[] nodes = new Graph.Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Graph.Node(i, 1);
        }
        Graph g = new Graph(nodes);

        Set<TwoTuple> edgesSet = new HashSet<>();
        Random random = new Random();
        while (edgesSet.size() < n) {
            int a = random.nextInt(n);
            int b = random.nextInt(n-1);
            if (b >= a) { b+=1; }
            else {
                int tmp = b;
                b = a;
                a = tmp;
            }

            TwoTuple tuple = new TwoTuple(a, b);
            if (!edgesSet.contains(tuple)) {
                edgesSet.add(tuple);
                g.addEdge(a, b);
            }
        }
        return g;
    }

    private static class TwoTuple {
        int a;
        int b;

        public TwoTuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TwoTuple twoTuple = (TwoTuple) o;
            return a == twoTuple.a && b == twoTuple.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
