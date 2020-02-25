package top.soliloquize.reflection.asm;

import org.objectweb.asm.Type;
import top.soliloquize.reflection.asm.method.impl.AsmMethodParseImpl;
import top.soliloquize.reflection.vo.MethodMeta;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author wb
 * @date 2020/2/24
 */
public class AsmMethods {

//    /**
//     * 根据注解获取对应的参数名称
//     * @param method 方法信息
//     * @return 方法列表
//     */
//    public static List<String> getParamNamesByAnnotation( Method method) {
//
//        return ReflectMethodUtil.getParamNames(method);
//    }

    /**
     * 根据注解获取对应的参数名称
     *
     * @param method 方法信息
     * @return 方法列表
     */
    public static List<String> getParamNamesByAsm( Method method) {

         boolean statics = Modifier.isStatic(method.getModifiers());
         String name = method.getName();
         String descriptor = Type.getMethodDescriptor(method);
         Class[] parameterTypes = method.getParameterTypes();
         Class clz = method.getDeclaringClass();

        MethodMeta methodMeta = MethodMeta.builder().statics(statics).name(name).declaringClass(clz).descriptor(descriptor).paramTypes(parameterTypes).build();

        return new AsmMethodParseImpl().getParamNames(methodMeta);
    }

//    /**
//     * 根据注解获取对应的参数名称
//     * @param constructor 构造器
//     * @return 方法列表
//     * @since 0.0.2
//     */
//    public static List<String> getParamNamesByAnnotation( Constructor constructor) {
//
//        Annotation[][] annotations = constructor.getParameterAnnotations();
//        return ReflectMethodUtil.getParamNames(annotations);
//    }

//    /**
//     * 根据注解获取对应的参数名称
//     * @param constructor 构造器
//     * @return 方法列表
//     * @since 0.0.2
//     */
//    public static List<String> getParamNamesByAsm(Constructor<?> constructor) {
//
//         boolean statics = Modifier.isStatic(constructor.getModifiers());
//         String name = "<init>";
//         String descriptor = Type.getConstructorDescriptor(constructor);
//         Class<?>[] parameterTypes = constructor.getParameterTypes();
//         Class<?> clz = constructor.getDeclaringClass();
//
//        MethodMeta methodMeta =  MethodMeta.builder().statics(statics).name(name).declaringClass(clz).descriptor(descriptor).paramTypes(parameterTypes).build();
//        return Instances.singleton(AsmMethodParamName.class).getParamNames(methodMeta);
//    }

}
