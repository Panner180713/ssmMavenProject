package realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import service.ShiroPermissionService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author chenshoukai
 * @Date 2020/05/28 22:27
 */
public class ShiroReam extends AuthorizingRealm {

    @Autowired
    private ShiroPermissionService shiroPermissionService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("shiro realm...");

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
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }else if("user".equals(userName)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }else if("jerry".equals(userName)){
            credentials = "984fdc10c74f292ac96e4b265b4ddb32";
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
        String algorithmName = "MD5";
        Object source = "123456";
        Object salt = ByteSource.Util.bytes("jerry");
        int hashIterations = 1024;
        Object result = new SimpleHash(algorithmName,source,salt,hashIterations);
        System.out.println(result);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.从PrincipalCollection中获取登录用户的信息
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        //2.利用登录用户的信息来获取当前用户的角色(可能需要查询数据库)
        Set<String> roles = new HashSet<>();
        roles.add("user");
        if("admin".equals(primaryPrincipal)){
            roles.add("admin");
        }
        List<Map<String, Object>> resultList = shiroPermissionService.getRoleAndPermissionByUserName(primaryPrincipal.toString());
        Set<String> permissions = new HashSet<>();
        for (Map<String, Object> map : resultList) {
            roles.add(map.get("roleName").toString());
            permissions.add(map.get("permission").toString());
        }
        //3.创建SimpleAuthorizationInfo对象，并设置其roles属性
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
