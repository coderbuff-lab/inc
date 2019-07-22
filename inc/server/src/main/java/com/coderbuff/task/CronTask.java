package com.coderbuff.task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 * Created by OKevin on 2019-07-08 22:58
 **/
//@Component("cronTask")
public class CronTask {

    /**
     * 获取公司市值信息
     *
     */
    @Scheduled(cron = "* * * * * ?")
    public void fetchCompanyInfo() {
        //TODO
        System.out.println("定时获取");
    }
}
