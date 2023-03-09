package ekko.spring.v1.core.Annotation;

import java.lang.annotation.*;

/**
 * @Author yll
 * @Date 2023/3/6 16:56
 * @PackageName:ekko.spirng.v1.core.Annotation
 * @ClassName: Componet
 * @Description: TODO
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
    String value() default "";
}
