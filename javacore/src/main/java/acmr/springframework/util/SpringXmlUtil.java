package acmr.springframework.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlUtil {
    private static ClassPathXmlApplicationContext ctx = null;
    public static ClassPathXmlApplicationContext getContext() {
        if(ctx == null) {
            ctx = new ClassPathXmlApplicationContext("xmlConfig/spring-config.xml");

        }
        return ctx;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) getContext().getBean(name);
    }

    public static <T> T getBean(String name, Class<T> type) {
        return getContext().getBean(name, type);
    }
}
