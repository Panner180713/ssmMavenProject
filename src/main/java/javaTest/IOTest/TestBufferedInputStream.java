package javaTest.IOTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节缓冲输入流，提高了读取效率
 *
 * @Author chenshoukai
 * @Date 2020/12/24 15:03
 */
public class TestBufferedInputStream {

    private static void testRead() throws IOException {
        InputStream in = new FileInputStream("E://test.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        byte[] bytes = new byte[20];
        int i = 0;
        while ((i = bufferedInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, i));
        }
        bufferedInputStream.close();
        in.close();
    }

    public static void main(String[] args) throws IOException {
        testRead();
    }
}
