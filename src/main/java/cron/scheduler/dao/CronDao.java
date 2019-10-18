package cron.scheduler.dao;



import cron.scheduler.domain.JobData;

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
        String query ="SELECT distinct to_char(oet.opr_ev_ty_start_date, 'yyyy-MM-dd HH24:MI:SS')as facha_inicio,\n" +
                "to_char(oet.opr_ev_ty_finish_date, 'yyyy-MM-dd HH24:MI:SS') as fecha_fin,\n" +
                "oet.OPR_EV_TY_NAME as nombre_tarea\n" +
                "FROM opr_ev_type oet order by 1";

        String url="jdbc:oracle:thin:@192.168.12.81:1521:xe";
        String  usr = "AES";
        String  pass = "AES";

        try(Connection con = DriverManager.getConnection(url,usr,pass)){
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Connect to Oracle Database

            System.out.println("Conectado a Base de Datos");
            Statement statement = con.createStatement();

            // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values
            // Enables us to retrieve values as if querying from a table
            rs = statement.executeQuery(query);
            while (rs.next()) {

                JobData jobData = new JobData();
                jobData.setTaskName(rs.getString("nombre_tarea"));
                jobData.setEndDate(rs.getString("fecha_fin"));
                jobData.setStartDate(rs.getString("facha_inicio"));
                jobDataList.add(jobData);
            }
            System.out.println("Cantidad de registros "+jobDataList.size());
        } catch (Exception e) {
            System.out.println("Error en  Base de Datos "+e);
            throw new Exception(e);
        }
        return jobDataList;
    }


    public  List<JobData> getData() throws Exception {

        List<JobData> jobDataList = new ArrayList<>();
        ResultSet rs;
        String query ="SELECT op.op_code,\n" +
                "       op.op_name,\n" +
                "       op.assigned_phone,\n" +
                "       oet.opr_ev_ty_code,\n" +
                "       oet.opr_ev_ty_name\n" +
                "FROM operative_event oe, opr_ev_type oet, operator op, opr_ev_type_operator_type oetot\n" +
                "WHERE to_char(oet.opr_ev_ty_start_date, 'yyyy-MM-dd HH24:MI:SS') =  '2019-05-29 07:30:00'\n" +
                "  AND to_char(oet.opr_ev_ty_finish_date, 'yyyy-MM-dd HH24:MI:SS') = '2019-05-29 07:45:00'\n" +
                "  AND oe.opr_ev_ty_code = oet.opr_ev_ty_code\n" +
                "  AND op.op_code = oe.op_code\n" +
                "  AND oetot.opr_ev_ty_code = oe.opr_ev_ty_code\n" +
                "  AND oetot.op_ty_code = op.op_ty_code\n" +
                "  AND oe.opr_ev_confirmed_date IS NULL";

        String url="jdbc:oracle:thin:@192.168.12.81:1521:xe";
        String  usr = "AES";
        String  pass = "AES";

        try(Connection con = DriverManager.getConnection(url,usr,pass)){
            // Load Oracle JDBC Driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Connect to Oracle Database

            System.out.println("Conectado a Base de Datos");
            Statement statement = con.createStatement();

            // Execute a SELECT query on Oracle Dummy DUAL Table. Useful for retrieving system values
            // Enables us to retrieve values as if querying from a table
            rs = statement.executeQuery(query);
            while (rs.next()) {

                JobData jobData = new JobData();
                jobData.setTaskName(rs.getString("nombre_tarea"));
                jobData.setEndDate(rs.getString("fecha_fin"));
                jobData.setStartDate(rs.getString("facha_inicio"));
                jobDataList.add(jobData);
            }
            System.out.println("Cantidad de registros "+jobDataList.size());
        } catch (Exception e) {
            System.out.println("Error en  Base de Datos "+e);
            throw new Exception(e);
        }
        return jobDataList;
    }









}
