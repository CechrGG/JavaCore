package acmr.javacore.basic.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Repeatable(AnnotationTest.class)
public @interface RepeatableAnnotation {
    String name() default "名称";
    int age() default 0;
}
