package my.skypiea.punygod;



import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//
//        InstrumentedSet<String> s = new InstrumentedSet<String>();
//        s.addAll(Arrays.asList("wang","huan","mint"));
//
//        System.out.println(s.getAddCount());

//        System.out.println(List<String>.class);


    }


    private static class InstrumentedSet<E> extends HashSet<E> {
        private int addCount = 0;


        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {

            addCount += c.size();
            return super.addAll(c);
        }

        public int getAddCount() {
            return addCount;
        }


    }

}
