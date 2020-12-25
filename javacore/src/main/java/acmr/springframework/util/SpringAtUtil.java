package acmr.springframework.util;

import acmr.springframework.annotation.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAtUtil {
    private static AnnotationConfigApplicationContext ctx = null;
    public static AnnotationConfigApplicationContext getContext() {
        if(ctx == null) {
            ctx = new AnnotationConfigApplicationContext(AppConfig.class);
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
