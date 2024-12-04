package deque;
import edu.princeton.cs.algs4.StdRandom;
import jh61b.junit.In;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    /**
     * basic add first and last
     * basic check for remove first and last
     */
    @Test
    public void addFirstAndLast() {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        s.addFirst(0);
        s.addLast(1);

        s.removeFirst();
        int t = s.get(0);
        //System.out.print(t);
        s.removeLast();
        s.get(0);
    }

    /**
     * test get method
     */
    @Test
    public void fillUpAndGet() {
        ArrayDeque<Integer> p = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            p.addFirst(i);
        }
        int t = p.get(0);
        assertEquals(7, t);
        int q = p.get(4);
        assertEquals(3, q);
    }

    /**
     * fill up the array using addFirst
     * resizing array when exceeds limit capacity
     * verify items are copied in the right order
     */
    @Test
    public void sizeUpAddFirstTest() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            q.addFirst(i);
        }
    }

    /**
     * fill up array using addLast
     * resizing array when exceeds limit capacity
     * verify items are copied in the right order
     */
    @Test
    public void sizeUpAddLastTest() {
        ArrayDeque<Integer> t = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            t.addLast(i);
        }
    }

    /**
     * randomly adding last and first
     * correct use of circular array
     */
    @Test
    public void sizeUpRandomCall() {
        ArrayDeque<Integer> f = new ArrayDeque<>();
        for (int i = 0; i < 9; i++) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                f.addLast(i);
            } else {
                f.addFirst(i);
            }
        }
    }

    /**
     * array reaches length 25
     * remove items until resizes down
     */
    @Test
    public void fillUpRemove() {
        ArrayDeque<Integer> h = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                h.addLast(i);
            } else {
                h.addFirst(i);
            }
        }
        for (int i = 0; i < 20; i++) {
            int operationNumber1 = StdRandom.uniform(0, 2);
            if (operationNumber1 == 0) {
                h.removeLast();
            } else {
                h.removeFirst();
            }
        }
    }

    /**
     * do not resize if the array has not exceeded length 8
     */
    @Test
    public void removeWhenLength8() {
        ArrayDeque<Integer> b = new ArrayDeque<>();
        for (int i = 0; i < 7; i++) {
            b.addFirst(i);
        }
        for (int i = 0; i < 6; i++) {
            b.removeLast();
        }
    }

    @Test
    public void randomAddRemove() {
        ArrayDeque<Integer> jf = new ArrayDeque<>();
        jf.addFirst(0);
        jf.removeLast();
        jf.addFirst(2);
        jf.isEmpty();
        jf.addFirst(4);
        jf.addFirst(5);
        jf.addFirst(6);
        int l = jf.removeLast();
        assertEquals(2, l);
    }

    @Test
    public void autogradertest() {
        ArrayDeque<Integer> j = new ArrayDeque<>();
        j.addFirst(0);
        j.addLast(1);
        j.removeFirst();     // 0
        j.removeLast();// 1
        j.addLast(4);
        int s = j.get(0);// null
        assertEquals(4, s);

    }

    @Test
    public void iteratorTestBasic() {
        ArrayDeque<Integer> z = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            z.addLast(i);
        }
        Iterator<Integer> seer = z.iterator();

        while (seer.hasNext()) {
            int i = seer.next();
            System.out.println(i);
        }
    }

    @Test
    public void equalsBasic() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        LinkedListDeque<Integer> b = new LinkedListDeque<>();

        for (int i = 0; i < 10; i++) {
            a.addLast(i);
            b.addLast(i);
        }

        boolean t = a.equals(b);
        boolean p = b.equals(a);
        assertTrue(t);
        assertTrue(p);
    }



}
