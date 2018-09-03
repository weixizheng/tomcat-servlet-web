package com.nick.dao.auth;

import com.nick.bean.User;
import com.nick.bean.auth.Privilege;
import com.nick.bean.auth.Role;
import com.nick.bean.auth.UserRole;
import com.nick.dao.common.BaseDAO;

import java.util.ArrayList;
import java.util.List;

public class UserPrivilegeDAO extends BaseDAO<Privilege> {

    //依赖注入
    public UserPrivilegeDAO() {

    }

    public List<Privilege> getUserRoleByUser(User user) {
        String sql = "SELECT r.* FROM UserRole ur LEFT JOIN Role r ON ur.roleId = r.roleId WHERE userId = ?";
        List param = new ArrayList();
        param.add(user.getUserId());
        return super.findByParam(sql, param);
    }

}
