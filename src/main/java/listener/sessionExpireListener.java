package listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 1.spring+shiro下HttpSessionListener接口监听Session失效的问题：
 * spring使用shiro后，由于shiro重新封装了原有的session，所以不能再使用原来的session监听方法了，
 * 要实现shiro的listener进行监听，并将其配置给sessionManager
 * @Author chenshoukai
 * @Date 2020/11/06 14:53
 */
public class sessionExpireListener implements SessionListener {


    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {
        System.out.println("sessionExpireListener onStop...");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("sessionExpireListener onExpiration...");
    }
}
