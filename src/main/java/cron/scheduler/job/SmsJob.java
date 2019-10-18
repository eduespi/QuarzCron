package cron.scheduler.job;

import cron.scheduler.dao.CronDao;
import cron.scheduler.domain.SmsData;
import org.quartz.*;

import java.util.List;

public class SmsJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        CronDao cronDao = new CronDao();

        String startDate = dataMap.getString("startDate");
        String endDate = dataMap.getString("endDate");

        try {
            List<SmsData> smsDatalist = cronDao.getData(startDate,endDate);
            for (SmsData smsData : smsDatalist) {

              ////////ENVIAR MENSAJE COLA JMS//////


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}