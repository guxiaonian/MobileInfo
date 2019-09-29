package com.mobile.mobilehardware.block;


public final class CpuInternals {

    private CpuSampler cpuSampler;

    private static CpuInternals sInstance;

    private long time = 1000L;

    private int MAX_ENTRY_COUNT = 10;

    private CpuInternals() {

    }

    public static CpuInternals getInstance() {
        if (sInstance == null) {
            synchronized (CpuInternals.class) {
                if (sInstance == null) {
                    sInstance = new CpuInternals();
                }
            }
        }
        return sInstance;
    }

    public CpuSampler getCpuSampler() {
        if(cpuSampler==null){
            cpuSampler = new CpuSampler(time);
        }
        return cpuSampler;
    }

    public CpuInternals setCpuRateTime(long time) {
        this.time = time;
        return this;
    }

    long getSampleDelay() {
        return (long) (time * 0.8f);
    }

     int getMaxEntryCount() {
        return MAX_ENTRY_COUNT;
    }

    public CpuInternals setMaxEntryCount(int MAX_ENTRY_COUNT) {
        this.MAX_ENTRY_COUNT = MAX_ENTRY_COUNT;
        return this;
    }
}
