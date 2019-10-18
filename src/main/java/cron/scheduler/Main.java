package cron.scheduler;

import cron.scheduler.dao.CronDao;
import cron.scheduler.domain.JobData;
import cron.scheduler.job.MyJob;
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

        HashMap<String,String> param = new HashMap<>();
        param.put("NAME","PEPE");
        param.put("HORA","xxx");

        List<JobData> jobDataList = printDao.getHours();
        SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i=0;

        for (JobData horas : jobDataList) {

            Date dateInicial = formatter.parse(horas.getStartDate());

            LocalDateTime localDateTimeInicial = dateInicial.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            Date dateFinal = formatter.parse(horas.getEndDate());
            LocalDateTime localDateTimeFinal = dateFinal.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            JobManager.addJob("Job"+i, MyJob.class,horas.getTaskName(),horas.getTaskName(),localDateTimeInicial,localDateTimeFinal,param);
            i++;
        }

    }

}
