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
 *   CopyOnWriteArrayList�ǡ��̰߳�ȫ���Ķ�̬���飬��ArrayList�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ���list����ʾ��
 *   (01) ��list��CopyOnWriteArrayList����ʱ���������������С�
 *   (02) ��list��ArrayList����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class CopyOnWriteArrayListTest1 {

    // TODO: list��ArrayList����ʱ����������
    private static List<String> list = new ArrayList<String>();
//   private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {


        // ͬʱ���������̶߳�list���в�����
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value = null;
        Iterator iter = list.iterator();
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
                String val = Thread.currentThread().getName()+"-"+i;
                list.add(val);
                // ͨ����Iterator������List��
                printAll();
            }
        }
    }
}
