package top.soliloquize.reflection.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author wb
 * @date 2020/2/24
 */
@Data
@Builder
public class MethodMeta {
    /**
     * 声明类
     */
    private Class declaringClass;

    /**
     * 是否为静态方法
     */
    private boolean statics;

    /**
     * 方法名称
     */
    private String name;

    /**
     * 获取方法描述符
     */
    private String descriptor;

    /**
     * 参数类型列表
     */
    private Class[] paramTypes;

}
