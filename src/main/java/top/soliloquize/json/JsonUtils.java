package top.soliloquize.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * Json工具类
 *
 * @author wb
 * @date 2020/2/25
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        //解析器支持解析单引号
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //解析器支持解析结束符
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    /**
     * json串转对象
     *
     * @param jsonStr json串
     * @param clz     CLass
     * @param <T>     对象类型
     * @return 对象
     * @throws JsonProcessingException 解析异常
     */
    public static <T> T json2Bean(String jsonStr, Class<T> clz) throws JsonProcessingException {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            return MAPPER.readValue(jsonStr, clz);
        }
    }

    /**
     * json串转对象
     *
     * @param jsonStr json串
     * @param type    Type
     * @return 对象
     * @throws JsonProcessingException 解析异常
     */
    public static Object json2Bean(String jsonStr, Type type) throws JsonProcessingException {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            JavaType javaType = MAPPER.getTypeFactory().constructType(type);
            return MAPPER.readValue(jsonStr, javaType);
        }
    }

    /**
     * json串转对象
     *
     * @param jsonStr json串
     * @param clz     CLass
     * @param <T>     对象类型
     * @return 对象
     */
    public static <T> T json2BeanEx(String jsonStr, Class<T> clz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            try {
                return MAPPER.readValue(jsonStr, clz);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * json串转对象
     *
     * @param jsonStr json串
     * @param type    Type
     * @return 对象
     */
    public static Object json2BeanEx(String jsonStr, Type type) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            try {
                JavaType javaType = MAPPER.getTypeFactory().constructType(type);
                return MAPPER.readValue(jsonStr, javaType);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 对象转json串
     *
     * @param obj 对象
     * @return json串
     * @throws JsonProcessingException 转化异常
     */
    public static String json(Object obj) throws JsonProcessingException {
        return MAPPER.writeValueAsString(obj);
    }

    /**
     * 对象转json串
     *
     * @param obj 对象
     * @return json串
     */
    public static String jsonEx(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
