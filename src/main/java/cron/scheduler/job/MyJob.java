package cron.scheduler.job;

import org.quartz.*;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String param1 = dataMap.getString("NAME");
        String param2 = dataMap.getString("HORA");

        System.out.println(param1 + " " + param2);
        System.out.println(context.getJobDetail().getKey());

    }
}