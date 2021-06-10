public class GraphTester {

    public static void main(String[] args) {
        testGraph();

    }

    private static void testGraph() {
        Graph g = buildGraph();

        assert g.getNumNodes() == 7;
        assert g.getNumEdges() == 0;

        // Add edges
        assert g.addEdge(1,3);
        assert g.addEdge(2,3);
        assert g.addEdge(7,4);
        assert g.addEdge(5,6);
        assert g.addEdge(6,2);
        assert g.addEdge(4,3);
        assert !g.addEdge(4,17);
        assert !g.addEdge(4,4);

        assert (g.getNumEdges() == 6);
        assert g.maxNeighborhoodWeight().getId() == 4;
        assert g.getNeighborhoodWeight(5) == 49;
        assert g.getNeighborhoodWeight(7) == 193;

        g.deleteNode(4);
        assert !g.addEdge(4, 1);
        assert g.getNumNodes() == 6;
        assert g.getNumEdges() == 4;
        assert g.maxNeighborhoodWeight().getId() == 6;

        assert g.addEdge(1, 2);
        assert g.maxNeighborhoodWeight().getId() == 6;
        assert g.getNeighborhoodWeight(2) == 44;

        assert g.addEdge(2, 7);
        assert g.maxNeighborhoodWeight().getId() == 2;

        g.deleteNode(2);
        g.deleteNode(3);
        g.deleteNode(5);
        g.deleteNode(6);
        g.deleteNode(7);
        assert g.maxNeighborhoodWeight().getId() == 1;
        assert g.getNeighborhoodWeight(1) == 8;
        assert g.getNeighborhoodWeight(3) == -1;
        assert g.getNumEdges() == 0;

        g.deleteNode(1);
        assert g.maxNeighborhoodWeight() == null;
        assert g.getNumEdges() == 0;
        assert g.getNumNodes() == 0;
    }

    private static Graph buildGraph() {
        Graph.Node[] nodes = {
                new Graph.Node(1, 8),
                new Graph.Node(2,10),
                new Graph.Node(3,1),
                new Graph.Node(4,170),
                new Graph.Node(5,24),
                new Graph.Node(6,25),
                new Graph.Node(7,23),
        };
        return new Graph(nodes);
    }
}
