package com.github.qq44920040.mc.Entity;

public class GroupCmd {
    private String cmd;
    private boolean ignorePermission;
    private double Delay;
    private boolean IsPlayerl;
    private String Msg;

    @Override
    public String toString() {
        return "GroupCmd{" +
                "cmd='" + cmd + '\'' +
                ", ignorePermission=" + ignorePermission +
                ", Delay=" + Delay +
                ", IsPlayerl=" + IsPlayerl +
                ", Msg='" + Msg + '\'' +
                '}';
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public boolean isIgnorePermission() {
        return ignorePermission;
    }

    public void setIgnorePermission(boolean ignorePermission) {
        this.ignorePermission = ignorePermission;
    }

    public double getDelay() {
        return Delay;
    }

    public void setDelay(double delay) {
        Delay = delay;
    }

    public boolean isPlayerl() {
        return IsPlayerl;
    }

    public void setPlayerl(boolean playerl) {
        IsPlayerl = playerl;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public GroupCmd(String cmd, boolean ignorePermission, double delay, boolean isPlayerl, String msg) {
        this.cmd = cmd;
        this.ignorePermission = ignorePermission;
        Delay = delay;
        IsPlayerl = isPlayerl;
        Msg = msg;
    }
}
