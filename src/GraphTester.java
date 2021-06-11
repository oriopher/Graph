import static org.junit.jupiter.api.Assertions.*;

public class GraphTester {

    public static void main(String[] args) {
        testGraph();

    }

    private static void testGraph() {
        Graph g = buildGraph();

        assertEquals(g.getNumNodes(), 7);
        assertEquals(g.getNumEdges(),0);

        // Add edges
        assertTrue(g.addEdge(1,3));
        assertTrue(g.addEdge(2,3));
        assertTrue(g.addEdge(7,4));
        assertTrue(g.addEdge(5,6));
        assertTrue(g.addEdge(6,2));
        assertTrue(g.addEdge(4,3));
        assertFalse(g.addEdge(4,17));
        assertFalse(g.addEdge(4,14));

        assertEquals(g.getNumEdges(),6);
        assertEquals(g.maxNeighborhoodWeight().getId(),4);
        assertEquals(g.getNeighborhoodWeight(5), 49);
        assertEquals(g.getNeighborhoodWeight(7), 193);

        g.deleteNode(4);
        assertFalse(g.addEdge(4,1));
        assertEquals(g.getNumNodes(),6);
        assertEquals(g.getNumEdges(),4);
        assertEquals(g.maxNeighborhoodWeight().getId(),6);

        assertTrue(g.addEdge(1,2));
        assertEquals(g.maxNeighborhoodWeight().getId(),6);
        assertEquals(g.getNeighborhoodWeight(2), 44);

        assertTrue(g.addEdge(2,7));
        assertEquals(g.maxNeighborhoodWeight().getId(),2);

        g.deleteNode(2);
        g.deleteNode(3);
        g.deleteNode(5);
        g.deleteNode(6);
        g.deleteNode(7);
        assertEquals(g.maxNeighborhoodWeight().getId(),1);

        assertEquals(g.getNeighborhoodWeight(1), 8);
        assertEquals(g.getNeighborhoodWeight(3), -1);

        assertEquals(g.getNumEdges(),0);

        g.deleteNode(1);
        assertNull(g.maxNeighborhoodWeight());
        assertEquals(g.getNumEdges(),0);
        assertEquals(g.getNumNodes(),0);

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
