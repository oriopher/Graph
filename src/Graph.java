import java.util.function.Function;

public class Graph {
    public Graph(Node[] nodes) {
        //TODO: implement this method.
    }

    public Node maxNeighborhoodWeight() {
        //TODO: implement this method.
        return null;
    }

    public int getNeighborhoodWeight(int node_id) {
        //TODO: implement this method.
        return 0;
    }

    public boolean addEdge(int node1_id, int node2_id) {
        //TODO: implement this method.
        return false;
    }

    public boolean deleteNode(int node_id) {
        //TODO: implement this method.
        return false;
    }

    public class Node {
        public Node(int id, int weight) {
            //TODO: implement this method.
            return;
        }

        public int getId() {
            //TODO: implement this method.
            return 0;
        }

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
            //TODO: implement this method.
            this.p = p;
            this.a = 0;
            this.b = 0;
        }

        @Override
        public int getHash(Integer elem) {
            //TODO: implement this method.
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
            //TODO: implement this method.
            this.m = tableSize;
            this.hashFunction = hashFunction;
            this.table = null;
        }

        private void insert(K key, V value) {
            //TODO: implement this method.
        }

        private void delete(K key) {
            //TODO: implement this method.
        }

        private V get(K key){
            //TODO: implement this method.
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
            //TODO: implement this method.
            this.node = node;
            this.neighbours = new DoublyLinkedList<>();
        }

        @Override
        public int getHeapPointer() {
            //TODO: implement this method.
            return 0;
        }

        @Override
        public int setHeapPointer(int index) {
            //TODO: implement this method.
            return 0;
        }

        @Override
        public int compareTo(ExtendedNode o) {
            //TODO: implement this method.
            return 0;
        }

        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode addNeighbour(NeighbouringNodes neighbours) {
            //TODO: implement this method.
            return null;
        }

        private void deleteNeighbour(NeighbouringNodes neighbours) {
            //TODO: implement this method.
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
            //TODO: implement this method.
            return null;
        }

        private ExtendedNode getNeighbourNode(ExtendedNode node) {
            //TODO: implement this method.
            return null;
        }

        private void setPointer(ExtendedNode node) {
            //TODO: implement this method.
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
            //TODO: implement this method.
        }

        private void deleteNode(T elem) {
            //TODO: implement this method.
        }

        private T getRoot() {
            //TODO: implement this method.
            return null;
        }

        private void updateElementPos(T elem) {
            //TODO: implement this method.
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
         * Get the amout of cells in the vector.
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
        private void incInitValues() {
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
                @SuppressWarnings("unchecked")
                final T value = (T)this.tArr[index];
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
         * @complexity - O(min{index, n-index})
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
    }
}
