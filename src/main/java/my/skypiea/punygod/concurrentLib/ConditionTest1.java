package my.skypiea.punygod.concurrentLib;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/3.
 */
public class ConditionTest1 {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        ThreadA ta = new ThreadA("ta");

        lock.lock(); // ��ȡ��
        try {
            System.out.println(Thread.currentThread().getName()+" start ta");
            ta.start();

            System.out.println(Thread.currentThread().getName()+" block");
            condition.await();    // �ȴ�

            System.out.println(Thread.currentThread().getName()+" continue");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();    // �ͷ���
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            lock.lock();    // ��ȡ��
            try {
                System.out.println(Thread.currentThread().getName()+" wakup others");
                condition.signal();    // ���ѡ�condition�������ϵ������̡߳�
            } finally {
                lock.unlock();    // �ͷ���
            }
        }
    }
}
