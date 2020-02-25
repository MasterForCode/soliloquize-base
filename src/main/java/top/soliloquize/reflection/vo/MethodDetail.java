package top.soliloquize.reflection.vo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wb
 * @date 2020/2/24
 * 通用方法包装
 */
@Data
@Builder
public class MethodDetail {
    /**
     * 方法名
     */
    private String name;
    /**
     * 方法的返回值类型
     */
    private Class type;
    /**
     * 方法参数
     */
    @Builder.Default
    private List<ParameterDetail> params = new ArrayList<>();
}
