package mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshoukai
 * @Date 2020/09/09 14:59
 */
public interface ShiroPermissionMapper {

    /**
     * @Description: 通过用户名获取角色和权限
     * @Author: chenshoukai
     * @Date: 2020/9/10 13:52
     **/
    List<Map<String, Object>> getRoleAndPermissionByUserName(@Param("userName") String userName);
}
