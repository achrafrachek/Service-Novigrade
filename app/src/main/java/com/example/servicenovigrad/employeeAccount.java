package com.example.servicenovigrad;

public class employeeAccount  extends User{
    private String start1 ="N/A";
    private String start2 ="N/A";
    private String start3 ="N/A";
    private String end1 = "N/A";
    private String end2 = "N/A";
    private String end3 = "N/A";

    public employeeAccount() {
    }

    public employeeAccount(String id, String usertype, String username) {
        super(id, usertype, username);
    }

    public employeeAccount(String id, String usertype, String username, String start1, String start2, String start3, String end1, String end2, String end3) {
        super(id, usertype, username);
        this.start1 = start1;
        this.start2 = start2;
        this.start3 = start3;
        this.end1 = end1;
        this.end2 = end2;
        this.end3 = end3;
    }

    public String getStart1() {
        return start1;
    }

    public void setStart1(String start1) {
        this.start1 = start1;
    }

    public String getStart2() {
        return start2;
    }

    public void setStart2(String start2) {
        this.start2 = start2;
    }

    public String getStart3() {
        return start3;
    }

    public void setStart3(String start3) {
        this.start3 = start3;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    public String getEnd2() {
        return end2;
    }

    public void setEnd2(String end2) {
        this.end2 = end2;
    }

    public String getEnd3() {
        return end3;
    }

    public void setEnd3(String end3) {
        this.end3 = end3;
    }
}
