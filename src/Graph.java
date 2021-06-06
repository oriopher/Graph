import java.util.Iterator;
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
            this.setWeight(weight);
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
         * Set the weight of the node.
         * @param weight - The weight of the node.
         * @complexity - O(1)
         */
        public void setWeight(int weight) {
            this.weight = weight;
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

    private class HashTable<K, V> implements Iterable<K> {
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

        /**
         * Create an iterator to iterate over all the keys in the table.
         * @return - Iterator over all keys in the table.
         * @complexity - O(1)
         */
        public Iterator<K> iterator() {
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
        }

        /**
         * Get the index of the element in the binery heap.
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
         * Get the weight of the node.
         * @return - The weight of the node.
         * @complexity - O(1)
         */
        private int getWeight() {
            return this.node.getWeight();
        }

        /**
         * Set the weight of the node.
         * @param weight - The weight of the node.
         * @complexity - O(1)
         */
        private void setWeight(int weight) {
            this.node.setWeight(weight);
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
         * Increse the total weight of the node.
         * @param inc - Amount to increse.
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
         * Compare between two ExtendedNodes based on total weight from higher to lower.
         * @other - Other node to compare to.
         * @complexity - O(1)
         */
        @Override
        public int compareTo(ExtendedNode other) {
            return Integer.compare(other.getTotalWeight(), this.getTotalWeight());
        }

        /**
         * Add the neighbouring relationsheep between two nodes.
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
         * Remove the neighbouring relationsheep between two nodes.
         * @param neighbours - Representation of this node and other node being neighbours.
         * @complexity - O(1)
         */
        private void deleteNeighbour(NeighbouringNodes neighbours) {
            DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode listNode = neighbours.getNodePointer(this);
            this.neighboursList.deleteNode(listNode);
            this.decTotalWeight(neighbours.getNeighbourNode(this).getWeight());
        }
    }

    private class NeighbouringNodes {
        private final ExtendedNode node1;
        private final ExtendedNode node2;
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer1;
        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer2;

        private NeighbouringNodes(ExtendedNode node1, ExtendedNode node2) {
            this.node1 = node1;
            this.node2 = node2;
            this.pointer1 = null;
            this.pointer2 = null;
        }

        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode getNodePointer(ExtendedNode node) {
            //TODO: implement this method.
            return null;
        }

        private DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode getNeighbourNodePointer(ExtendedNode node) {
            //TODO: implement this method.
            return null;
        }

        private ExtendedNode getNeighbourNode(ExtendedNode node) {
            //TODO: implement this method.
            return null;
        }

        private void setPointer(ExtendedNode node, DoublyLinkedList<NeighbouringNodes>.DoublyLinkedListNode pointer) {
            //TODO: implement this method.
        }
    }

    private interface BinaryHeapSelfIndex {
        /**
         * Get the index of the element in the binery heap.
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
         */
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

        private void heapifyArray() {
            for (int i = this.getElementsCount() - 1; i >= 0; i--) {
                this.bubbleDown(i);
            }
        }

        private void deleteNode(T elem) {
            int index = elem.getHeapIndex();
            this.deleteNode(index);
        }

        private void deleteNode(int index) {
            int lastElementIndex = this.getElementsCount() - 1;
            this.heap.set(this.heap.get(lastElementIndex), index);
            this.updateElementPos(index);
            this.decElementsCount();
        }

        private T getRoot() {
            if (this.getElementsCount() > 0) {
                return this.heap.get(0);
            }
            return null;
        }

        private void updateElementPos(T elem) {
            int index = elem.getHeapIndex();
            updateElementPos(index);
        }

        private void updateElementPos(int index) {
            this.updateElementPosRebaseRoot(index, 0);
        }

        private void updateElementPosRebaseRoot(int index, int rootIndex) {
            if (!isLegal(this.getParentIndexRebaseRoot(index, rootIndex), index)) {
                this.bubbleUp(index);
            }
            else if (!(isLegal(index, this.getLeftChildIndex(index)) && isLegal(index, getRightChildIndex(index)))) {
                this.bubbleDown(index);
            }
        }

        /**
         * Checks if the realtionsheep between parent and child in heap satisfies the order relationsheep.
         * @param parentIndex - Index of parent node.
         * @param childIndex - Index of child node.
         * @return - True if the relationsheep between parent and child is correct, otherwise false.
         * @complexity - O(1)
         */
        private boolean isLegal(int parentIndex, int childIndex) {
            if (parentIndex == -1 && childIndex == -1) {
                return true;
            }
            return this.heap.get(parentIndex).compareTo(this.heap.get(childIndex)) <= 0;
        }

        private int getMinIndex(int index1, int index2) {
            int cmp = this.heap.get(index1).compareTo(this.heap.get(index2));
            if (cmp >= 0) {
                return index2;
            }
            return index1;
        }

        private void bubbleUp(int index) {
            this.bubbleUpRebaseRoot(index, 0);
        }

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

        private int singleBubbleUp(int childIndex, int parentIndex, int rootIndex) {
            if (isLegal(parentIndex, childIndex)) {
                return childIndex;
            }
            this.changeHeapNodes(childIndex, parentIndex);
            return parentIndex;
        }

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

        private int singleBubbleDown(int parentIndex, int leftChildIndex, int rightChildIndex) {
            if (!(isLegal(parentIndex, leftChildIndex) && isLegal(parentIndex, rightChildIndex))) {
                int minChildIndex = this.getMinIndex(leftChildIndex, rightChildIndex);
                this.changeHeapNodes(parentIndex, minChildIndex);
                return minChildIndex;
            }
            return parentIndex;
        }

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
            this.incInitValuesCount();
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
