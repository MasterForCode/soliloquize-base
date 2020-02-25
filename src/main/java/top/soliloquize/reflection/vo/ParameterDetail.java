package top.soliloquize.reflection.vo;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Type;

/**
 * @author wb
 * @date 2020/2/24
 * 通用参数包装
 */
@Data
@Builder
public class ParameterDetail {
    /**
     * 参数类型
     */
    private Class type;
    /**
     * 参数名
     */
    private String param;
    /**
     * 参数值
     */
    private Object value;
}
