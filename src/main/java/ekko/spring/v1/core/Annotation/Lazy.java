package ekko.spring.v1.core.Annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author yll
 * @Date 2023/3/9 20:55
 * @PackageName:ekko.spring.v1.core.Annotation
 * @ClassName: Lazy
 * @Description: TODO
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Lazy {
    boolean value() default true;
}
