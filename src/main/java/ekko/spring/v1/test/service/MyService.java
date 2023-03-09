package ekko.spring.v1.test.service;

import ekko.spring.v1.core.Annotation.Component;

/**
 * @Author yll
 * @Date 2023/3/9 20:07
 * @PackageName:ekko.spring.v1.test.service
 * @ClassName: myService
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class MyService {
    public void say(){
        System.out.println("hello");
    }
}
