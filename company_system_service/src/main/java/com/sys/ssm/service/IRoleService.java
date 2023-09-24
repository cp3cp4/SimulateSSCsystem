package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role>findAll() throws Exception;
    public void save(Role r) throws Exception;
    public List<Role> findotherrole(String id) throws Exception;
    public Role findbyid(String id) throws  Exception;
    public void addPermissionToRole(String roleId, String []permissionIds);
    void deleteRoleById(String roleId) throws Exception;
    List<Permission> findOtherPermissions(String roleId) throws Exception;
}
