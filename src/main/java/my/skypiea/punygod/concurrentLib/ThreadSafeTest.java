package my.skypiea.punygod.concurrentLib;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/4/6.
 */
public class ThreadSafeTest {

    static ConcurrentHashMap<String, HashMap<Integer, Set<String>>> test = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        init();
        new MyThread("ta").start();
        new MyThread("tb").start();
        new MyThread("tc").start();
        new MyThread("td").start();
        new MyThread("te").start();
        new MyThread("tf").start();
    }

    private static void init() {
        HashSet<String> test1 = new HashSet<>();
        Set<String> test2 = Collections.synchronizedSet(test1);

        HashMap<String,String> test4 = (HashMap<String, String>) Collections.synchronizedMap(new HashMap<String,String>());
        HashMap<Integer, Set<String>> test3 = new HashMap<>();
        test3.put(321, test2);
        test.put("wang", test3);
    }

    private static class MyThread extends Thread {

        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // ¡°Ïß³ÌÃû¡± + "-" + "ÐòºÅ"
                String val = Thread.currentThread().getName() + i;
                HashMap<Integer, Set<String>> testt_1 = test.get("wang");
              //  System.out.println(val + testt_1);
                Set<String> testt_2 = testt_1.get(321);
                System.out.println("size + " + testt_2.size());
                testt_2.add(val);


            }
        }
    }


}
