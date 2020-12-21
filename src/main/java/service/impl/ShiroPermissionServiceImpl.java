package service.impl;

import javaBean.Employee;
import mapper.ShiroPermissionMapper;
import org.springframework.stereotype.Service;
import service.ShiroPermissionService;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshoukai
 * @Date 2020/09/09 14:58
 */
@Service
public class ShiroPermissionServiceImpl implements ShiroPermissionService {

    private ShiroPermissionMapper shiroPermissionMapper;

    public ShiroPermissionServiceImpl(ShiroPermissionMapper shiroPermissionMapper) {
        this.shiroPermissionMapper = shiroPermissionMapper;
    }

    @Override
    public List<Map<String, Object>> getRoleAndPermissionByUserName(String userName) {
        return shiroPermissionMapper.getRoleAndPermissionByUserName(userName);
    }

    @Override
    public Employee getEmployeeInfoByUserName(String userName) {
        return shiroPermissionMapper.getEmployeeInfoByUserName(userName);
    }
}
