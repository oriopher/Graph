import java.util.Comparator;

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
    public Graph(Node [] nodes){
        //TODO: implement this method.
    }

    /**
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight(){
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
    public int getNeighborhoodWeight(int node_id){
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
    public boolean addEdge(int node1_id, int node2_id){
        //TODO: implement this method.
        return false;
    }

    /**
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id){
        //TODO: implement this method.
        return false;
    }


    /**
     * This class represents a node in the graph.
     */
    public class Node{
        /**
         * Creates a new node object, given its id and its weight.
         * @param id - the id of the node.
         * @param weight - the weight of the node.
         */
        public Node(int id, int weight){
            //TODO: implement this method.
            return;
        }

        /**
         * Returns the id of the node.
         * @return the id of the node.
         */
        public int getId(){
            //TODO: implement this method.
            return 0;
        }

        /**
         * Returns the weight of the node.
         * @return the weight of the node.
         */
        public int getWeight(){
            //TODO: implement this method.
            return 0;
        }
    }

    private interface BinaryHeapSelfPointer {
        public int getIndex();

        public int setIndex(int index);
    }

    private class BinaryHeap<T> {
        private final Comparator<T> cmp;
        private final Vector<T> heap;

        private BinaryHeap(Vector<T> array, Comparator<T> cmp) {
            this.cmp = cmp;
            this.heap = array;
            this.heapifyArray();
        }

        private void heapifyArray() {

        }

        private void deleteNode(int index) {

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

        public DoublyLinkedList() {
            this.initSentinel();
            this.setSize(0);
        }

        private void initSentinel() {
            this.sentinel = new DoublyLinkedListNode(null, this);
            this.sentinel.setNext(this.sentinel);
            this.sentinel.setPrev(this.sentinel);
        }

        private DoublyLinkedListNode getSentinel() {
            return this.sentinel;
        }

        public int getSize() {
            return this.size;
        }

        private void setSize(int size) {
            this.size = size;
        }

        private void incSize() {
            this.setSize(this.getSize() + 1);
        }

        private void decSize() {
            this.setSize(this.getSize() - 1);
        }

        private boolean checkIndexValidity(int index) {
            return index < this.getSize() && index >= 0;
        }

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

        public DoublyLinkedListNode insertFirst(T value) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
            this.insertFirst(node);
            return node;
        }

        private void insertFirst(DoublyLinkedListNode node) {
            this.insertAfter(node, this.sentinel);
        }

        public DoublyLinkedListNode insertLast(T value) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
            this.insertLast(node);
            return node;
        }

        private void insertLast(DoublyLinkedListNode node) {
            this.insertAfter(node, this.sentinel.getPrev());
        }

        public DoublyLinkedListNode insertAfter(T value, int index) {
            if (this.checkIndexValidity(index)) {
                DoublyLinkedListNode node = new DoublyLinkedListNode(value, this);
                this.insertAfter(node, index);
                return node;
            }
            return null;
        }

        private void insertAfter(DoublyLinkedListNode node, int index) {
            if (this.checkIndexValidity(index)) {
                DoublyLinkedListNode prev = this.getNode(index);
                this.insertAfter(node, prev);
            }
        }
        
        private void insertAfter(DoublyLinkedListNode node, DoublyLinkedListNode prev) {
            DoublyLinkedListNode next = prev.getNext();
            next.setPrev(node);
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(next);
            this.incSize();
        }

        public void deleteFirst() {
            this.deleteNode(this.getSentinel().getNext());
        }

        public void deleteLast() {
            this.deleteNode(this.getSentinel().getPrev());
        }

        public void deleteNode(T value) {
            DoublyLinkedListNode node = this.getNode(value);
            this.deleteNode(node);
        }

        public void deleteNode(int index) {
            DoublyLinkedListNode node = this.getNode(index);
            this.deleteNode(node);
        }

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
