package ekko.spring.v1.core;

import lombok.Data;

/**
 * @Author yll
 * @Date 2023/3/6 16:35
 * @PackageName:ekko.spirng.core
 * @ClassName: BeanDefinition
 * @Description: 封装bean数据
 * @Version 1.0
 */
@Data
public class BeanDefinition {
    private Class type;
    private String scope;
    private boolean isLazy;
}
