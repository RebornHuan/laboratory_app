package my.skypiea.punygod;



import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static final int  RSLOCK     = 1;
    private static final int  RSIGNAL    = 1 << 1;
    private static final int  STARTED    = 1 << 2;
    private static final int  STOP       = 1 << 29;
    private static final int  TERMINATED = 1 << 30;
    private static final int  SHUTDOWN   = 1 << 31;
    public static void main(String[] args) {
//
//        InstrumentedSet<String> s = new InstrumentedSet<String>();
//        s.addAll(Arrays.asList("wang","huan","mint"));
//
//        System.out.println(s.getAddCount());

//        System.out.println(List<String>.class);

        System.out.println(RSLOCK);
        System.out.println(RSIGNAL);
        System.out.println(STARTED);
        System.out.println(STOP);
        System.out.println(TERMINATED);
        System.out.println(SHUTDOWN);

        System.out.println((RSLOCK|RSIGNAL)&RSLOCK);

        int a=1;
        int b=2;


        System.out.println(++a>=b);

        int n=4;
        n<<=1;

        System.out.println(n);




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
