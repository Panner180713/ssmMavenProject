package javaTest;

/**
 * @Author chenshoukai
 * @Date 2020/06/11 9:34
 */
public class TestReturnAndFinally {
    public static void main(String[] args) {
        ReturnAndFinally returnAndFinally = new ReturnAndFinally();
//        System.out.println(returnAndFinally.helloworld());
        returnAndFinally.test();
    }
}

class ReturnAndFinally{
    public String result = "hello";

    public String helloworld(){
        try {
            result = "hello world";
            return result;
        }finally {
            result = "hello world finally";
        }
    }

    public void test(){
        String s = ",,a,b,c,";
        String[] array = s.split(",");
        for (String s1 : array) {
            System.out.println(s1);
        }
    }
}
