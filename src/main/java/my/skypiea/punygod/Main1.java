package my.skypiea.punygod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/25.
 */
public class Main1 {

    public static void main(String[] args) throws Exception {

        MyThread myThread = new MyThread();
        myThread.start();

        myThread.interrupt();
        myThread.join();

    }

    static class MyThread extends Thread {


        @Override
        public void run() {

            int i=0;

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("end");
        }


    }
}
