package acmr.springframework.util.timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;

public class TimerService {

    private final Logger logger = LogManager.getLogger(TimerService.class);
    private Map<String, String> cronMap = new HashMap<>();
    private Scheduler scheduler;

    public void addJob(String code, Class<? extends Job> job){
        try {
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(code, Scheduler.DEFAULT_GROUP).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(code, Scheduler.DEFAULT_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronMap.get(code))).build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("定时任务--" + code + "--初始化完成");
        } catch (SchedulerException e) {
            logger.info(e.getMessage());
        }
    }

    public void setCronMap(Map<String, String> cronMap) {
        this.cronMap = cronMap;
    }

    public void start() {
        try {
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
            scheduler.start();
        } catch (SchedulerException e) {
            logger.info(e.getMessage());
        }
    }

    public void stop() {
        try {
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
            scheduler.shutdown();
        } catch (SchedulerException e) {
            logger.info(e.getMessage());
        }
    }
}
