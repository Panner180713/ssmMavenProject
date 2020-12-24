package javaTest.IOTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 字节文件输出流，用于将数据写入到file，从程序中写入到其他位置。
 * @Author chenshoukai
 * @Date 2020/12/24 14:20
 */
public class TestFileOutputStream {

    private static void testWrite() throws IOException {
        OutputStream outputStream = new FileOutputStream("D://test.txt");
        outputStream.write("abcd".getBytes());
        outputStream.close();

        //内容追加写入
        OutputStream appendOutputStream = new FileOutputStream("D://test.txt",true);
        appendOutputStream.write("\r\n".getBytes());
        appendOutputStream.write("hello".getBytes());
        appendOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        testWrite();
    }
}
