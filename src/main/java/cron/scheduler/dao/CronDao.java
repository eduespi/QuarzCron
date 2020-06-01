package cron.scheduler.dao;



import cron.scheduler.domain.JobData;
import cron.scheduler.domain.SmsData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CronDao {


    public  List<JobData> getHours() throws Exception {

        List<JobData> jobDataList = new ArrayList<>();
        ResultSet rs;
        String query ="";

        String url="";
        String  usr = "";
        String  pass = "";

        try(Connection con = DriverManager.getConnection(url,usr,pass)){
            // Load Oracle JDBC Driver
           // DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Connect to Oracle Database

            System.out.println("Conectado a Base de Datos");
            Statement statement = con.createStatement();

            // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values
            // Enables us to retrieve values as if querying from a table
            rs = statement.executeQuery(query);
            while (rs.next()) {

                JobData jobData = new JobData();
                jobData.setTaskName(rs.getString(""));
                jobData.setEndDate(rs.getString(""));
                jobData.setStartDate(rs.getString(""));
                jobDataList.add(jobData);
            }
            System.out.println("Cantidad de registros "+jobDataList.size());
        } catch (Exception e) {
            System.out.println("Error en  Base de Datos "+e);
            throw new Exception(e);
        }
        return jobDataList;
    }


    public  List<SmsData> getData(String startDate, String endDate) throws Exception {

        List<SmsData> smsDataList = new ArrayList<>();
        ResultSet rs;
        String query ="";

        String url="";
        String  usr = "";
        String  pass = "";

        try(Connection con = DriverManager.getConnection(url,usr,pass)){
            // Load Oracle JDBC Driver
           // DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Connect to Oracle Database

            Statement statement = con.createStatement();

            // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values
            // Enables us to retrieve values as if querying from a table
            rs = statement.executeQuery(query);
            while (rs.next()) {

                SmsData smsData = new SmsData();
                smsData.setOpAssignedPhone(rs.getString(""));
                smsData.setOpCode(rs.getString(""));
                smsData.setOpName(rs.getString(""));
                smsData.setOprEvTycode(rs.getString(""));
                smsData.setOprEvTyName(rs.getString(""));

                smsDataList.add(smsData);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return smsDataList;
    }

}
