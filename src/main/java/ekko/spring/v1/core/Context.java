package ekko.spring.v1.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yll
 * @Date 2023/3/6 16:27
 * @PackageName:ekko.spirng.core
 * @ClassName: Context
 * @Description: congtext接口,定义了context的实现
 * getBean(beanname):获取对应名称的类
 * 需要使用的注解:@Component,@Scope
 * @Version 1.0
 */
public interface Context {
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(); //存储class的对象
    Map<String, Object> singletonObjects = new HashMap<>();//存储单例对象map
    /**
    * @description:向容器中获取bean对象
    * @author yll
    * @date 2023/3/6 16:33 
    * @param
    *  beanname:  
    * @return java.lang.Object
    */
    Object getBean(String beanname) throws InstantiationException, IllegalAccessException;

}
