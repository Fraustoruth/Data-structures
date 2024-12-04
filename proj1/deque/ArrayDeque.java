package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int front;
    private int back;

    /**
     * nested Iterator class
     */
    private class ArrayIterator<T> implements Iterator<T> {

        private int wizPos;
        private int frontTemp;
        private int backTemp;

        private  ArrayIterator() {
            wizPos = 0;
            frontTemp = preFront(front);
            backTemp = preBack(back);
        }

        @Override
        public boolean hasNext() {
            return wizPos < size();
        }

        @Override
        public T next() {
            T toReturn;
            if (items[frontTemp] != null) {
                toReturn = (T) items[frontTemp];
                frontTemp = preFront(frontTemp);
            } else {
                toReturn = (T) items[backTemp];
                backTemp = preBack(backTemp);
            }
            wizPos++;
            return toReturn;
        }
    }

    /**
     * Iterator method
     */
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    public ArrayDeque() {

        items = (T[]) new Object[8];
        size = 0;
        front = 4;
        back = 4;

    }

    public void addFirst(T item) {

        //checking if there is still space
        resizeAdding();
        //adding item to our current front
        if (isEmpty()) {
            items[front] = item;
            if (front == back) {
                back = nextBack(back);
            }

        } else {
            items[front] = item;
        }
        //updating front for next input
        front = nextFront(front);
        size++;

    }

    //compute position for item to be inserted at the front
    private int nextFront(int nxFront) {
        return Math.floorMod((nxFront - 1), items.length);
    }

    public void addLast(T item) {
        //checking if array is not full
        resizeAdding();
        //inputting item to our current last position
        if (isEmpty()) {
            items[back] = item;
            if (front == back) {
                front = nextFront(front);
            }

        } else {
            items[back] = item;
        }
        //updating back position for next last addition
        back = nextBack(back);
        size++;
    }

    //compute position for item to be inserted at the back
    private int nextBack(int nxBack) {
        return Math.floorMod((nxBack + 1), items.length);
    }

    /**
     * double its size if the array is at its maximun capacity
     * OR downsize the array if usage is < .25
     */
    private void resizeAdding() {
        if ((size) == items.length - 1) {
            sizeUp();
        }
    }

    private void resizeRemoving() {
        if (size < (items.length / 4) && items.length > 8) {
            sizeDown();
        }
    }

    /**
     * copy items to a new array depending the newSize using front and back
     * set a new front and back
     */
    private void sizeUp() {
        int rangeFrontToEnd = (items.length - front);
        T[] temp = (T[]) new Object[size * 2];
        System.arraycopy(items, front + 1, temp, 0, rangeFrontToEnd - 1);
        System.arraycopy(items, 0, temp, rangeFrontToEnd - 1, items.length - rangeFrontToEnd + 1);
        items = temp;
        front = 0;
        front = nextFront(front);
        back = size - 1;
        back = nextBack(back);
    }

    private void sizeDown() {
        int numItemsToCopy = (back) - (front + 1);
        T[] temp;
        if (items.length / 2 < 8) {
            temp = (T[]) new Object[8];
        } else {
            temp = (T[]) new Object[items.length / 2];
        }
        System.arraycopy(items, front + 1, temp, 0, numItemsToCopy);
        items = temp;
        front = 0;
        front = nextFront(front);
        back = size - 1;
        back = nextBack(back);

    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            int start = 0;
            T toPrint = get(start);
            while (toPrint != null) {
                System.out.println(toPrint + " ");
                toPrint = get(start + 1);
            }
        }
        System.out.println();
    }

    public T removeLast() {
        if (!isEmpty()) {
            back = preBack(back);
            T toReturn = items[back];
            items[back] = null;
            //back = preBack(back);
            size--;
            resizeRemoving();
            return toReturn;
        }
        return null;
    }

    private int preBack(int prBack) {
        return Math.floorMod(prBack - 1, items.length);
    }

    public T removeFirst() {
        if (!isEmpty()) {
            front = preFront(front);
            T toReturn = items[front];
            items[front] = null;
            //front = preFront(front);
            size--;
            resizeRemoving();
            return toReturn;
        }
        return null;
    }

    private int preFront(int prFront) {
        return Math.floorMod((prFront + 1), items.length);
    }

    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        int temp = preFront(front);
        int count = 0;
        while (index != count && index >= 0) {
            T itemToCheck = items[temp];
            if (itemToCheck != null) {
                temp = preFront(temp);
                count++;
            } else {
                count++;
                temp = preFront(temp);
            }
        }
        if (items[temp] == null) {
            return null;
        }
        return items[temp];
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


