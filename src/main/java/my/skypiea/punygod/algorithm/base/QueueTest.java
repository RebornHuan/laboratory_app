package my.skypiea.punygod.algorithm.base;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/3.
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedBlockingQueue<String>();

        queue.add("111");
        queue.add("222");
        queue.add("333");
        queue.add("444");

        for(String string : queue) {

            System.out.println(string);
        }
    }
}
