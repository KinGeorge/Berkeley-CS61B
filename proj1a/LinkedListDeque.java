public class LinkedListDeque<T> {
    private class Node {
        /** the item stored on this node. */
        private T item;
        /** the Node before this Node. **/
        private Node prev;
        /** the Node after this Node. **/
        private Node next;

        /** constructor for Node. */
        public Node(T n, Node pprev, Node nnext) {
            item = n;
            prev = pprev;
            next = nnext;
        }

        /** constructor for Node.(especially for sentinel node). */
        public Node(Node pprev, Node nnext) {
            prev = pprev;
            next = nnext;
        }
    }
    /** sentinel node. */
    private Node sentinel;
    /** size of the deque. */
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        Node newList = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newList;
        sentinel.next = newList;
        size++;
    }
    public void addLast(T item) {
        Node newList = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newList;
        sentinel.prev = newList;
        size++;

    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return ret;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return ret;
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }
    private T getRecursive(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursive(start.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }
    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }
    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }
}
