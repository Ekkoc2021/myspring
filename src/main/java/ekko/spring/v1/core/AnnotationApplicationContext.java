package ekko.spring.v1.core;

import com.sun.xml.internal.bind.v2.TODO;
import ekko.spring.v1.core.Annotation.Component;
import ekko.spring.v1.core.Annotation.ComponentScan;
import ekko.spring.v1.core.Annotation.Lazy;
import ekko.spring.v1.core.Annotation.Scope;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @Author yll
 * @Date 2023/3/6 16:43
 * @PackageName:ekko.spirng.v1.core
 * @ClassName: AnnotationApplicationContext
 * @Description: 完成对
 * @Version 1.0
 */

public class AnnotationApplicationContext implements Context {
    private Class configClass; //配置文件
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();//
    private Map<String, Object> singletonObject = new HashMap<>();//单例对象的集合

    public AnnotationApplicationContext(Class configClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.configClass = configClass;
        //扫描:class封装信息到beanDefinition对象,然后装beanDefinitionMap容器
        scan(configClass);

        //创建对象: 从beanDefinitionMap中获取beanDefinition判断是否是单例,是就创建对象,放入单例池
        Set<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            
            //是否是懒加载,同时是单例==>如果是懒加载创建对象的需要放在getBean方法中
            if (!beanDefinition.isLazy() && beanDefinition.getScope().equals("singleton")){
                //创建对象
                createBean(name,beanDefinition);
            }
        }

    }
    
    /**
    * @description:完成对配置类上扫描路径下标注有class的类的扫描
    * @author yll
    * @date 2023/3/9 21:52
    * @param
    *  configClass:
    * @return void
    */
    private void scan(Class configClass) throws ClassNotFoundException {
        ComponentScan componentScan = null;
        //完成BeanDefinitionMap的封装
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            //如果不包含该注解,应该
            componentScan = (ComponentScan) configClass.getAnnotation(ComponentScan.class);

            //解析需要扫描的包
            String path = componentScan.value();
            path = path.replace(".", "/"); //URL里面要求的 '/' 而后面获取到的文件路径改为了 '\'
            ClassLoader classLoader = configClass.getClassLoader();
            URL resource = classLoader.getResource(path);
            String filepath = resource.getFile(); //拿到扫描的包位置
            File file = new File(filepath);

            //解析包内包含Componnet注解的class文件
            File[] files = file.listFiles();
            //todo 只做简单的单级的目录扫描,service包下面的目录不再进行扫描
            List<String> classFilePath = new ArrayList<>();
            if (file.isDirectory()) {
                //进行一个简单的判断,防止给的路径是一个文件
                for (File file1 : files) {
                    if (!file1.isDirectory()) {
                        //减少嵌套层数,将获取到的文件简单包装到一个list集合
                        classFilePath.add(file1.getPath());
                    }
                }
            }

            //E:\workspace\idea_workspace02\myspring\target\classes\ekko\spring\v1\test\service\myService.class
            //得到的Path是绝对目录,需要处理成相对路径,并且\替换成. : ekko.spring.v1.test.service.myService

            // /E:/workspace/idea_workspace02/myspring/target/classes/ 需要处理掉第一个,将/ 替换成 \ : 类路径可能会常用,可以抽成属性
            String classPath = classLoader.getResource("").getPath().substring(1).replace("/", "\\");

            // 初始化 beandefinitionMap对象
            Lazy lazy=null;
            Component component=null;
            Scope scope=null;
            for (String classPathName : classFilePath) {
                BeanDefinition beanDefinition = new BeanDefinition();
                String substring = classPathName.replace(classPath, "");
                // ekko\spring\v1\test\service\myService.class 需要进一步处理
                Class<?> aClass = classLoader.loadClass(substring.replace("\\", ".").substring(0, substring.indexOf(".class")));
                component=aClass.getAnnotation(Component.class);
                if (component!=null) {
                    //存在:获取类名称,获取是否是懒加载,获取是否是单例
                    //类名
                    String beanName = component.value();
                    if ("".equals(beanName)){
                        //todo 需要把开头转换为小写,这里不做处理
                        beanName=aClass.getSimpleName();
                    }
                    //是否懒加载
                    lazy = aClass.getAnnotation(Lazy.class);
                    if (lazy !=null) {
                        boolean value = lazy.value();
                        beanDefinition.setLazy(value);
                        lazy=null;
                    }else{
                        beanDefinition.setLazy(true);
                    }
                    //是否是单例
                    scope=aClass.getAnnotation(Scope.class);
                    if (scope!=null){
                        if ("prototype".equals(scope.value())){
                            beanDefinition.setScope("prototype");
                        }else{
                            beanDefinition.setScope("singleton");
                        }
                        scope=null;
                    }else {
                        beanDefinition.setScope("singleton");
                    }
                    //类型
                    beanDefinition.setType(aClass);

                    //装入beanDefinitionMap
                    beanDefinitionMap.put(beanName,beanDefinition);
                    //扫描完成!

                }
                component=null;
            }


        }
    }

    /**
    * @description: 单例则创建对象放入单例池,多例则创建返回
    * @author yll
    * @date 2023/3/9 21:35 
    * @param
    *  name: bean的名称
    *  beanDefinition:  封装Componet信息的对象
    * @return Object
    */
    private Object createBean(String name, BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException {
        // TODO: 2023/3/9 创建对象是一个复杂的过程:需要考虑到空参,有参,装配,@Autowired,代理等,暂时只考虑空参构造
        //判断是否单例
        if (beanDefinition.getScope().equals("singleton")) {
            //单例,创建对象,放入单例池
            Object o = beanDefinition.getType().newInstance();
            singletonObject.put(name,o);
            return o;
        }
        return  beanDefinition.getType().newInstance();
    }
    
    /**
    * @description:通过bean的名称 获取bean
    * @author yll
    * @date 2023/3/9 21:44
    * @param
    *  beanname: bean的名称
    * @return null
    */
    @Override
    public Object getBean(String beanname) {
        Object o = singletonObject.get(beanname);
        if (o!=null) {
            return o;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanname);
        Object bean=null;
        try {
            if (beanDefinition!=null){
                bean = createBean(beanname, beanDefinition);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        if (bean==null){
            throw new RuntimeException("没有这个bean对象!");
        }
        return bean;
    }
}
