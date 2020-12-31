package acmr.springframework.xml.service;

import acmr.springframework.util.SpringXmlUtil;
import acmr.springframework.util.StringUtil;
import acmr.springframework.xml.entity.Cat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Random;

public class CatFactory implements Job {

    private Logger logger = LogManager.getLogger(CatFactory.class);
    private final String openTime = "2020-12-31";   //开业时间

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("喵星人生产工厂,定期产出优质喵星人...");
        Cat cat = new Cat();
        try {
            long catNumber = StringUtil.getTimeStamp(openTime);
            cat.setName("虎妞" + catNumber);
            cat.setColor(new Random().nextInt(5));
            cat.setGmt_birthday(new Date());
            cat.setGmt_deathday(StringUtil.strToDate("9999-12-31"));
            cat.setSex(50 > new Random().nextInt(100) ? 'F' : 'M');
            cat.setLength((float)(10 * Math.random() + 1));
            cat.setWeight((float)(5 * Math.random() + 1));
            cat.setBreed(new Random().nextInt(10));
            cat.setMemo("初来乍到");
            cat.setGmt_create(new Date());
            cat.setGmt_update(new Date());
            EzCatService ezCatSrv = SpringXmlUtil.getBean("ezCatSrv", EzCatService.class);
            int count = ezCatSrv.register(cat);
            if(count == 1) {
                logger.info("成功产出喵星人" + catNumber + "号");
            } else {
                logger.info("残次品，直接过滤");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
