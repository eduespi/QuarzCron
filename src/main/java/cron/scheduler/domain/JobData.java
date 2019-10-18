package cron.scheduler.domain;


public class JobData {

    private String startDate;
    private String endDate;
    private String taskName;

    public JobData(String startDate, String endDate, String taskName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskName = taskName;
    }

    public JobData() { }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
