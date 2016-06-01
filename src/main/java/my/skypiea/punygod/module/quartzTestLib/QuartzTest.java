package my.skypiea.punygod.module.quartzTestLib;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/14.
 */

import java.text.ParseException;
import java.util.*;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest implements Job {



    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("Generating report - "
                + ", name ="+arg0.getJobDetail().getKey().getName()
                + ", group ="+arg0.getJobDetail().getKey().getGroup()
                + ", type =" + arg0.getJobDetail().getJobDataMap().get("type"));
        System.out.println(new Date().toString());


    }

    public static void main(String[] args) throws SchedulerException, ParseException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzTest.class)
                .withIdentity("testJob_1", "group_1")
                .build();

/*        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1", "group_1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10) //时间间隔
                        .withRepeatCount(5)        //重复次数(将执行6次)
                )
                .build();*/


/*
        java.util.Calendar rightNow = java.util.Calendar.getInstance();
        rightNow.setTime(new Date());

        rightNow.add(java.util.Calendar.DAY_OF_YEAR,-1);

        rightNow.add(Calendar.MINUTE,-20);
        rightNow.set(Calendar.SECOND,0);

        Date current = rightNow.getTime();

        System.out.println(current);

        CronExpression expr = new CronExpression("* 2/14 * * * ?");
        Date date = expr.getNextValidTimeAfter(current);

        System.out.println(date.toString());*/

 /*       System.out.println(expr.getNextInvalidTimeAfter(current).toString());*/


      //  System.out.println((expr.getTimeAfter(new Date())).toString());

/*        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1", "group_1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("*//*7 * * * * ?")
                )
                .build();*/


        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1", "group_1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("7 * * * * ?")
                )
                .build();


        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        sched.scheduleJob(jobDetail, trigger);

        sched.start();
    }
}
