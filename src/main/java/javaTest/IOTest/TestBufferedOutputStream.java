package javaTest.IOTest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节缓冲输出流，提高了输出效率。
 * @Author chenshoukai
 * @Date 2020/12/24 15:08
 */
public class TestBufferedOutputStream {

    private static void testWrite() throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D://test.txt"));
        bufferedOutputStream.write("hello world".getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        testWrite();
    }
}
