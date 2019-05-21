package com.mobile.mobilehardware.block;

/**
 * @author gunaonian
 */
public class CpuBean {

    private String cpu;
    private String app;
    private String user;
    private String system;
    private String ioWait;

    public CpuBean() {
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getIoWait() {
        return ioWait;
    }

    public void setIoWait(String ioWait) {
        this.ioWait = ioWait;
    }

    @Override
    public String toString() {
        return "CpuBean{" +
                "cpu='" + cpu + '\'' +
                ", app='" + app + '\'' +
                ", user='" + user + '\'' +
                ", system='" + system + '\'' +
                ", ioWait='" + ioWait + '\'' +
                '}';
    }
}
