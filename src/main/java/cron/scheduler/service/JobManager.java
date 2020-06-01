package cron.scheduler.service;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;


public class JobManager  {

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";



    public static void addJob(String jobName, Class cls, String jobGroupName, String triggerName,
                              LocalDateTime startDate, LocalDateTime endDate, HashMap<String, String> param) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail job = newJob(cls)
                    .withIdentity(jobName, jobGroupName)
                    .build();

            for(Map.Entry<String, String> entry : param.entrySet()) {
                job.getJobDataMap().put(entry.getKey(), entry.getValue());
            }

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(jobName, triggerName)
                    .withSchedule(simpleSchedule()
                           /// .withIntervalInSeconds(10)
                              .withIntervalInMinutes(10)
                            .repeatForever())
                    .startAt(dateOf(startDate.getHour(),startDate.getMinute()
                            ,startDate.getSecond(),startDate.getDayOfMonth(),startDate.getMonth().getValue(),startDate.getYear()))
                    .endAt(dateOf(endDate.getHour(),endDate.getMinute()
                            ,endDate.getSecond(),endDate.getDayOfMonth(),endDate.getMonth().getValue(),endDate.getYear())).build();

            sched.scheduleJob(job, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


      public static void modifyJobTime(String jobName, String time) {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                jobName, TRIGGER_GROUP_NAME);

        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger =(CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                CronScheduleBuilder scheduleBuilder =CronScheduleBuilder.cronSchedule(time);

                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                sched.rescheduleJob(triggerKey, trigger);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void modifyJobTime(String triggerName,
                                     String triggerGroupName, String time) {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                triggerName, triggerGroupName);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {

                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                        .cronSchedule(time);

                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();

                sched.resumeTrigger(triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void removeJob(String jobName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                jobName, TRIGGER_GROUP_NAME);
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            Trigger trigger = (Trigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            sched.pauseTrigger(triggerKey);
            sched.unscheduleJob(triggerKey);
            sched.deleteJob(jobKey);
            System.out.println("JOB REMOVED"+jobName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void removeJob(String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(
                jobName, triggerGroupName);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseTrigger(triggerKey);
            sched.unscheduleJob(triggerKey);
            sched.deleteJob(jobKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void pauseJob(String jobName, String jobGroupName) {
        JobKey jobKey =JobKey.jobKey(jobName, jobName);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseJob(jobKey);
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void pauseJob(String jobName) {
        JobKey jobKey =JobKey.jobKey(jobName, JOB_GROUP_NAME);
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}