package top.soliloquize.reflection.asm.method.impl;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;
import top.soliloquize.exception.AsmException;
import top.soliloquize.reflection.asm.method.AsmMethodParse;
import top.soliloquize.reflection.vo.LocalVariable;
import top.soliloquize.reflection.vo.MethodMeta;
import top.soliloquize.validator.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wb
 * @date 2020/2/24
 */
public class AsmMethodParseImpl implements AsmMethodParse {

    /**
     * 基于 asm 获取对应的名称
     *
     * @param methodMeta 方法原始信息
     * @return 名称列表
     */
    @Override
    public List<String> getParamNames(MethodMeta methodMeta) {
        try {
             Class clazz = methodMeta.getDeclaringClass();
             Class[] paramTypes = methodMeta.getParamTypes();

            if (Validator.isBlack(paramTypes)) {
                return Collections.emptyList();
            }

            // ASM树接口形式访问
            ClassReader cr = new ClassReader(clazz.getName());
            ClassNode cn = new ClassNode();
            cr.accept(cn, ClassReader.EXPAND_FRAMES);

            // 处理
            MethodNode methodNode = getMethodNode(cn, methodMeta);
             boolean statics = methodMeta.isStatics();

            // 构建结果
            List<LocalVariable> localVariables = getLocalVars(methodNode, statics);
            return localVariables.stream().map(LocalVariable::getName).collect(Collectors.toList());
        } catch (IOException e) {
            throw new AsmException(e);
        }
    }


    /**
     * 获取匹配的方法节点
     *
     * @param classNode  类阶段
     * @param methodMeta 方法元信息
     * @return 方法节点
     */
    @SuppressWarnings("unchecked")
    private MethodNode getMethodNode(ClassNode classNode, MethodMeta methodMeta) {

         String methodDescriptor = methodMeta.getDescriptor();
         String methodName = methodMeta.getName();

        List<MethodNode> methods = classNode.methods;
        for (MethodNode asmMethod : methods) {
            // 验证方法签名
            if (asmMethod.desc.equals(methodDescriptor)
                    && asmMethod.name.equals(methodName)) {
                return asmMethod;
            }
        }

        // 这里理论上是不会走到的
        throw new AsmException("Method not found!");
    }

    /**
     * 获排序后的方法参数
     *
     * @param asmMethod 方法信息
     * @param isStatic  是否为静态
     * @return 结果列表
     */
    @SuppressWarnings("unchecked")
    private List<LocalVariable> getLocalVars(MethodNode asmMethod, boolean isStatic) {
        List<LocalVariableNode> localVariableNodes = asmMethod.localVariables;
        List<LocalVariable> resultList = new ArrayList<>(localVariableNodes.size());

        localVariableNodes.forEach(variableNode -> {
            // index-记录了正确的方法本地变量索引。
            // (方法本地变量顺序可能会被打乱。而index记录了原始的顺序)
            int index = variableNode.index;
            String name = variableNode.name;

            // 非静态方法,第一个参数是this
            if (!isThisVarName(isStatic, variableNode)) {
                LocalVariable localVariable = new LocalVariable(index, name);
                resultList.add(localVariable);
            }
        });
        // 根据index来重排序，以确保正确的顺序
        Collections.sort(resultList);
        return resultList;
    }

    /**
     * 如果是引用类型（非静态方法），第一个参数为 this(指代本身)
     *
     * @param isStatic     是否为静态
     * @param variableNode 变量节点
     * @return 是否为 this 参数
     */
    private boolean isThisVarName(boolean isStatic, LocalVariableNode variableNode) {
        if (isStatic) {
            return false;
        }

        int index = variableNode.index;
        String name = variableNode.name;
        return 0 == index && "this".equals(name);
    }
}
