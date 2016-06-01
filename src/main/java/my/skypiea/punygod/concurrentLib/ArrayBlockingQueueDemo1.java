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
 *   ArrayBlockingQueue�ǡ��̰߳�ȫ���Ķ��У���LinkedList�Ƿ��̰߳�ȫ�ġ�
 *
 *   �����ǡ�����߳�ͬʱ�������ұ���queue����ʾ��
 *   (01) ��queue��ArrayBlockingQueue����ʱ���������������С�
 *   (02) ��queue��LinkedList����ʱ����������ConcurrentModificationException�쳣��
 *
 * @author skywang
 */
public class ArrayBlockingQueueDemo1{



    private static ThreadLocal<Test> seqNum = new ThreadLocal<Test>() {
        Test  aa ;
        @Override
        public Test get() {

            if(aa== null) {
                aa = new Test();
            }

            return  aa;
        }

        @Override
        public void remove() {
            super.remove();
        }


    };

    static class Test{
        String x;
        String y;

        public Test() {
        }

        public Test(String x, String y) {
            this.x = x;
            this.y = y;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "x='" + x + '\'' +
                    ", y='" + y + '\'' +
                    '}';
        }
    }

    // TODO: queue��LinkedList����ʱ����������
    //private static Queue<String> queue = new LinkedList<String>();
    private static Queue<String> queue = new ArrayBlockingQueue<String>(20);
    public static void main(String[] args) {
/*
        // ͬʱ���������̶߳�queue���в�����
        new MyThread("ta").start();
        new MyThread("tb").start();*/

/*        Test test1 = new Test("1","2");

        MyThread1 myThread1 = new MyThread1(test1);
        MyThread2 myThread2 = new MyThread2(test1);

        myThread1.start();
        myThread2.start();*/
        Test test1 = new Test("1","2");
        Test test2 = test1;

        Test test3 = test2;

        test1  = new Test("2","3");

        System.out.println(test3.toString());

        test1.setX("2");

        System.out.println(test3.toString());
        System.out.println(test1.toString());
        System.out.println(test2.toString());





/*        Test test = seqNum.get();

        test.setX("wang");
        test.setY("haun");

        System.out.println(seqNum.get().toString());*/







    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value + ", ");
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

    private static class MyThread1 extends Thread {

        Test test;
        MyThread1(Test test) {
            this.test = test;
        }
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test.setX("1111");
            System.out.println("end");
        }
    }


    private static class MyThread2 extends Thread {

        Test test;
        MyThread2(Test test) {
            this.test = test;
        }
        @Override
        public void run() {
            Test test2 = test;

            Test test3 = test2;

            System.out.println(test3.toString());

            System.out.println(test.toString());
            System.out.println(test3.toString());
        }
    }
}