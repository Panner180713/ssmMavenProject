package javaTest;

import org.junit.Test;

/**
 * @Author chenshoukai
 * @Date 2020/07/02 10:43
 */
public class TestString {

    @Test
    public void test1() {
        String a = "aaa";
        String b = testString(a);
        System.out.println(a == b);
    }

    private String testString(String str1){
        str1 += "sss";
        return str1;
    }
}
