package acmr.javacore.basic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author guoguo
 * @version 1.0.0
 * 2022/5/4
 */
public class TraceHandler implements InvocationHandler {
    private final Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target + "." + method.getName() + "(" + Arrays.toString(args) + ")");
        return method.invoke(target, args);
    }
}
