package ekko.spring.v1.service;

import ekko.spring.v1.core.Annotation.Component;
import ekko.spring.v1.core.Annotation.Scope;

/**
 * @Author yll
 * @Date 2023/3/9 21:53
 * @PackageName:ekko.spring.v1.test.service
 * @ClassName: HelloService
 * @Description: TODO
 * @Version 1.0
 */
@Component
@Scope("prototype")
public class HelloService {
    public void test(){
        System.out.println("helloServie");
    }
}
