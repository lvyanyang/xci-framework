/*
 * Copyright (c) 2007-2020 西安交通信息投资营运有限公司 版权所有
 */

package com.xci.component;

import com.xci.core.IAppStop;
import com.xci.core.XCI;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务服务
 */
@Component
public class AsyncService implements IAppStop {
    /** 操作延迟10毫秒 */
    private static final int OPERATE_DELAY_TIME = 10;
    /** 异步操作任务调度线程池 */
    private ScheduledExecutorService executor;
    private static AsyncService me;

    /**
     * 单例模式
     */
    private AsyncService() {
    }

    public static AsyncService me() {
        return me;
    }

    @PostConstruct
    private void init() {
        me = this;
    }

    /**
     * 执行任务
     * @param task 任务
     */
    public void execute(TimerTask task) {
        if (executor == null) {
            executor = XCI.getBean("scheduledExecutorService");
        }
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /** 停止 */
    @Override public void stop() {
        if (executor != null) {
            XCI.shutdownAndAwaitTermination(executor);
        }
    }
}
