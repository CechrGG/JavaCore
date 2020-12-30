package acmr.springframework;

import acmr.springframework.util.SpringXmlUtil;
import acmr.springframework.util.timer.TimerService;
import acmr.springframework.xml.service.CatFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    private final Logger logger = LogManager.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("铲屎官日常系统启动==>ServletContextListener.contextInitialized");
        TimerService timerSrv = SpringXmlUtil.getBean("timerSrv", TimerService.class);
        timerSrv.addJob("catfactory", CatFactory.class);
        timerSrv.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        TimerService timerSrv = SpringXmlUtil.getBean("timerSrv", TimerService.class);
        timerSrv.stop();
    }
}
