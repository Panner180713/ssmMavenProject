package realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @Author chenshoukai
 * @Date 2020/05/28 22:27
 */
@Component
public class SecondReam extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("second realm...");

        //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2.通过UsernamePasswordToken获取userName
        String userName = usernamePasswordToken.getUsername();
        //3.调用数据库方法，从数据库中获取userName对应的用户数据
        System.out.println("查询用户："+userName);
        //4.若用户不存在，抛出unKnownAccountException
        if("unKnown".equals(userName)){
            throw new UnknownAccountException("用户不存在");
        }
        //5.根据用户信息的情况，决定是否抛出其他AuthenticationException
        if("monster".equals(userName)){
            throw new LockedAccountException("账户被锁定");
        }
        //6.构建AuthenticationInfo对象并返回，通常的实现类为SimpleAuthenticationInfo
        //6.1 principal:实体的认证信息，可以是userName，也可以是数据库记录对应的实体类对象。
        Object principal = userName;
        //6.2 credentials:密码
        Object credentials = null;//"fc1709d0a95a6be30bc5926fdb7f22f4";
        if("admin".equals(userName)){
            //使用new SimpleHash计算使用盐值之后的密码的值
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }else if("user".equals(userName)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        //6.3 realmName:当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;//new SimpleAuthenticationInfo(principal,credentials,realmName);
        //6.4 盐值,盐值入参需要是唯一的,入参一般使用userId或随机字符串
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        String algorithmName = "SHA1";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName,source,salt,hashIterations);
        System.out.println(result);
    }
}
