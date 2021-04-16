package javaTest.socket;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author chenshoukai
 * @Date 2021/03/27 20:05
 */
public class TCPTest1 {

    @Test
    public void client1() {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            //1.获取Socket对象，指明服务器端的ip和端口
            socket = new Socket(inetAddress, 8899);
            //2.获取输出流
            outputStream = socket.getOutputStream();
            //3.写出数据
            outputStream.write("你好，我是客户端".getBytes());
            //明确告诉服务端的输入流，客户端的输出结束了，服务端停止阻塞继续执行。
            socket.shutdownOutput();

            byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = socket.getInputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }
            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != byteArrayOutputStream){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void Server1() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(8899);
            //2.用于接收来自客户端的socket，阻塞式的
            socket = serverSocket.accept();
            //3.获取输入流
            inputStream = socket.getInputStream();
            //4.读取输入流的数据
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[5];
            int len;
            while((len = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }

            System.out.println(byteArrayOutputStream.toString());

            outputStream = socket.getOutputStream();
            outputStream.write("客户端你好，我是服务端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != byteArrayOutputStream){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != serverSocket){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
