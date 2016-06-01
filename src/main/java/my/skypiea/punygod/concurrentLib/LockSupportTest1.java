package my.skypiea.punygod.concurrentLib;
import java.util.concurrent.locks.LockSupport;
/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/3.
 */

public class LockSupportTest1 {

    private static Thread mainThread;

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");
        // ��ȡ���߳�
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start ta");
        ta.start();

        System.out.println(Thread.currentThread().getName()+" block");
        // ���߳�����
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakup others");
            // ���ѡ����̡߳�
            LockSupport.unpark(mainThread);
        }
    }
}
