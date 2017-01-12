package my.skypiea.punygod;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by punyGod on 16/8/31.
 */
public class InstrumentedSet<E> extends HashSet<E> {
    private int addCount = 0;


    @Override
    public boolean add(E e) {
        addCount ++ ;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        addCount += c.size() ;
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
