package ekko.spring.v1.test;

import ekko.spring.v1.core.Annotation.Component;
import ekko.spring.v1.core.Annotation.ComponentScan;
import ekko.spring.v1.core.AnnotationApplicationContext;
import ekko.spring.v1.test.service.HelloService;
import ekko.spring.v1.test.service.MyService;
import ekko.spring.v1.test.service.NoAannotationService;
import ekko.spring.v1.test.service.SingletonLazyService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * @Author yll
 * @Date 2023/3/6 16:43
 * @PackageName:ekko.spirng.v1.test
 * @ClassName: AppConfig
 * @Description: TODO
 * @Version 1.0
 */
@ComponentScan("ekko.spring.v1.test.service")
//@Component("app") //只包含第一个属性可以不用指出key值
public class Application {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //创建容器
        AnnotationApplicationContext annotationApplicationContext = new AnnotationApplicationContext(Application.class);

        //获取bean对象
        MyService myService =(MyService) annotationApplicationContext.getBean("MyService");
        myService.say();
        MyService myService2 =(MyService) annotationApplicationContext.getBean("MyService");
        System.out.println(myService2);
        System.out.println(myService);

        HelloService helloService =(HelloService) annotationApplicationContext.getBean("HelloService");
        helloService.test();
        System.out.println(helloService);

        HelloService helloService2 =(HelloService) annotationApplicationContext.getBean("HelloService");
        System.out.println(helloService2);

        SingletonLazyService singletonLazyService=(SingletonLazyService)annotationApplicationContext.getBean("sls");
        singletonLazyService.test();
        SingletonLazyService singletonLazyService2=(SingletonLazyService)annotationApplicationContext.getBean("sls");
        System.out.println(singletonLazyService);
        System.out.println(singletonLazyService2);


        NoAannotationService noAannotationService=(NoAannotationService)annotationApplicationContext.getBean("NoAannotationService");
        System.out.println(noAannotationService);


    }


    public static void t1(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> testdemo1 = Class.forName("ekko.spring.v1.test.testdemo");

        Constructor<?> constructor = testdemo1.getConstructor(int.class);
        testdemo t = (testdemo)constructor.newInstance(2);


        Constructor<?> constructor2 = testdemo1.getConstructor();
        System.out.println(constructor);
        System.out.println(constructor2);
        //public ekko.spring.v1.test.testdemo(int)
        //public ekko.spring.v1.test.testdemo()

        Constructor<?>[] constructors = testdemo1.getConstructors();
        for (Constructor c: constructors) {
            System.out.println(c);
        }

        //public ekko.spring.v1.test.testdemo(int)
        //public ekko.spring.v1.test.testdemo()

        Constructor<?> declaredConstructor = testdemo1.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("通过构造方法");

        //private ekko.spring.v1.test.testdemo(java.lang.String)
        Constructor[] declaredConstructors = testdemo1.getDeclaredConstructors();

        for (Constructor c: declaredConstructors) {
            System.out.println(c);
        }
        //ekko.spring.v1.test.testdemo(java.lang.Long)
        //private ekko.spring.v1.test.testdemo(java.lang.String)
        //public ekko.spring.v1.test.testdemo(int)
        //public ekko.spring.v1.test.testdemo()

        Annotation[] annotation = testdemo1.getAnnotations();
        for (Annotation A :annotation
             ) {
            System.out.println(A);
        }
        //@ekko.spring.v1.core.Annotation.Component(key=[你好, hello], value=wait wait)
        Component[] annotationsByType = testdemo1.getAnnotationsByType(Component.class);
        System.out.println(annotationsByType);
        Annotation[] declaredAnnotations = testdemo1.getDeclaredAnnotations();
        for (Annotation A :declaredAnnotations
        ) {
            System.out.println(A);
        }

    }
}
