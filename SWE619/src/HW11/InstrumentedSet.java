package HW11;
import java.util.*;
public class InstrumentedSet<E> extends ForwardingSet<E> {
    //Instance variables
    private int addCount = 0;

    //Constructors
    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    //Methods
    @Override public boolean add(E e) {
        addCount++;
        return super.add(e);
    }
    public int getAddCount() {
        return addCount;
    }
}
