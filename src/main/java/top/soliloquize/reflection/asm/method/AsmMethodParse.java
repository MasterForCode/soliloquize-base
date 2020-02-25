package top.soliloquize.reflection.asm.method;


import top.soliloquize.reflection.vo.MethodMeta;

import java.util.List;

/**
 * @author wb
 * @date  2020/2/24
 */
public interface AsmMethodParse {
    /**
     * 获取参数名称
     * @param methodMeta 方法元信息
     * @return 方法名称
     */
    List<String> getParamNames(MethodMeta methodMeta);

}
