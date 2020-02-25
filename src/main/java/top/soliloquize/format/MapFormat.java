package top.soliloquize.format;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * map工具类
 *
 * @author wb
 * @date 2020/2/25
 */
public class MapFormat {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 对象列表转map列表
     *
     * @param objects 对象列表
     * @param <T>     T
     * @return map列表
     */
    public static <T> List<Map<String, Object>> objects2Maps(List<T> objects) {
        JavaType javaType = MapFormat.OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
        return objects.parallelStream().map(each -> MapFormat.object2Map(each, javaType)).collect(Collectors.toList());
    }

    /**
     * 对象转map
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> object2Map(Object obj, JavaType javaType) {
        return MapFormat.OBJECT_MAPPER.convertValue(obj, javaType);
    }

}
