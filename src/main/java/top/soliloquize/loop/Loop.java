package top.soliloquize.loop;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author wb
 * @date 2020/2/24
 */
public class Loop {
    /**
     * 数组的循环方法
     * @param elements 数组
     * @param action Consumer
     * @param <T> 数组包含的元素的类型
     */
    public static <T> void loop(T[] elements, Consumer<T> action) {
        for (T element : elements) {
            action.accept(element);
        }
    }

    /**
     * 为数组遍历添加下表
     * @param elements 数组
     * @param action BiConsumer
     * @param <U> 数组包含的元素的类型
     */
    public static <U> void loop(U[] elements, BiConsumer<Integer, U> action) {
        int index = 0;
        for (U element : elements) {
            action.accept(index++, element);
        }
    }

    /**
     * 为可迭代对象添加下标
     * @param elements 可迭代对象
     * @param action BiConsumer
     * @param <U> 可迭代对象包含的元素的类型
     */
    public static <U> void loop(Iterable<? extends U> elements, BiConsumer<Integer, U> action) {
        int index = 0;
        for (U element : elements) {
            action.accept(index++, element);
        }
    }

    /**
     * 为Map的循环添加下标
     * @param elements Map
     * @param action MapFunction
     * @param <K> K key类型
     * @param <V> V value类型
     */
    public static <K, V> void loop(Map<K, V> elements, MapFunction<Integer, K, V> action) {
        int index = 0;
        for (Map.Entry<K, V> entry : elements.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(index++, k, v);
        }
    }
}
