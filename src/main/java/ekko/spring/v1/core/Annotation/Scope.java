package ekko.spring.v1.core.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author yll
 * @Date 2023/3/9 20:56
 * @PackageName:ekko.spring.v1.core.Annotation
 * @ClassName: Scope
 * @Description: TODO
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    String  value() default "singleton" ; // prototype 原型
}
