/** Array based list.
 *  @author Josh Hug
 *  Modified by George Sun
 */
public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int length;
    private int front;
    private int rear;

    /** Creates an empty list. */
    public ArrayDeque() {
        length = 8;
        items = (T []) new Object[length];
        size = 0;
        front = 4;
        rear = 4;
    }
    /** Resizes the underlying array to the target capacity . */
    private void expand() {
        T[] a = (T []) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != rear) {
            a[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        front = length;
        rear = ptr2;
        items = a;
        length *= 2;
    }
    private void shrink() {
        T[] a = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != rear) {
            a[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        rear = ptr2;
        items = a;
        length /= 2;
    }
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int len) {
        index %= len;
        if (index == len - 1) {
            return 0;
        }
        return index + 1;
    }
    /** Inserts X to the front of the list. */
    public void addFirst(T x) {
        if (size == items.length - 1) {
            expand();
        }
        front = minusOne(front);
        items[front] = x;
        size++;
    }
    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if (size == items.length - 1) {
            expand();
        }
        items[rear] = x;
        rear = plusOne(rear, length);
        size++;
    }
    /** Deletes item from front of the list and
     * returns deleted item. */
    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ret = items[front];
        front = plusOne(front, length);
        size--;
        return ret;
    }
    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        rear = minusOne(rear);
        size--;
        return items[rear];
    }
    /** Gets the ith item in the list (0 is the front). */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return items[ptr];
    }
    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        int ptr = front;
        while (ptr != rear) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}
