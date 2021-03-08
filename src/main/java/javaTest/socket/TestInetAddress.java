package javaTest.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 一、网络编程中有两个主要的问题：
 * 1.如何准确的定位网络上的一台或多台主机，定位主机上特定的应用
 * 2.找到主机后如何可靠高效的传输数据
 * 二、网络编程中的两个要素
 * 1.ip和端口号
 * 2.网络通信协议 tcp/ip参考模型
 *
 * 三、通信要素一 ip和端口号
 * 1.ip唯一的标识internet上的计算机
 * 2.使用InetAddress类表示ip
 * 3.ip的分类 ipv4和ipv6 万维网和局域网(192.168.0.0 - 192.168.255.255)
 */
public class TestInetAddress {

    public static void main(String[] args) {

        try {
            InetAddress inetAddress1 = InetAddress.getByName("192.168.10.14");
            System.out.println(inetAddress1);

            InetAddress inetAddress2 = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress2);

            //获取本地ip
            InetAddress inetAddress3 = InetAddress.getByName("127.0.0.1");
            InetAddress inetAddress4 = InetAddress.getLocalHost();

            //InetAddress实例的常用方法 获取域名、获取ip
            String hostName = inetAddress2.getHostName();
            String hostAddress = inetAddress2.getHostAddress();
            System.out.println(hostName);
            System.out.println(hostAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
