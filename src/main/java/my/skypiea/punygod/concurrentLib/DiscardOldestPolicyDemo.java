package my.skypiea.punygod.concurrentLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/7.
 */
import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

public class DiscardOldestPolicyDemo {

    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {

        // �����̳߳ء��̳߳ص�"���ش�С"��"���ĳش�С"��Ϊ1(THREADS_SIZE)��"�̳߳�"��������������Ϊ1(CAPACITY)��
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        // �����̳߳صľܾ�����Ϊ"DiscardOldestPolicy"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        // �½�10�����񣬲���������ӵ��̳߳��С�
        for (int i = 0; i < 10; i++) {
            Runnable myrun = new MyRunnable1("task-"+i);
            pool.execute(myrun);
        }
        // �ر��̳߳�
        pool.shutdown();
    }
}

class MyRunnable1 implements Runnable {
    private String name;
    public MyRunnable1(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " is running.");
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
