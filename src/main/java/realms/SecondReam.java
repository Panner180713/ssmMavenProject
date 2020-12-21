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

        return null;
    }
}
