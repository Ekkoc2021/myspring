package ekko.spring.v1.test;

import ekko.spring.v1.core.Annotation.Component;
import org.junit.jupiter.api.Test;

/**
 * @Author yll
 * @Date 2023/3/6 16:40
 * @PackageName:ekko.spirng.v1.test
 * @ClassName: testdemo
 * @Description: 用于测试v1版本
 * v1版本主要完成:通过注解启动完成ioc容器的装配,区分单例对象,多例对象
 * @Version 1.0
 */
@Component("wait wait")
public class testdemo {
    private String name;

    public String password;

    int i;

    public testdemo(){

    }
    public testdemo(int i){
        System.out.println(i);
    }

    private testdemo(String t){
        System.out.println(t);
    }

    testdemo(Long i){
        System.out.println(i);
    }
    @Test
    public void test01(){
        System.out.println("test");
    }



}
