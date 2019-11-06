package com.training.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;

/**
 * @Description TODO
 * @date 2019/10/21 9:25
 */
public class CharMatcherTest {
    public static void main(String[] args) {
        String string = "1235control 2 r23..23";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');//去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
        // 只保留数字和小写字母

        System.out.println(noControl);
        System.out.println(theDigits);
        System.out.println(spaced);
        System.out.println(noDigits);
        System.out.println(lowerAndDigit);

        String constant_name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");// returns "constantName"

        System.out.println(constant_name);

    }
}
