package my.skypiea.punygod.concurrentLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/4.
 */
import java.util.*;
import java.util.concurrent.*;

/*
 *   CopyOnWriteArraySet�ǡ��̰߳�ȫ���ļ��ϣ���HashSet�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ�������set����ʾ��
 *   (01) ��set��CopyOnWriteArraySet����ʱ���������������С�
 *   (02) ��set��HashSet����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class CopyOnWriteArraySetTest1 {

    // TODO: set��HashSet����ʱ����������
    //private static Set<String> set = new HashSet<String>();
    private static Set<String> set = new CopyOnWriteArraySet<String>();
    public static void main(String[] args) {

        // ͬʱ���������̶߳�set���в�����
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value = null;
        Iterator iter = set.iterator();
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
            while (i++ < 10) {
                // ���߳����� + "-" + "���"
                String val = Thread.currentThread().getName() + "-" + (i%6);
                System.out.println("reborn  .. :"+Thread.currentThread().getName());
                set.add(val);
                // ͨ����Iterator������set��
                printAll();
            }
        }
    }
}
