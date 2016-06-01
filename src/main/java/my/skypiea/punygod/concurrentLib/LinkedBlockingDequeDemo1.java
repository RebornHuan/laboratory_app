package my.skypiea.punygod.concurrentLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/7.
 */
import java.util.*;
import java.util.concurrent.*;

/*
 *   LinkedBlockingDeque�ǡ��̰߳�ȫ���Ķ��У���LinkedList�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ���queue����ʾ��
 *   (01) ��queue��LinkedBlockingDeque����ʱ���������������С�
 *   (02) ��queue��LinkedList����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class LinkedBlockingDequeDemo1 {

    // TODO: queue��LinkedList����ʱ����������
    //private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queue = new LinkedBlockingDeque<String>();
    public static void main(String[] args) {

        // ͬʱ���������̶߳�queue���в�����
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // ���߳����� + "-" + "���"
                String val = Thread.currentThread().getName()+i;
                queue.add(val);
                // ͨ����Iterator������queue��
                printAll();
            }
        }
    }
}
