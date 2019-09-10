package com.mobile.mobileinfo.util;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ThreadPoolUtil {

    /**
     * 单利类
     **/
    private static ThreadPoolUtil singleton;
    /**
     * android自带封装线程池
     **/
    private static Executor mExecutor;
    /**
     * 线程数
     */
    private static final int GTM_THREAD_LENGTH = 10;

    /**
     * 初始化线程池
     *
     * @return
     */
    public static ThreadPoolUtil getInstance() {
        if (singleton == null) {
            synchronized (ThreadPoolUtil.class) {
                if (singleton == null) {
                    singleton = new ThreadPoolUtil();
                    mExecutor = Executors.newFixedThreadPool(GTM_THREAD_LENGTH);
                }
            }
        }
        return singleton;
    }

    /**
     * 开启runnable
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        try {
            if (runnable != null) {
                mExecutor.execute(runnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
