import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class Graph {

    private static final int P = (int) (Math.pow(10, 9) + 9);

    private final BinaryHeap<ExtendedNode> heap;
    private final HashTable<Integer, ExtendedNode> table;

    private int numNodes;
    private int numEdges;

    /**
     * Initializes a new Graph with a given list of nodes.
     * @param nodes - an array of N nodes to initialize the graph with.
     * @complexity - O(N)
     */
    public Graph(Node[] nodes) {
        Vector<ExtendedNode> extendedNodes = nodesToExtendedNodes(nodes);
        int n = nodes.length;
        this.heap = new BinaryHeap<>(extendedNodes);
        this.table = new HashTable<>(n, new ModularHashFunction(P));
        this.initTable(extendedNodes);
        this.setNumEdges(0);
        this.setNumNodes(n);
    }

    /**
     * Convert an array of nodes to an array of extended nodes (create a new extended node from each node in the given array).
     * @param nodes - an array of N nodes to extend.
     * @return - a new Vector with the N extended nodes.
     * @complexity - O(N)
     */
    private Vector<ExtendedNode> nodesToExtendedNodes(Node[] nodes) {
        int n = nodes.length;
        ExtendedNode extendedNode;
        Vector<ExtendedNode> extendedNodes = new Vector<>(n, null);
        for (int i = 0; i < n; i++) {
            extendedNode = new ExtendedNode(nodes[i]);
            extendedNodes.set(extendedNode, i);
        }
        return extendedNodes;
    }

    /**
     * insert the given nodes into the graphs hash table.
     * @param nodes - a Vector of N extended nodes to insert to the graphs hash table.
     * @complexity - O(N)
     */
    private void initTable(Vector<ExtendedNode> nodes) {
        ExtendedNode node;
        for (int i = 0; i < nodes.getSize(); i++) {
            node = nodes.get(i);
            this.table.insert(node.getId(), node);
        }
    }

    /**
     * @return  - the Node in the graph that has the maximal neighborhood weight. return null if the graph is empty.
     * @complexity - O(1)
     */
    public Node maxNeighborhoodWeight() {
        ExtendedNode node = this.heap.getRoot();
        if (node == null) {
            return null;
        }
        return node.getNode();
    }

    /**
     * Calculate the neighborhood weight of a given node.
     * @param node_id - the ID of the node to Calculate its neighborhood weight.
     * @return - the neighborhood weight of the node with the given ID, if exists in graph, else null.
     * @complexity - O(1)
     */
    public int getNeighborhoodWeight(int node_id) {
        ExtendedNode node = this.table.get(node_id);
        if (node == null) {
            return -1;
        }
        return node.getTotalWeight();
    }

    /**
     * Add to graph an edge between node_1 and node_2.
     * @param node1_id - the ID of the first node in the new edge.
     * @param node2_id - the ID of the second node in the new edge.
     * @return true if a new edge was added to graph, false otherwise
     * (if one of the node ID's is not in the graph, or they are the same nodes.
     * @complexity - O(log n)
     */
    public boolean addEdge(int node1_id, int node2_id) {
        ExtendedNode node1 = this.table.get(node1_id);
        ExtendedNode node2 = this.table.get(node2_id);
        if (node1 == null || node2 == null || node1_id==node2_id) {
            return false;
        }
        NeighbouringNodes neighbours = new NeighbouringNodes(node1, node2);
        this.addNeighbour(node1, neighbours);
        this.addNeighbour(node2, neighbours);
        this.incNumEdges();
        return true;
    }

    /**
     * Add a neighbour to a given node.
     * @param node - the node to add the neighbour to.
     * @param neighbours - the NeighbouringNodes object that includes the given node and its wanted new neighbour.
     * @complexity - O(log n)
     */
    private void addNeighbour(ExtendedNode node, NeighbouringNodes neighbours) {
        DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode nodePointer = node.addNeighbour(neighbours);
        this.heap.updateElementPos(node);
        neighbours.setPointer(node, nodePointer);
    }

    /**
     *  Delete a node from the graph.
     * @param node_id - the ID of the node to delete.
     * @return true if the node was deleted false otherwise (the node ID does not exist in the graph).
     * @complexity - O((d_v + 1)*log n)
     */
    public boolean deleteNode(int node_id) {
        ExtendedNode node = this.table.get(node_id);
        if (node == null) {
            return false;
        }
        ExtendedNode other;
        for (NeighbouringNodes neighbours : node.getNeighboursList()) {
            other = neighbours.getNeighbourNode(node);
            other.deleteNeighbour(neighbours);
            this.heap.updateElementPos(other);
            this.decNumEdges();
        }
        this.heap.deleteNode(node);
        this.table.delete(node.getId());
        this.decNumNodes();
        return true;
    }

    /**
     * @return - the number of nodes in the graph.
     * @complexity - O(1)
     */
    public int getNumNodes() {
        return this.numNodes;
    }

    /**
     * Change the numNodes field of the graph.
     * @param numNodes - the new value for the numNodes field.
     * @complexity - O(1)
     */
    private void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    /**
     * Decrement the numNodes field by 1.
     * @complexity - O(1)
     */
    private void decNumNodes() {
        this.setNumNodes(this.getNumNodes() - 1);
    }

    /**
     * @return - The number of edges in the graph.
     * @complexity - O(1)
     */
    public int getNumEdges() {
        return this.numEdges;
    }

    /**
     * Change the numEdges field of the graph.
     * @param numEdges - the new value for the numEdges field.
     * @complexity - O(1)
     */
    private void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    /**
     * Increment the numEdges field by 1.
     * @complexity - O(1)
     */
    private void incNumEdges() {
        this.setNumEdges(this.getNumEdges() + 1);
    }

    /**
     * Decrement the numEdges field by 1.
     * @complexity - O(1)
     */
    private void decNumEdges() {
        this.setNumEdges(this.getNumEdges() - 1);
    }

    public static class Node {
        private final int id;
        private int weight;

        /**
         * Initialize a new node with an id and weight.
         * @param id - The ID of the node.
         * @param weight - The weight of the node.
         * @complexity - O(1)
         */
        public Node(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        /**
         * Return the ID of the node.
         * @return - ID of the node.
         * @complexity - O(1)
         */
        public int getId() {
            return this.id;
        }

        /**
         * Get the weight of the node.
         * @return - The weight of the node.
         * @complexity - O(1)
         */
        public int getWeight() {
            return this.weight;
        }
    }

    private class ModularHashFunction implements HashFunction<Integer> {

        private final int a;
        private final int b;
        private final int p;

        /**
         * Initialize a Modular Hash Function instance.
         * @param p - the prime number that is the upper limit for our integer keys for hashing.
         * @complexity - O(1)
         */
        private ModularHashFunction(int p) {
            Random rand = new Random();
            this.p = p;
            this.a = rand.nextInt(p-1)+1;
            this.b = rand.nextInt(p);
        }

        /**
         * Get the result of using the hash function on a given element.
         * @param elem - the element to hash.
         * @return - the hash function result for elem.
         */
        @Override
        public int getHash(Integer elem) {
            return Math.floorMod((this.a*elem + this.b),this.p);
        }
    }

    private interface HashFunction<T> {

        /**
         * Calculates the hash value of a given element.
         * @param elem - the element to operate the hash function on.
         * @return - the result of operating the hash function on the element.
         */
        public int getHash(T elem);
    }

    private class HashTable<K, V> {
        private final Vector<DoublyLinkedList<HashTableNode>> table;

        private final HashFunction<K> hashFunction;
        private final int m; // the size of the table used by the hash table.

        /**
         * Initialize a new hash table.
         * @param tableSize - the size of the hash tables table.
         * @param hashFunction - the hash function used by the hash table.
         * @complexity - O(1)
         */
        private HashTable(int tableSize, HashFunction<K> hashFunction) {
            this.m = tableSize;
            this.hashFunction = hashFunction;
            this.table = new Vector<>(tableSize, null);
        }

        /**
         * @return - the hash function instance used by the hash table.
         * @complexity - O(1)
         */
        private HashFunction<K> getHashFunction() {
            return this.hashFunction;
        }

        /**
         * @return - the m of the hash table (the size of the table).
         * @complexity - O(1)
         */
        private int getM() {
            return this.m;
        }

        /**
         * Get the chain the element with the given key is in.
         * @param key - the key of the element to return the chain it is in.
         * @return - the chain in the table's cell that is in the place of the hash of the given key.
         * if the cell is empty a new linked list (empty list) will be returned.
         * @complexity - O(1)
         */
        private DoublyLinkedList<HashTableNode> getChain(K key) {
            int hash = Math.floorMod(getHashFunction().getHash(key),getM());
            DoublyLinkedList<HashTableNode> chain = this.table.get(hash);
            if (chain == null) {
                chain = new DoublyLinkedList<>();
                this.table.set(chain, hash);
            }
            return chain;
        }


        /**
         * Get the chains node that holds the element with the given key.
         * @param key - the key of the element to get the chain node of.
         * @return - the node in the chain of the element with the given key.
         * @complexity - O(1)
         */
        private DoublyLinkedList<HashTableNode>.DoublyLinkedListNode getNode(K key) {
            DoublyLinkedList<HashTableNode> chain = getChain(key);
            HashTableNode node = new HashTableNode(key);
            return chain.getNode(node);
        }

        /**
         * Insert a new element to the table, if it's key is not in the the table yet.
         * @param key - the key of new element.
         * @param value - the value of the new element.
         * @complexity - O(1)
         */
        private void insert(K key, V value) {
            DoublyLinkedList<HashTableNode>.DoublyLinkedListNode chainNode = getNode(key);
            if (chainNode == null) {
                getChain(key).insertLast(new HashTableNode(key, value));
            }
        }

        /**
         * Delete from the table the item with the given key.
         * @param key - the key of the item to delete.
         * @complexity - O(1)
         */
        private void delete(K key) {
            DoublyLinkedList<HashTableNode>.DoublyLinkedListNode chainNode = getNode(key);
            if (chainNode != null) {
                getChain(key).deleteNode(chainNode);
            }
        }

        /**
         * Get the value of an item with the given key.
         * @param key - the key of the wanted item.
         * @return - the value of the wanted item. null - if there is no item with the given key in the table.
         * @complexity - O(1)
         */
        private V get(K key){
            DoublyLinkedList<HashTableNode>.DoublyLinkedListNode chainNode = getNode(key);
            if (chainNode != null) {
                return chainNode.getValue().getValue();
            }
            return null;
        }

        private class HashTableNode {
            private final K key;
            private V value;

            /**
             * Initialize a new hash table node
             * @param key - the key of the new node.
             * @param value - the value of the new node.
             * @complexity - O(1)
             */
            private HashTableNode(K key, V value) {
                this.key = key;
                this.value = value;
            }

            /**
             * Initialize a new hash table node with only a key.
             * @param key - the key of the new node.
             * @complexity - O(1)
             */
            private HashTableNode(K key) {
                this.key = key;
            }

            /**
             * @return - the key of a hash table node.
             * @complexity - O(1)
             */
            private K getKey() {
                return key;
            }

            /**
             * @return - the value of a hash table node.
             * @complexity - O(1)
             */
            private V getValue() {
                return value;
            }

            /**
             * Check if two hash table nodes are equal.
             * @param o - an object representing the other hash table node.
             * @return - true if o is a HashTableNode and its key is equal to the node's key. false otherwise.
             * @complexity - O(1)
             */
            @Override
            public boolean equals(Object o) {
                HashTableNode node;
                if (o instanceof HashTable.HashTableNode) {
                    node = (HashTableNode) o;
                    return getKey().equals(node.getKey());
                }
                return false;
            }


        }
    }

    private class ExtendedNode implements BinaryHeapSelfIndex, Comparable<ExtendedNode> {

        private int heapIndex;
        private int totalWeight;
        private final Node node;
        private final DoublyLinkedList<NeighbouringNodes> neighboursList;

        /**
         * Initialize an extended node that wraps a node and adds functionality.
         * @param node - Wrapped node.
         * @complexity - O(1)
         */
        private ExtendedNode(Node node) {
            this.node = node;
            this.neighboursList = new DoublyLinkedList<>();
            this.setHeapIndex(-1);
            this.setTotalWeight(node.getWeight());
        }

        /**
         * Get the index of the element in the binary heap.
         * @return Index of the element in the binary heap.
         * @complexity - O(1)
         */
        @Override
        public int getHeapIndex() {
            return this.heapIndex;
        }

        /**
         * Set the index of the element in the binary heap.
         * @param index - Index of the element in the binary heap.
         * @complexity - O(1)
         */
        @Override
        public void setHeapIndex(int index) {
            this.heapIndex = index;
        }

        /**
         * Get the ID of the node.
         * @return - The ID of the node.
         * @complexity - O(1)
         */
        private int getId() {
            return this.node.getId();
        }

        /**
         * Get the weight of the node.
         * @return - The weight of the node.
         * @complexity - O(1)
         */
        private int getWeight() {
            return this.node.getWeight();
        }

        /**
         * Get the wrapped node.
         * @return - The wrapped node.
         * @complexity - O(1)
         */
        private Node getNode() {
            return this.node;
        }

        /**
         * Get the sum of the weight of the node and its neighbours' weight.
         * @return - Sum of the weight of the node and its neighbours' weight.
         * @complexity - O(1)
         */
        private int getTotalWeight() {
            return this.totalWeight;
        }

        /**
         * Set the sum of the weight of the node and its neighbours' weight.
         * @param totalWeight - Sum of the weight of the node and its neighbours' weight.
         * @complexity - O(1)
         */
        private void setTotalWeight(int totalWeight) {
            this.totalWeight = totalWeight;
        }

        /**
         * Increase the total weight of the node.
         * @param inc - Amount to increase.
         * @complexity - O(1)
         */
        private void incTotalWeight(int inc) {
            this.setTotalWeight(this.getTotalWeight() + inc);
        }

        /**
         * Decrease the total weight of the node.
         * @param dec - Amount to decrease/
         * @complexity - O(1)
         */
        private void decTotalWeight(int dec) {
            this.setTotalWeight(this.getTotalWeight() - dec);
        }

        
        /**
         * Get a list of the node's neighbours.
         * @return List of the node's neighbours.
         * @complexity - O(1)
         */
        private DoublyLinkedList<NeighbouringNodes> getNeighboursList() {
            return this.neighboursList;
        }

        /**
         * Compare between two ExtendedNodes based on total weight from higher to lower.
         * @param other - Other node to compare to.
         * @complexity - O(1)
         */
        @Override
        public int compareTo(ExtendedNode other) {
            return Integer.compare(other.getTotalWeight(), this.getTotalWeight());
        }

        /**
         * Add the neighbouring relationship between two nodes.
         * @param neighbours - Representation of this node and other node being neighbours.
         * @return - Pointer to the neighbours list node to this relation.
         * @complexity - O(1)
         */
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode addNeighbour(NeighbouringNodes neighbours) {
            DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode listNode = this.neighboursList.insertFirst(neighbours);
            ExtendedNode other = neighbours.getNeighbourNode(this);
            this.incTotalWeight(other.getWeight());
            return listNode;
        }

        /**
         * Remove the neighbouring relationship between two nodes.
         * @param neighbours - Representation of this node and other node being neighbours.
         * @complexity - O(1)
         */
        private void deleteNeighbour(NeighbouringNodes neighbours) {
            DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode listNode = neighbours.getNodePointer(this);
            this.neighboursList.deleteNode(listNode);
            this.decTotalWeight(neighbours.getNeighbourNode(this).getWeight());
        }

        /**
         * Decide if two nodes are the same based on their names.
         * @param o - Other node to compare to
         * @complexity - O(1)
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof ExtendedNode) {
                ExtendedNode other = (ExtendedNode)o;
                return this.getId() == other.getId();
            }
            return false;
        }
    }

    private class NeighbouringNodes {
        private final ExtendedNode node1;
        private final ExtendedNode node2;
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer1;
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer2;

        /**
         * Initialize a new neighbouring node.
         * @param node1 - the first node in the neighbouring node.
         * @param node2 - the second node in the neighbouring node.
         * @complexity - O(1)
         */
        private NeighbouringNodes(ExtendedNode node1, ExtendedNode node2) {
            this.node1 = node1;
            this.node2 = node2;
            this.pointer1 = null;
            this.pointer2 = null;
        }

        /**
         * Get the pointer to the cell in the given node neighbours list.
         * @param node - the node to get its relevant pointer.
         * @return - the pointer to the relevant cell in the given node's neighbours list.
         * @complexity - O(1)
         */
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode getNodePointer(ExtendedNode node) {
            if (node.equals(this.node1))  {
                return this.pointer1;
            }
            return this.pointer2;
        }

        /**
         * Get the neighbouring node of the given node.
         * @param node - the node to get its neighbour.
         * @return - the node in the NeighbouringNodes object that was not given.
         * @complexity - O(1)
         */
        private ExtendedNode getNeighbourNode(ExtendedNode node) {
            if (node.equals(this.node1))  {
                return this.node2;
            }
            return this.node1;
        }

        /**
         * Set the pointer relevant for the given node.
         * @param node - the node relevant to the pointer to set.
         * @param pointer - the value to set for the relevant pointer field.
         * @complexity - O(1)
         */
        private void setPointer(ExtendedNode node, DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer) {
            if (node.equals(this.node1))  {
                this.pointer1 = pointer;
            } else {
                this.pointer2 = pointer;
            }
        }
    }

    private interface BinaryHeapSelfIndex {
        /**
         * Get the index of the element in the binary heap.
         * @return - Index of the element in the binary heap.
         */
        public int getHeapIndex();

        /**
         * Set the index of the element in the binary heap.
         * @param index - Index of the element in the binary heap.
         */
        public void setHeapIndex(int index);
    }

    private class BinaryHeap<T extends Comparable<T> & BinaryHeapSelfIndex> {
        private final Vector<T> heap;
        private int elementsCount;

        /**
         * Initialize a binary heap from an array.
         * @param array - An array to initialize a heap.
         * @complexity - O(n)
         */
        private BinaryHeap(Vector<T> array) {
            this.heap = array;
            this.setElementsCount(this.heap.getSize());
            this.heapifyArray();
        }

        /**
         * Get the amount of elements in the heap.
         * @return - Amount of elements in the heap.
         * @complexity - O(1)
         */
        private int getElementsCount() {
            return this.elementsCount;
        }

        /**
         * Set the amount of elements in the heap.
         * @param elementsCount - Amount of elements in the heap.
         * @complexity - O(1)
         */
        private void setElementsCount(int elementsCount) {
            this.elementsCount = elementsCount;
        }

        /**
         * Decrement the amount of elements in the heap by 1.
         * @complexity - O(1)
         */
        private void decElementsCount() {
            this.setElementsCount(this.getElementsCount() - 1);
        }

        /**
         * Get the index of the node's parent.
         * @param nodeIndex - The index of the node.
         * @return - The index of the node's parent. If this is the root, return -1.
         * @complexity - O(1)
         *
         */
        // TODO: DELETE!!!
        private int getParentIndex(int nodeIndex) {
            return this.getParentIndexRebaseRoot(nodeIndex, 0);
        }

        /**
         * Get the index of the node's parent, while taking only a subset of the heap.
         * @param nodeIndex - The index of the node.
         * @param rootIndex - The index of the root in the subset heap.
         * @return - The index of the node's parent. If this is the root, return -1.
         * @complexity - O(1)
         */
        private int getParentIndexRebaseRoot(int nodeIndex, int rootIndex) {
            if (nodeIndex > rootIndex) {
                nodeIndex += 1;
                int parentIndex = (int)Math.floor((double)nodeIndex/2) - 1;
                if (parentIndex >= rootIndex) {
                    return parentIndex;
                }
            }
            return -1;
        }

        /**
         * Get the index of the node's left child.
         * @param nodeIndex - The index of the node.
         * @return - The index of the node's left child. If the node doesn't have a left child, return -1.
         * @complexity - O(1)
         */
        private int getLeftChildIndex(int nodeIndex) {
            nodeIndex += 1;
            int leftIndex = 2*nodeIndex - 1;
            if (leftIndex < this.getElementsCount()) {
                return leftIndex;
            }
            return -1;
        }

        /**
         * Get the index of the node's right child.
         * @param nodeIndex - The index of the node.
         * @return - The index of the node's right child. If the node doesn't have a right child, return -1.
         * @complexity - O(1)
         */
        private int getRightChildIndex(int nodeIndex) {
            nodeIndex += 1;
            int rightIndex = 2*nodeIndex;
            if (rightIndex < this.getElementsCount()) {
                return rightIndex;
            }
            return -1;
        }

        /**
         * While initializing a heap, make the array into a valid heap.
         * @complexity - O(n)
         */
        private void heapifyArray() {
            for (int i = 0; i < this.getElementsCount(); i++) {
                this.heap.get(i).setHeapIndex(i);
            }
            for (int i = this.getElementsCount() - 1; i >= 0; i--) {
                this.bubbleDown(i);
            }
        }

        /**
         * Delete an element from the heap.
         * @param elem - Element to delete from the heap.
         * @complexity - O(log n)
         */
        private void deleteNode(T elem) {
            int index = elem.getHeapIndex();
            this.deleteNode(index);
        }


        /**
         * Delete a node in a specified index.
         * @param index - The index of the node to delete.
         * @complexity - O(log n)
         */
        private void deleteNode(int index) {
            int lastElementIndex = this.getElementsCount() - 1;
            T node = this.heap.get(lastElementIndex);
            this.heap.set(node, index);
            node.setHeapIndex(index);
            this.heap.set(null, lastElementIndex);
            this.decElementsCount();
            if (index != lastElementIndex) {
                this.updateElementPos(index);
            }
        }

        /**
         * Get the root element in the heap.
         * @return Root element in the heap.
         * @complexity - O(1)
         */
        private T getRoot() {
            if (this.getElementsCount() > 0) {
                return this.heap.get(0);
            }
            return null;
        }

        /**
         * Decide if an element is in the correct location in the heap, and if not, perform either a
         * bubble up or bubble down operation, according to which is required.
         * @param elem - The element to update its location in the heap.
         * @complexity - O(log n)
         */
        private void updateElementPos(T elem) {
            int index = elem.getHeapIndex();
            updateElementPos(index);
        }

        /**
         * Decide if a node is in the correct location in the heap, and if not, perform either a bubble
         * up or bubble down operation, according to which is required.
         * @param index - The index of the node in question.
         * @complexity - O(log n)
         */
        private void updateElementPos(int index) {
            this.updateElementPosRebaseRoot(index, 0);
        }

        /**
         * Decide if a node is in the correct location in a sub-heap, and if not, perform either a bubble
         * up or bubble down operation, according to which is required.
         * @param index - The index of the node in question.
         * @param rootIndex - The index of the sub-heap root index.
         * @complexity - O(log n)
         */
        private void updateElementPosRebaseRoot(int index, int rootIndex) {
            if (!isLegal(this.getParentIndexRebaseRoot(index, rootIndex), index)) {
                this.bubbleUpRebaseRoot(index, rootIndex);
            }
            else if (!(isLegal(index, this.getLeftChildIndex(index)) && isLegal(index, getRightChildIndex(index)))) {
                this.bubbleDown(index);
            }
        }

        /**
         * Checks if the relationship between parent and child in heap satisfies the order relationship.
         * @param parentIndex - Index of parent node.
         * @param childIndex - Index of child node.
         * @return - True if the relationship between parent and child is correct, otherwise false.
         * @complexity - O(1)
         */
        private boolean isLegal(int parentIndex, int childIndex) {
            if (parentIndex == -1 || childIndex == -1) {
                return true;
            }
            return this.getMinIndex(parentIndex, childIndex) == parentIndex;
        }

        /**
         * Compare between two values in the heap, and check according to their order relation
         * which is minimal.
         * @param index1 - Index of a node to check.
         * @param index2 - Index of other node to check.
         * @return - Index of the minimal node according to the order relation. If both are of the same
         * order, return index1.
         */
        private int getMinIndex(int index1, int index2) {
            int cmp = this.heap.get(index1).compareTo(this.heap.get(index2));
            if (cmp >= 0) {
                return index2;
            }
            return index1;
        }

        /**
         * Perform a bubble up operation of a node in the heap, from current
         * location to correct location in the heap.
         * @param index - The index of the node to perform the bubble up on.
         * @complexity - O(log n)
         */
        // TODO: DELETE!!!
        private void bubbleUp(int index) {
            this.bubbleUpRebaseRoot(index, 0);
        }

        /**
         * Perform a bubble up operation of a node in a sub-heap, from current
         * location to correct location in the heap.
         * @param index - The index of the node to perform the bubble up on.
         * @param rootIndex - The root index of the sub-heap.
         * @complexity - O(log n)
         */
        private void bubbleUpRebaseRoot(int index, int rootIndex) {
            int parentIndex;
            while (index > rootIndex) {
                parentIndex = this.getParentIndexRebaseRoot(index, rootIndex);
                if (singleBubbleUp(index, parentIndex, rootIndex) == index) {
                    break;
                }
                index = parentIndex;
            }
        }

        /**
         * Perform a single bubble up operation on a node in a sub-heap, if required.
         * @param childIndex - The index of the node 
         * @param parentIndex - The parent index of the node.
         * @param rootIndex - The index of the sub-heap root.
         * @return - The new index of the node.
         * @complexity - O(1)
         */
        private int singleBubbleUp(int childIndex, int parentIndex, int rootIndex) {
            if (isLegal(parentIndex, childIndex)) {
                return childIndex;
            }
            this.changeHeapNodes(childIndex, parentIndex);
            return parentIndex;
        }

        /**
         * Perform a bubble down operation of a node in the heap, from current
         * location to correct location in the heap.
         * @param index - The index of the node to perform the bubble down on.
         * @complexity - O(log n)
         */
        private void bubbleDown(int index) {
            int leftChildIndex;
            int rightChildIndex;
            int newIndex;
            int elementsCount = this.getElementsCount();
            while (index < elementsCount) {
                leftChildIndex = this.getLeftChildIndex(index);
                rightChildIndex = this.getRightChildIndex(index);
                newIndex = singleBubbleDown(index, leftChildIndex, rightChildIndex);
                if (index == newIndex) {
                    break;
                }
                index = newIndex;
            }
        }

        /**
         * Perform a single bubble down operation between a node and its children, if required.
         * @param parentIndex - The index of the node in question.
         * @param leftChildIndex - The index of the node's left child.
         * @param rightChildIndex - The index of the node's right child.
         * @return - The new index of the node.
         * @complexity - O(1)
         */
        private int singleBubbleDown(int parentIndex, int leftChildIndex, int rightChildIndex) {
            if (!(isLegal(parentIndex, leftChildIndex) && isLegal(parentIndex, rightChildIndex))) {
                int minChildIndex = rightChildIndex != -1 ?
                                    this.getMinIndex(leftChildIndex, rightChildIndex) : leftChildIndex;
                this.changeHeapNodes(parentIndex, minChildIndex);
                return minChildIndex;
            }
            return parentIndex;
        }

        /**
         * Switch between the values of two cells in the array representing the heap.
         * @param index1 - The index of the first element.
         * @param index2 - The index of the second element.
         * @complexity - O(1)
         */
        private void changeHeapNodes(int index1, int index2) {
            T element1 = this.heap.get(index1);
            T element2 = this.heap.get(index2);
            this.heap.set(element1, index2);
            this.heap.set(element2, index1);
            element1.setHeapIndex(index2);
            element2.setHeapIndex(index1);
        }
    }

    private class Vector<T> {
        private final Object[] tArr;
        private final int[] positions;
        private final int[] legals;
        private final int size;
        private T defaultValue;
        private int initValuesCount;

        /**
         * Initialize a vector with all the cells initialized to the same value.
         * @param size - The size of the vector.
         * @param defaultValue - The initial value of all the cells.
         * @complexity - O(1)
         */
        private Vector(int size, T defaultValue) {
            this.size = size;
            this.tArr = new Object[size];
            this.positions = new int[size];
            this.legals = new int[size];
            this.setDefaultValue(defaultValue);
            this.setInitValuesCount(0);
        }

        /**
         * Get the amount of cells in the vector.
         * @return - The amount of cells in the vector.
         * @complexity - O(1)
         */
        private int getSize() {
            return this.size;
        }

        /**
         * Get the default value of all the non initialized cells.
         * @return - Default value of a cell in the vector.
         * @complexity - O(1)
         */
        private T getDefaultValue() {
            return this.defaultValue;
        }

        /**
         * Set the default value of all the non initialized cells.
         * @param defaultValue - Default value of a cell in the vector.
         * @complexity - O(1)
         */
        private void setDefaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
        }

        /**
         * Get the amount of initialized values in the vector.
         * @return - The amount of initialized values in the vector.
         * @complexity - O(1)
         */
        private int getInitValuesCount() {
            return this.initValuesCount;
        }

        /**
         * Set the amount of initialized values in the vector.
         * @param initValuesCount - The amount of initialized values in the vector.
         * @complexity - O(1)
         */
        private void setInitValuesCount(int initValuesCount) {
            this.initValuesCount = initValuesCount;
        }

        /**
         * Increment the amount of initialized values in the vector.
         * @complexity - O(1)
         */
        private void incInitValuesCount() {
            this.setInitValuesCount(this.getInitValuesCount() + 1);
        }

        /**
         * Check the validity of an index relative to the vector.
         * @param index - The index to check its validity.
         * @return - If the index is valid return true, otherwise false.
         * @complexity - O(1)
         */
        private boolean checkIndexValidity(int index) {
            return (index < this.getSize() && index >= 0);
        }

        /**
         * Get the element in the vector at a specified index.
         * @param index - The index of the element to get.
         * @return - The value of the vector at the index.
         * @complexity - O(1)
         */
        private T get(int index) {
            if (!this.checkIndexValidity(index)) {
                throw new IndexOutOfBoundsException();
            }
            if (this.isInit(index)) {
                Object valueOptional = this.tArr[index];
                @SuppressWarnings("unchecked")
                T value = valueOptional != null ? (T)valueOptional : null;
                return value;
            }
            return this.getDefaultValue();
        }

        /**
         * Set the value of an element in the vector at a specified index.
         * @param value - The value to store in the vector.
         * @param index - The index to store the value in the vector.
         * @complexity - O(1)
         */
        private void set(T value, int index) {
            if (this.checkIndexValidity(index)) {
                this.tArr[index] = value;
                if (!this.isInit(index)) {
                    this.markInit(index);
                }
            }
            else {
                throw new IndexOutOfBoundsException();
            }
        }

        /**
         * Checks if a cell in the vector is initialized.
         * @param index - Index of cell to check.
         * @return - Returns true if cell was previously initialize, otherwise false.
         * @complexity - O(1)
         */
        private boolean isInit(int index) {
            if (this.checkIndexValidity(index)) {
                int position = this.positions[index];
                if (0 <= position && position < this.getInitValuesCount()) {
                    int legal = this.legals[position];
                    return legal == index;
                }
            }
            return false;
        }

        /**
         * Mark a cell as initialized.
         * @param index - The cell to mark as initialized.
         * @complexity - O(1)
         */
        private void markInit(int index) {
            this.legals[this.getInitValuesCount()] = index;
            this.positions[index] = this.getInitValuesCount();
            this.incInitValuesCount();
        }
    }

    private class DoublyLinkedList<T> implements Iterable<T> {

        private DoublyLinkedListNode sentinel;
        private int size;

        /**
         * Initialize an empty list
         * @complexity - O(1)
         */
        public DoublyLinkedList() {
            this.initSentinel();
            this.setSize(0);
        }

        /**
         * Initialize the sentinel of the list
         * @complexity - O(1)
         */
        private void initSentinel() {
            this.sentinel = new DoublyLinkedListNode(null, this);
            this.sentinel.setNext(this.sentinel);
            this.sentinel.setPrev(this.sentinel);
        }

        /**
         * Get the sentinel of the list
         * @return - Sentinel of list
         * @complexity - O(1)
         */
        private DoublyLinkedListNode getSentinel() {
            return this.sentinel;
        }

        /**
         * Get the amount of elements in the list.
         * @return - The size of the list.
         * @complexity - O(1)
         */
        public int getSize() {
            return this.size;
        }

        /**
         * Sets the amount of elements in the list.
         * @param size - The new size of the list.
         * @complexity - O(1)
         */
        private void setSize(int size) {
            this.size = size;
        }

        /**
         * Increase the amount of elements in the list by 1.
         * @complexity - O(1)
         */
        private void incSize() {
            this.setSize(this.getSize() + 1);
        }

        /**
         * Decrease the amount of elements in the list by 1.
         * @complexity - O(1)
         */
        private void decSize() {
            this.setSize(this.getSize() - 1);
        }

        /**
         * Checks the validity of an index relatively to the list size.
         * @complexity - O(1)
         */
        private boolean checkIndexValidity(int index) {
            return index < this.getSize() && index >= 0;
        }

        /**
         * Get the first node in the list that contains the value.
         * @param value - value to get its node
         * @return - If value exists than returns the node, otherwise null.
         * @complexity - O(n)
         */
        private DoublyLinkedListNode getNode(T value) {
            DoublyLinkedListNode sentinel = this.getSentinel();
            DoublyLinkedListNode node = sentinel.getNext();
            while (node != sentinel) {
                if (value.equals(node.getValue())) {
                    return node;
                }
                node = node.getNext();
            }
            return null;
        }

        /**
         * Get the node at a specific index.
         * @param index - The index of the required node.
         * @return - The node of the index if valid, otherwise null.
         * @complexity - O(min{index, n-index})
         */
        private DoublyLinkedListNode getNode(int index) {
            if (this.checkIndexValidity(index)) {
                return null;
            }
            int reverseIndex = this.getSize() - 1 - index;
            Function<DoublyLinkedListNode, DoublyLinkedListNode> func;
            if (index <= reverseIndex) {
                func = ((currentNode) -> (currentNode.getNext()));
            }
            else {
                func = ((currentNode) -> (currentNode.getPrev()));
            }
            DoublyLinkedListNode node = func.apply(this.getSentinel());
            for (int i = 0; i < Math.min(index, reverseIndex); i++) {
                node = func.apply(node);
            }
            return node;
        }

        /**
         * Insert a value as the first entry in the list.
         * @param value - Value to insert.
         * @return - Node which contains the value.
         * @complexity - O(1)
         */
        public DoublyLinkedListNode insertFirst(T value) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
            this.insertFirst(node);
            return node;
        }

        /**
         * Insert a node as a first entry in the list.
         * @param node - Node to insert first.
         * @complexity - O(1)
         */
        private void insertFirst(DoublyLinkedListNode node) {
            this.insertAfter(node, this.sentinel);
        }

        /**
         * Insert a value as the last entry in the list.
         * @param value - Value to insert.
         * @return - Node which contains the value.
         * @complexity - O(1)
         */
        public DoublyLinkedListNode insertLast(T value) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
            this.insertLast(node);
            return node;
        }

        /**
         * Insert a node as the last entry in the list.
         * @param node - Node to insert last.
         * @complexity - O(1)
         */
        private void insertLast(DoublyLinkedListNode node) {
            this.insertAfter(node, this.sentinel.getPrev());
        }

        /**
         * Insert a value to the list after an index. If index is invalid, then does nothing.
         * @param value - Value to insert.
         * @param index - The index after which to insert the value.
         * @return - Node which contains the inserted value.
         * @complexity - O(min{index, n-index})
         */
        // TODO: DELETE!!!
        public DoublyLinkedListNode insertAfter(T value, int index) {
            if (this.checkIndexValidity(index)) {
                DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
                this.insertAfter(node, index);
                return node;
            }
            return null;
        }

        /**
         * Insert a node to the list after an index. If index is invalid, then does nothing.
         * @param node - Node to insert.
         * @param index - The index after which to insert the node.
         * @complexity - O(min{index, n-index})
         */
        private void insertAfter(DoublyLinkedListNode node, int index) {
            if (this.checkIndexValidity(index)) {
                DoublyLinkedListNode prev = this.getNode(index);
                this.insertAfter(node, prev);
            }
        }
        
        /**
         * Insert a node to the list after a specified node.
         * @param node - Node to insert.
         * @param prev - Node after which the new node will be inserted.
         * @complexity - O(1)
         */
        private void insertAfter(DoublyLinkedListNode node, DoublyLinkedListNode prev) {
            DoublyLinkedListNode next = prev.getNext();
            next.setPrev(node);
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(next);
            this.incSize();
        }

        /**
         * Delete the first value in the list. If list is empty then does nothing.
         * @complexity - O(1)
         */
        // TODO: DELETE!!!
        public void deleteFirst() {
            this.deleteNode(this.getSentinel().getNext());
        }

        /**
         * Delete the last value in the list. If list is empty then does nothing.
         * @complexity - O(1)
         */
        // TODO: DELETE!!!
        public void deleteLast() {
            this.deleteNode(this.getSentinel().getPrev());
        }

        /**
         * Delete the first node with a specific value.
         * If the list doesn't contain the value, does nothing.
         * @param value - Value to delete from the list.
         * @complexity - O(n)
         */
        // TODO: DELETE!!!
        public void deleteNode(T value) {
            DoublyLinkedListNode node = this.getNode(value);
            this.deleteNode(node);
        }

        /**
         * Delete the node at a specified location.
         * If location is invalid, then does nothing.
         * @param index - Index at which to delete the node.
         * @complexity - O(min{index, n-index})
         */
        // TODO: DELETE!!!
        public void deleteNode(int index) {
            DoublyLinkedListNode node = this.getNode(index);
            this.deleteNode(node);
        }

        /**
         * Delete a node from the list.
         * @param node - Node to delete from the list.
         * @complexity - O(1)
         */
        private void deleteNode(DoublyLinkedListNode node) {
            if (node != null && node.getParentList() == this && node != this.getSentinel()) {
                DoublyLinkedListNode next = node.getNext();
                DoublyLinkedListNode prev = node.getPrev();
                prev.setNext(next);
                next.setPrev(prev);
                this.decSize();
            }
        }

        /**
         * Return an iterator over the elements in the list.
         * @complexity - O(1)
         */
        @Override
        public Iterator<T> iterator() {
            return new DoublyLinkedListIterator(this.sentinel);
        }

        public class DoublyLinkedListNode {

            private T value;
            private DoublyLinkedList<T> parentList;
            private DoublyLinkedListNode next;
            private DoublyLinkedListNode prev;

            /**
             * Initialize a new node.
             * @param value - The value which the node contains.
             * @param parentList - The list in which the node is contained.
             * @complexity - O(1)
             */
            private DoublyLinkedListNode(T value, DoublyLinkedList<T> parentList) {
                this.setValue(value);
                this.setParentList(parentList);
            }

            /**
             * Get the value which the node contains.
             * @return - The value which the node contains.
             * @complexity - O(1)
             */
            public T getValue() {
                return this.value;
            }

            /**
             * Set the value which the node contains.
             * @param value - The new value which the node will contain.
             * @complexity - O(1)
             */
            private void setValue(T value) {
                this.value = value;
            }

            /**
             * Get the list in which the node is contained.
             * @return - The list in which the node is contained.
             * @complexity - O(1)
             */
            public DoublyLinkedList<T> getParentList() {
                return this.parentList;
            }

            /**
             * Set the list in which the node is contained.
             * @param parentList - The list in which the node is contained.
             * @complexity - O(1)
             */
            private void setParentList(DoublyLinkedList<T> parentList) {
                this.parentList = parentList;
            }

            /**
             * Get the next node in the list after this node.
             * @return - The node in the list which is after this node.
             * @complexity - O(1)
             */
            public DoublyLinkedListNode getNext() {
                return this.next;
            }
            
            /**
             * Set the next node in the list after this node.
             * @param next - The node in the list which is after this node.
             * @complexity - O(1)
             */
            private void setNext(DoublyLinkedListNode next) {
                this.next = next;
            }

            /**
             * Get the previous node in the list before this node.
             * @return - The previous node in the list which is before this node.
             * @complexity - O(1)
             */
            public DoublyLinkedListNode getPrev() {
                return this.prev;
            }

            /**
             * Set the previous node in the list before this node.
             * @param prev - The previous node in the list which is before this node.
             * @complexity - O(1)
             */
            private void setPrev(DoublyLinkedListNode prev) {
                this.prev = prev;
            }
        }

        private class DoublyLinkedListIterator implements Iterator<T> {

            DoublyLinkedListNode sentinel;
            DoublyLinkedListNode next;

            /**
             * Initialize an iterator over a doubly linked list.
             * @param sentinel - Sentinel of the list to iterate.
             */
            private DoublyLinkedListIterator(DoublyLinkedListNode sentinel) {
                this.sentinel = sentinel;
                this.next = sentinel.getNext();
            }

            /**
             * Check if there is another element in the list to return.
             * @complexity - O(1)
             */
            @Override
            public boolean hasNext() {
                return this.sentinel != this.next;
            }
            
            /**
             * Return the next item in the doubly linked list.
             * @complexity - O(1)
             */
            @Override
            public T next() {
                DoublyLinkedListNode current = this.next;
                this.next = current.getNext();
                return current.getValue();
            }
        }
    }
}
