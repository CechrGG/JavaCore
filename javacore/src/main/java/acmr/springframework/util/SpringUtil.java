package acmr.springframework.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
    private static ClassPathXmlApplicationContext ctx = null;
    public static ClassPathXmlApplicationContext getContext() {
        if(ctx == null) {
            ctx = new ClassPathXmlApplicationContext("config/spring-config.xml");
        }
        return ctx;
    }

    public static <T> T getBean(String name) {
        return (T) ctx.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return ctx.getBean(name, type);
    }
}
