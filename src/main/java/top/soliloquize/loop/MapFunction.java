package top.soliloquize.loop;

/**
 * @author wb
 * @date 2020/2/25
 */
@FunctionalInterface
public interface MapFunction<I, K, V> {
    /**
     * 遍历map
     *
     * @param index map的下标
     * @param key   map的key
     * @param value map的value
     */
    void accept(I index, K key, V value);
}
