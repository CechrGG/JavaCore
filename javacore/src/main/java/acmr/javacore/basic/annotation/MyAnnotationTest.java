package acmr.javacore.basic.annotation;

public class MyAnnotationTest {
    public static void main(String[] args) {
        Class<MyAnnotattion> annotation = MyAnnotattion.class;
        boolean annotationPresent = annotation.isAnnotationPresent(RepeatableAnnotation.class);
//        if(annotationPresent) {
            RepeatableAnnotation[] repeatableAnnotation = annotation.getDeclaredAnnotationsByType(RepeatableAnnotation.class);
            for (RepeatableAnnotation ra : repeatableAnnotation) {
                System.out.println(ra.name());
                System.out.println(ra.age());
            }
//        }
    }
}
