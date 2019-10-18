package cron.scheduler;

import cron.scheduler.dao.CronDao;
import cron.scheduler.domain.JobData;
import cron.scheduler.job.SmsJob;
import cron.scheduler.service.JobManager;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception{
        CronDao printDao = new CronDao();

        List<JobData> jobDataList = printDao.getHours();

        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i=0;

        for (JobData horas : jobDataList) {
            HashMap<String,String> param = new HashMap<>();


            Date dateInicial = formatter.parse(horas.getStartDate());
            LocalDateTime localDateTimeInicial = dateInicial.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            Date dateFinal = formatter.parse(horas.getEndDate());
            LocalDateTime localDateTimeFinal = dateFinal.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            param.put("startDate",localDateTimeInicial.toString());
            param.put("endDate",localDateTimeFinal.toString());

            JobManager.addJob("Job"+i, SmsJob.class,horas.getTaskName(),horas.getTaskName(),localDateTimeInicial,localDateTimeFinal,param);
            i++;
        }
    }
}
