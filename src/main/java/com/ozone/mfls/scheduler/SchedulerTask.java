package com.ozone.mfls.scheduler;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @ClassName SchedulerTask
 * @Description TODO
 * @Author Ozone
 * @Date 2019/5/20 0020 22:37
 * @Version 1.0
 **/
@Component
public class SchedulerTask {
    @Scheduled(cron="0 0 1 * * ? ") //每天凌晨1点
    private void process(){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(DateTime.now().toDate())+"*********B任务每5秒执行一次进入测试");
    }
}
