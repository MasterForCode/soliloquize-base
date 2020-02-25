package top.soliloquize.string;

import top.soliloquize.validator.Validator;

/**
 * @author wb
 * @date 2020/2/25
 */
public class StringUtils {
    /**
     * 判断字符串的每个字符是否全相等
     *
     * @param str 待判断字符串
     * @return 判断结果
     */
    public static boolean isCharEqual(String str) {
        Validator.parameterRequireNonNull(str);
        if ("".equals(str)) {
            return true;
        }
        return str.replace(str.charAt(0), ' ').trim().length() == 0;
    }

    /**
     * 判断字符串的每个字符是否都是数字
     *
     * @param str 待判断字符串
     * @return 判断结果
     */
    public static boolean isNumeric(String str) {
        Validator.parameterRequireNonNull(str);
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.isNumeric(""));
    }
}
