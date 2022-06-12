package acmr.springframework;

import acmr.springframework.annotation.service.SlaveFactory;
import acmr.springframework.util.SpringAtUtil;
import acmr.springframework.util.SpringXmlUtil;
import acmr.springframework.util.timer.AnnotationTimerService;
import acmr.springframework.util.timer.XmlTimerService;
import acmr.springframework.xml.service.CatFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("铲屎官日常系统启动==>ServletContextListener.contextInitialized");
        XmlTimerService timerSrv = SpringXmlUtil.getBean("xmlTimerSrv", XmlTimerService.class);
        timerSrv.addJob("catfactory", CatFactory.class);
        AnnotationTimerService atTimerSrv = SpringAtUtil.getBean("atTimerSrv", AnnotationTimerService.class);
        atTimerSrv.addJob("slavefactory", SlaveFactory.class);
        timerSrv.start();
        atTimerSrv.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        XmlTimerService timerSrv = SpringXmlUtil.getBean("timerSrv", XmlTimerService.class);
        timerSrv.stop();
        AnnotationTimerService atTimerSrv = SpringAtUtil.getBean("atTimerSrv", AnnotationTimerService.class);
        atTimerSrv.stop();
    }
}
