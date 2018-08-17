package tools;

import com.jayway.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Chuckie on 15/11/13.
 */

public class AssertTool extends Assert {
    static LoggerControler logger = LoggerControler.getLogger(AssertTool.class);
//======================================assertEqualsInt=================================
    public static void assertEqualsInt(int actualInt,int expectInt,String message) {
        StringBuilder msg = new StringBuilder("assertEqualsInt(int actualInt,int expectInt,String message):\n");
        if (actualInt == expectInt) {
            msg.append(":int相等校验正确\n预期结果为：" + String.valueOf(expectInt) + "\n实际结果为：" + String.valueOf(actualInt));
            logger.info(msg);
        } else {
            msg.append(message + ":int相等校验失败\n预期结果串为：" + String.valueOf(expectInt) + "\n实际结果为：" + String.valueOf(actualInt));
            logger.error(msg);
//            Assert.fail(message);
            Assert.fail("error occurred");
        }
    }

//======================================assertEqualsStr=================================

    public static void assertEqualsStr(String actualStr,String expectStr,String message){
        StringBuilder msg = new StringBuilder("assertEqualsStr(String actualStr,String expectStr,String message):\n");
        if (actualStr.equals(expectStr)) {
            msg.append(":string相等校验正确\n预期结果为：" + String.valueOf(expectStr) + "\n实际结果为：" + String.valueOf(actualStr));
            logger.info(msg);
        } else {
            msg.append(message + ":string相等校验失败\n预期结果串为：" + String.valueOf(expectStr) + "\n实际结果为：" + String.valueOf(actualStr));
            logger.error(msg);
//            Assert.fail(message);
        }
    }


//======================================assertInclude=================================

    /**
     * 断言字符串中是否包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 包含的字符串
     * @param message  断言错误消息
     */
    public static void assertInclude(String content, String included, String message) {
        StringBuilder msg = new StringBuilder("assertInclude(String content, String included, String message):\n");
        if (content.contains(included)) {
            msg.append(message + ":包含校验正确\n校验字符串为：" + content + "\n包含字符串为：" + included);
            logger.info(msg);
        } else {
            msg.append(message + ":包含校验失败\n校验字符串为：" + content + "\n包含字符串为：" + included);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 断言字符串中是否包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertInclude(String content, String included) {
        AssertTool.assertInclude(content, included, null);
    }

    //======================================assertNotInclude=================================

    /**
     * 断言字符串中不包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 不包含的字符串
     * @param message  断言错误消息
     */
    public static void assertNotInclude(String content, String included, String message) {
        StringBuilder msg = new StringBuilder("assertNotInclude(String content, String included, String message):\n");
        if (!content.contains(included)) {
            msg.append(message + ":不包含校验正确\n校验字符串为：" + content + "\n包含字符串为：" + included);
            logger.info(msg);
        } else {
            msg.append(message + ":不包含校验失败\n校验字符串为：" + content + "\n包含字符串为：" + included);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 断言字符串中是否不包含某些字符
     *
     * @param content  断言的字符串为
     * @param included 包含的字符串
     */
    public static void assertNotInclude(String content, String included) {
        AssertTool.assertNotInclude(content, included, null);
    }

    //======================================assertMatch=================================

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     * @param message 断言错误消息
     */
    public static void assertMatch(String matcher, String regex, String message) {
        StringBuilder msg = new StringBuilder("assertMatch(String actual,String expect,String message):\n");
        if (Pattern.matches(regex, matcher)) {
            msg.append(message + ":匹配校验成功！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            logger.info(msg);
        } else {
            msg.append(message + ":匹配校验失败！\n待校验的字符串为:" + matcher + "\n校验的正则表达式为:" + regex);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 根据正则表达式断言是否匹配
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertMatch(String matcher, String regex) {
        AssertTool.assertMatch(matcher, regex, null);
    }

    //======================================assertNotMatch=================================

    /**
     * 根据正则表达式断言不匹配待校验的字符串
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     * @param message 断言错误消息
     */
    public static void assertNotMatch(String matcher, String regex, String message) {
        StringBuilder msg = new StringBuilder("assertNotMatch(String actual,String expect,String message):\n");
        if (!Pattern.matches(regex, matcher)) {
            msg.append(message + ":匹配校验成功！\n待校验的字符窜为:" + matcher + "\n校验的正则表达式为:" + regex);
            logger.info(msg);
        } else {
            msg.append(message + ":匹配校验失败！\n待校验的字符窜为:" + matcher + "\n校验的正则表达式为:" + regex);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 根据正则表达式断言不匹配待校验的字符串
     *
     * @param matcher 待校验的字符串
     * @param regex   校验的正则表达式
     */
    public static void assertNotMatch(String matcher, String regex) {
        AssertTool.assertNotMatch(matcher, regex, null);
    }

    //=============================assertStartWith  assertEndWith=====================================================

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     * @param message 断言错误消息
     */
    public static void assertStartWith(String content, String prefix, String message) {
        StringBuilder msg = new StringBuilder("assertStartWith(String content, String prefix, String message)");
        if (content.startsWith(prefix)) {
            msg.append(message + ":前缀匹配校验成功！\n待校验的字符窜为:" + content + "\n校验的前缀表达式为:" + prefix);
            logger.info(msg);
        } else {
            msg.append(message + ":前缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的前缀表达式为:" + prefix);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 断言字符是否已某个字符串开头
     *
     * @param content 待断言字符串
     * @param prefix  前缀表达式
     */
    public static void assertStartWith(String content, String prefix) {
        AssertTool.assertStartWith(content, prefix, null);
    }

    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     * @param message 断言错误消息
     */
    public static void assertEndWith(String content, String endfix, String message) {
        StringBuilder msg = new StringBuilder("assertEndWith(String content, String endfix, String message)");
        if (content.endsWith(endfix)) {
            msg.append(message + ":");
            msg.append("后缀匹配校验成功！\n待校验的字符窜为:" + content + "\n校验的后缀表达式为:" + endfix);
            logger.info(msg);
        } else {
            msg.append(message + ":");
            msg.append("后缀匹配校验失败！\n待校验的字符窜为:" + content + "\n校验的后缀表达式为:" + endfix);
            logger.error(msg);
            Assert.fail(message);
        }
    }

    /**
     * 断言字符是否已某个字符串结尾
     *
     * @param content 待断言字符串
     * @param endfix  前缀表达式
     */
    public static void assertEndWith(String content, String endfix) {
        AssertTool.assertEndWith(content, endfix, null);
    }

    /**
     * 断言返回的Json串的值是否跟预期的一致
     * @param jsonPath 返回的jsonPath
     * @param keys 要校验的字段列表
     * @param expectedValue 预期返回的字段值列表
     */
    public static void assertJsonPathValues(JsonPath jsonPath,String[] keys,String[] expectedValue){
        if(keys.length != expectedValue.length){
            Assert.fail("需要校验的字段个数跟预期的值的个数不一致，编写有误，请重新检查！");
        }
        for (int i=0; i < keys.length; i++){
            String actual = "";
            if(jsonPath.get(keys[i]) != null){
                if(jsonPath.get(keys[i]) instanceof ArrayList){
                    actual = ((ArrayList) jsonPath.get(keys[i])).get(0).toString();
                }else{
                    actual = jsonPath.get(keys[i]).toString();
                }
            }else{
                if(expectedValue[i] !=  null){
                    Assert.fail("实际为null,预期却不为null");
                }
                continue;
            }
            if(!expectedValue[i].equals(actual)){
                String msg =  "预期返回：" + expectedValue[i] + " 实际返回：" + actual;
                Assert.fail(msg);
            }
        }

    }
}
