package my.skypiea.punygod.module.cron4jLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/14.
 */
import it.sauronsoftware.cron4j.Scheduler;

import java.util.Date;

public class Cron4jTest {

    public static void main(String[] args) {
        // Creates a Scheduler instance.
        Scheduler s = new Scheduler();
        // Schedule a once-a-minute task.
        s.schedule("*/7 * * * *", new Runnable() {
            public void run() {

                System.out.println(new Date());
                System.out.println("Another minute ticked away...");
            }
        });
        // Starts the scheduler.
        s.start();
        // Will run for ten minutes.
        try {
            Thread.sleep(1000L * 60L * 10L);
        } catch (InterruptedException e) {
        }
        // Stops the scheduler.
        s.stop();
    }

}
