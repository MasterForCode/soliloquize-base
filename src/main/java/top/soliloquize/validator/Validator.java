package top.soliloquize.validator;

import java.util.List;
import java.util.Objects;

/**
 * @author wb
 * @date 2020/2/24
 */
public class Validator {
    public static boolean isBlack(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
    public static boolean isNotBlack(List<?> list) {
        return !Validator.isBlack(list);
    }


    public static boolean isBlack(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlack(Object[] objs) {
        return !Validator.isBlack(objs);
    }

    public static void parameterRequireNonNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
    }
}
