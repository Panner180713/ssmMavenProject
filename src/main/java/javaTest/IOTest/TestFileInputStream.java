package javaTest.IOTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 字节文件输入流，从文件系统中的某个文件中获得输入字节，用于读取诸如图像文件之类的原始字节流。
 * @Author chenshoukai
 * @Date 2020/12/24 14:05
 */
public class TestFileInputStream {

    private static void testRead() throws IOException {
        InputStream inputStream = new FileInputStream("E://test.txt");
        int i = 0;
        while ((i = inputStream.read()) != -1){
            System.out.print((char) i + " ");
        }
        inputStream.close();
    }

    private static void testByteArrayRead() throws IOException {
        InputStream inputStream = new FileInputStream("E://test.txt");
        byte[] bytes = new byte[4];
        int i = 0;
        while ((i = inputStream.read(bytes)) != -1){
            System.out.println(new String(bytes,0,i));
        }
        inputStream.close();
    }

    public static void main(String[] args) throws IOException {
        testRead();
        System.out.println();
        testByteArrayRead();
    }
}
