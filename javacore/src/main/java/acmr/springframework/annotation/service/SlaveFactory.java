package acmr.springframework.annotation.service;

import acmr.springframework.annotation.entity.Slave;
import acmr.springframework.util.SpringAtUtil;
import acmr.springframework.util.StringUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Random;

public class SlaveFactory implements Job {
    private final Logger logger = LogManager.getLogger(SlaveFactory.class);
    private final String openTime = "2020-12-31";   //开业时间
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("奴隶生产工厂,定期生成优秀铲屎官...");
        try {
            Slave slave = new Slave();
            long slaveNumber = StringUtil.getTimeStamp(openTime);
            slave.setName("铲屎官" + slaveNumber);
            slave.setMobile(StringUtil.getMobile());
            slave.setSex(70 > new Random().nextInt(100) ? 'F' : 'M');
            slave.setGmt_birthday(new Date());
            slave.setGmt_enslaved(StringUtil.strToDate("9999-12-31"));
            slave.setMemo("闻屎而来");
            slave.setGmt_create(new Date());
            slave.setGmt_update(new Date());
            SlaveService slaveSrv = SpringAtUtil.getBean("slaveSrv", SlaveService.class);
            int count = slaveSrv.registerSlave(slave);
            if(count == 1) {
                logger.info("成功生产优秀铲屎官" + slaveNumber + "号");
            } else {
                logger.warn("注意! 生产出傻缺铲屎官, 直接回笼");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
