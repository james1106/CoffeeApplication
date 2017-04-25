package com.mk.coffee.conf;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Executor线程池配置
 * Created by Administrator on 2017/4/12 0012.
 */
@Configuration
@EnableAsync
public class ExecutorConfig {
    /**
     * 设置ThreadPoolExecutor核心池大小。
     */
    private int corePoolSize = 10;
    /**
     * 设置ThreadPoolExecutor最大池大小。
     */
    private int maxPoolSize = 200;
    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    private int queueCapacity = 10;

    @Bean
    public Executor makeCoffeesAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("makeCoffeesExecutor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}
