package cron.scheduler.domain;

public class SmsData {

    private String opCode;
    private String opName;
    private String opAssignedPhone;
    private String oprEvTycode;
    private String oprEvTyName;

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public String getOpAssignedPhone() {
        return opAssignedPhone;
    }

    public void setOpAssignedPhone(String opAssignedPhone) {
        this.opAssignedPhone = opAssignedPhone;
    }

    public String getOprEvTycode() {
        return oprEvTycode;
    }

    public void setOprEvTycode(String oprEvTycode) {
        this.oprEvTycode = oprEvTycode;
    }

    public String getOprEvTyName() {
        return oprEvTyName;
    }

    public void setOprEvTyName(String oprEvTyName) {
        this.oprEvTyName = oprEvTyName;
    }
}
