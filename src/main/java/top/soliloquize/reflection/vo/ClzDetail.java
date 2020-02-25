package top.soliloquize.reflection.vo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wb
 * @date 2020/2/24
 * 通用类包装
 */
@Data
@Builder
public class ClzDetail {
    /**
     * 类
     */
    private Class clz;
    /**
     * 类名
     */
    private String name;
    /**
     * 类中方法
     */
    @Builder.Default
    private List<MethodDetail> methods = new ArrayList<>();
}
