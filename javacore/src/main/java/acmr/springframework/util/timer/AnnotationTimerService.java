package acmr.springframework.util.timer;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Properties;

@Service("atTimerSrv")
public class AnnotationTimerService {

    private final Logger logger = LoggerFactory.getLogger(AnnotationTimerService.class);
    @Resource(name = "crons")
    private Properties crons;
    private Scheduler scheduler;

    public void addJob(String code, Class<? extends Job> job){
        try {
            if (scheduler == null) {
                scheduler = StdSchedulerFactory.getDefaultScheduler();
            }
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(code, Scheduler.DEFAULT_GROUP).build();
            String cron = crons.getProperty(code);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(code, Scheduler.DEFAULT_GROUP)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("定时任务--" + code + "--初始化完成");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Bean("crons")
    public PropertiesFactoryBean getCrons() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("timer.properties"));
        return propertiesFactoryBean;
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
