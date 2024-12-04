package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;
    private T[] maxDeque;
    private int size;

    public MaxArrayDeque(Comparator<T> c) {
        maxDeque = (T[]) new Object[8];
        this.comp = c;
        size = 0;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        int maxD = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != null) {
                int p = comp.compare(this.get(i), this.get(maxD));
                if (p >= 0) {
                    maxD = i;
                }
            }
        }
        return this.get(maxD);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxD = 0;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) != null) {
                int p = c.compare(this.get(i), this.get(maxD));
                if (p >= 0) {
                    maxD = i;
                }
            }
        }
        return this.get(maxD);
    }
}
