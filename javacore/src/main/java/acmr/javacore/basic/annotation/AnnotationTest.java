package acmr.javacore.basic.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface AnnotationTest {
    RepeatableAnnotation[] value();
}

