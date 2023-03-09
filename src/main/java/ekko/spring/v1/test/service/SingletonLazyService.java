package ekko.spring.v1.test.service;

import ekko.spring.v1.core.Annotation.Component;
import ekko.spring.v1.core.Annotation.ComponentScan;
import ekko.spring.v1.core.Annotation.Lazy;

/**
 * @Author yll
 * @Date 2023/3/9 22:04
 * @PackageName:ekko.spring.v1.test.service
 * @ClassName: SingleTestService
 * @Description: TODO
 * @Version 1.0
 */
@Component("sls")
@Lazy(value = false)
public class SingletonLazyService {
    public void test(){
        System.out.println("sls");
    }
}
