package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    /**
     * nested class Iterator
     */
    private class LinkedListIterator<T> implements Iterator<T> {
        private int wizPos;
        Node p;

        private LinkedListIterator() {
            wizPos = 0;
            p = sentinel.next;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T toReturn = (T) p.item;
            wizPos++;
            p = p.next;
            return toReturn;
        }
    }

    /**
     * Iterator method
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator<>();
    }

    /**
     * nested class Node
     */
    private class Node {
        Node prev;
        Node next;
        T item;

        private Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    /**
     * instance variables
     */
    private Node sentinel;
    private Node front;
    private Node back;
    private int size;

    /**
     * instance methods
     */

    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
        front = sentinel;
        back = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            sentinel.next = new Node(sentinel, item, null);
            sentinel.prev = sentinel.next;
            front = sentinel.next;
            back = front;
        } else {
            sentinel.next = new Node(sentinel, item, front);
            front.prev = sentinel.next;
            front = sentinel.next;
        }
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.prev = new Node(sentinel, item, null);
            sentinel.next = sentinel.prev;
            front = sentinel.prev;
            back = front;
        } else {
            back.next = new Node(back, item, null);
            back = back.next;
            sentinel.prev = back;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node toPrint = sentinel.next;
        while (toPrint != null) {
            System.out.print(toPrint.item + " ");
            toPrint = toPrint.next;
        }
        System.out.println(" ");
    }

    public T removeFirst() {
        if (!isEmpty()) {
            Node frontToReturn = front;
            if (size() == 1) {
                frontToReturn.prev = null;
                front = sentinel;
                back = sentinel;
                sentinel.next = sentinel;
                sentinel.prev = sentinel;
            } else {
                frontToReturn.prev = null;
                sentinel.next = front.next;
                front.next.prev = sentinel;
                front = sentinel.next;
            }
            size--;
            return frontToReturn.item;
        }
        return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            Node backToReturn = back;
            if (size() == 1) {
                backToReturn.prev = null;
                front = sentinel;
                back = sentinel;
                sentinel.next = sentinel;
                sentinel.prev = sentinel;
            } else {
                sentinel.prev = back.prev;
                back.prev.next = null;
                back = sentinel.prev;
                backToReturn.prev = null;
            }
            size--;
            return backToReturn.item;
        }
        return null;
    }

    public T get(int index) {
        if (index > this.size() - 1 || index < 0) {
            return null;
        }
        Node temp = sentinel.next;
        while (temp != null && index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        T toReturn = getRecursiveHelper(sentinel.next, index);
        return toReturn;
    }

    private T getRecursiveHelper(Node temp, int index) {
        if (index == 0) {
            return temp.item;
        }
        return getRecursiveHelper(temp.next, index - 1);
    }

    public boolean equals(Object o) {
        if (o != null) {
            //arrayDeque
            if (o instanceof Deque) {
                Deque<T> other = (Deque<T>) o;
                if (this.size() != other.size()) {
                    return false;
                }
                if (other.isEmpty() && this.isEmpty()) {
                    return true;
                }
                for (int i = 0; i < this.size(); i++) {
                    if (!this.get(i).equals(other.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}



