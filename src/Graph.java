/**
 * This class represents a graph that efficiently maintains the heaviest neighborhood over edge addition and
 * vertex deletion.
 */
public class Graph {
    /**
     * Initializes the graph on a given set of nodes. The created graph is empty, i.e. it has no edges.
     * You may assume that the ids of distinct nodes are distinct.
     *
     * @param nodes - an array of node objects
     */
    public Graph(Node[] nodes) {
        //TODO: implement this method.
    }

    /**
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     *
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight() {
        //TODO: implement this method.
        return null;
    }

    /**
     * given a node id of a node in the graph, this method returns the neighborhood weight of that node.
     *
     * @param node_id - an id of a node.
     * @return the neighborhood weight of the node of id 'node_id' if such a node exists in the graph.
     * Otherwise, the function returns -1.
     */
    public int getNeighborhoodWeight(int node_id) {
        //TODO: implement this method.
        return 0;
    }

    /**
     * This function adds an edge between the two nodes whose ids are specified.
     * If one of these nodes is not in the graph, the function does nothing.
     * The two nodes must be distinct; otherwise, the function does nothing.
     * You may assume that if the two nodes are in the graph, there exists no edge between them prior to the call.
     *
     * @param node1_id - the id of the first node.
     * @param node2_id - the id of the second node.
     * @return returns 'true' if the function added an edge, otherwise returns 'false'.
     */
    public boolean addEdge(int node1_id, int node2_id) {
        //TODO: implement this method.
        return false;
    }

    /**
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id) {
        //TODO: implement this method.
        return false;
    }


    /**
     * This class represents a node in the graph.
     */
    public class Node {
        /**
         * Creates a new node object, given its id and its weight.
         *
         * @param id     - the id of the node.
         * @param weight - the weight of the node.
         */
        public Node(int id, int weight) {
            //TODO: implement this method.
            return;
        }

        /**
         * Returns the id of the node.
         *
         * @return the id of the node.
         */
        public int getId() {
            //TODO: implement this method.
            return 0;
        }

        /**
         * Returns the weight of the node.
         *
         * @return the weight of the node.
         */
        public int getWeight() {
            //TODO: implement this method.
            return 0;
        }
    }

    private class ModularHashFunction implements HashFunction<Integer> {

        private final int a;
        private final int b;
        private final int p;

        private ModularHashFunction(int p) {
            this.p = p;
            this.a = 0;
            this.b = 0;
        }

        @Override
        public int getHash(Integer elem) {
            return 0;
        }
    }

    private interface HashFunction<T> {
        public int getHash(T elem);
    }

    private class HashTable<K, V> {
        private final Vector<DoublyLinkedList<HashTableNode>> table;
        private final HashFunction<K> hashFunction;
        private final int m;

        private HashTable(int tableSize, HashFunction<K> hashFunction) {
            this.m = tableSize;
            this.hashFunction = hashFunction;
            this.table = null;
        }

        private void insert(K key, V value) {

        }

        private void delete(K key) {

        }

        private V get(K key){
            return null;
        }

        private class HashTableNode {
            private final K key;
            private V value;

            private HashTableNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    private class ExtendedNode implements BinaryHeapSelfPointer, Comparable<ExtendedNode> {

        private int heapIndex;
        private final Node node;
        private final DoublyLinkedList<NeighbouringNodes> neighbours;

        private ExtendedNode(Node node) {
            this.node = node;
            this.neighbours = new DoublyLinkedList<>();
        }

        @Override
        public int getHeapPointer() {
            return 0;
        }

        @Override
        public int setHeapPointer(int index) {
            return 0;
        }

        @Override
        public int compareTo(ExtendedNode o) {
            return 0;
        }

        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode addNeighbour(NeighbouringNodes neighbours) {
            return null;
        }

        private void deleteNeighbour(NeighbouringNodes neighbours) {

        }
    }

    private class NeighbouringNodes {
        private ExtendedNode node1;
        private ExtendedNode node2;
        private DoublyLinkedList<ExtendedNode>.DoublyLinkedListNode pointer1;
        private DoublyLinkedList<ExtendedNode>.DoublyLinkedListNode pointer2;

        private NeighbouringNodes(ExtendedNode node1, ExtendedNode node2) {
            this.node1 = node1;
            this.node2 = node2;
            this.pointer1 = null;
            this.pointer2 = null;
        }

        private DoublyLinkedList<ExtendedNode>.DoublyLinkedListNode getNeighbourNodePointer(ExtendedNode node) {
            return null;
        }

        private ExtendedNode getNeighbourNode(ExtendedNode node) {
            return null;
        }

        private void setPointer(ExtendedNode node) {

        }
    }

    private interface BinaryHeapSelfPointer {
        public int getHeapPointer();

        public int setHeapPointer(int index);
    }

    private class BinaryHeap<T extends Comparable<T> & BinaryHeapSelfPointer> {
        private final Vector<T> heap;

        private BinaryHeap(Vector<T> array) {
            this.heap = array;
            this.heapifyArray();
        }

        private void heapifyArray() {

        }

        private void deleteNode(T elem) {

        }

        private T getRoot() {
            return null;
        }

        private void updateElementPos(T elem) {

        }
    }

    private class Vector<T> {
        private final Object[] tArr;
        private final int[] positions;
        private final int[] legals;
        private final int length;
        private T defaultValue;
        private int initValues;

        private Vector(int size, T defaultValue) {
            this.length = size;
            this.tArr = new Object[size];
            this.positions = new int[size];
            this.legals = new int[size];
            this.setDefaultValue(defaultValue);
            this.setInitValues(0);
        }

        private int getLength() {
            return this.length;
        }

        private T getDefaultValue() {
            return this.defaultValue;
        }

        private void setDefaultValue(T defaultValue) {
            this.defaultValue = defaultValue;
        }

        private int getInitValues() {
            return this.initValues;
        }

        private void setInitValues(int initValues) {
            this.initValues = initValues;
        }

        private void incInitValues() {
            this.setInitValues(this.getInitValues() + 1);
        }

        private void decInitValues() {
            this.setInitValues(this.getInitValues() - 1);
        }

        private boolean checkIndexValidity(int index) {
            return (index < this.getLength() && index >= 0);
        }

        private T get(int index) {
            if (!this.checkIndexValidity(index)) {
                throw new IndexOutOfBoundsException();
            }
            if (this.isInit(index)) {
                @SuppressWarnings("unchecked")
                final T value = (T)this.tArr[index];
                return value;
            }
            return this.getDefaultValue();
        }

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

        private boolean isInit(int index) {
            if (this.checkIndexValidity(index)) {
                int position = this.positions[index];
                if (0 <= position && position < this.getInitValues()) {
                    int legal = this.legals[position];
                    return legal == index;
                }
            }
            return false;
        }

        private void markInit(int index) {
            this.legals[this.getInitValues()] = index;
            this.positions[index] = this.getInitValues();
            this.incInitValues();
        }
    }

    private class DoublyLinkedList<T> {

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
         * Checks the validity of an index relativly to the list size.
         * @complexity - O(1)
         */
        private boolean checkIndexValidity(int index) {
            return index < this.getSize() && index >= 0;
        }

        /**
         * Get the first node in the list that contains the value.
         * @param value - value to get its node
         * @return - If value exists than retuns the node, otherwise null.
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
         * @complexity - O(index)
         */
        private DoublyLinkedListNode getNode(int index) {
            if (this.checkIndexValidity(index)) {
                return null;
            }
            DoublyLinkedListNode sentinel = this.getSentinel();
            DoublyLinkedListNode node = sentinel.getNext();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
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
         * @complexity - O(n)
         */
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
         * @complexity - O(n)
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
        public void deleteFirst() {
            this.deleteNode(this.getSentinel().getNext());
        }

        /**
         * Delete the last value in the list. If list is empty then does nothing.
         * @complexity - O(1)
         */
        public void deleteLast() {
            this.deleteNode(this.getSentinel().getPrev());
        }

        /**
         * Delete the first node with a specific value.
         * If the list doesn't contain the value, does nothing.
         * @param value - Value to delete from the list.
         * @complexity - O(n)
         */
        public void deleteNode(T value) {
            DoublyLinkedListNode node = this.getNode(value);
            this.deleteNode(node);
        }

        /**
         * Delete the node at a specified location.
         * If location is invalid, then does nothing.
         * @param index - Index at which to delete the node.
         * @complexity - O(n)
         */
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

        public class DoublyLinkedListNode {

            private T value;
            private DoublyLinkedList<T> parentList;
            private DoublyLinkedListNode next;
            private DoublyLinkedListNode prev;

            private DoublyLinkedListNode(T value, DoublyLinkedList<T> parentList) {
                this.setValue(value);
                this.setParentList(parentList);
            }

            public T getValue() {
                return this.value;
            }

            private void setValue(T value) {
                this.value = value;
            }

            public DoublyLinkedList<T> getParentList() {
                return this.parentList;
            }

            private void setParentList(DoublyLinkedList<T> parentList) {
                this.parentList = parentList;
            }

            public DoublyLinkedListNode getNext() {
                return this.next;
            }
            
            private void setNext(DoublyLinkedListNode next) {
                this.next = next;
            }

            public DoublyLinkedListNode getPrev() {
                return this.prev;
            }

            private void setPrev(DoublyLinkedListNode prev) {
                this.prev = prev;
            }
        }
    }
}
