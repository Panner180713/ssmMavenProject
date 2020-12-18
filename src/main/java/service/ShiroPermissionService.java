package service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 查询数据库授权
 * @Author chenshoukai
 * @Date 2020/09/09 14:57
 */
public interface ShiroPermissionService {
    /**
     * @Description: 通过用户名获取角色和权限
     * @Author: chenshoukai
     * @Date: 2020/9/10 13:52
     **/
    List<Map<String, Object>> getRoleAndPermissionByUserName(String userName);
}
