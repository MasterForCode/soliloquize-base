package top.soliloquize.reflection.clz;

import top.soliloquize.loop.Loop;
import top.soliloquize.reflection.asm.AsmMethods;
import top.soliloquize.reflection.vo.ClzDetail;
import top.soliloquize.reflection.vo.MethodDetail;
import top.soliloquize.reflection.vo.ParameterDetail;
import top.soliloquize.validator.Validator;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;

/**
 * @author wb
 * @date 2020/2/24
 */
public class ParseClz {
    public static void main(String[] args) {
        User user = new User();
        ClzDetail re = parseClz(user.getClass());
        System.out.println("-------");
    }

    public static <T> ClzDetail parseClz(Class clz) {
        ClzDetail clzDetail = ClzDetail.builder().clz(clz).name(clz.getSimpleName()).build();
        Method[] methods = clz.getDeclaredMethods();
        Loop.loop(methods, method -> clzDetail.getMethods().add(ParseClz.parseMethod(method)));
        return clzDetail;
    }

    public static MethodDetail parseMethod(Method method) {
        MethodDetail methodDetail = MethodDetail.builder().type(method.getReturnType()).name(method.getName()).build();
        List<String> paramNames = AsmMethods.getParamNamesByAsm(method);
        Parameter[] parameters = method.getParameters();
        Loop.loop(parameters, (index, parameter) -> {
            ParameterDetail parameterDetail = ParameterDetail.builder().type(parameter.getType()).build();
            if (Validator.isNotBlack(paramNames)) {
                parameterDetail.setParam(paramNames.get(index));
            }
            methodDetail.getParams().add(parameterDetail);
        });
        return methodDetail;
    }

}
