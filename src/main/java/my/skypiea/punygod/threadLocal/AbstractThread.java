package my.skypiea.punygod.threadLocal;

import static java.lang.System.out;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/27.
 */
public class AbstractThread {

    public static void work(final ThreadLocal<String> local) {
        local.set("a");
        out.println(Thread.currentThread() + "," + local.get());
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                out.println(Thread.currentThread() + "," + local.get());
                local.set("b");
                out.println(Thread.currentThread() + "," + local.get());
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        out.println(Thread.currentThread() + "," + local.get());
    }
}
