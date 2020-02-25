package top.soliloquize.reflection.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wb
 * @date 2020/2/24
 */
@Data
@AllArgsConstructor
public class LocalVariable implements Comparable<LocalVariable> {

    /**
     * 下标
     */
    private int index;
    /**
     * 名称
     */
    private String name;

    @Override
    public int compareTo(LocalVariable o) {
        return this.index - o.index;
    }
}
