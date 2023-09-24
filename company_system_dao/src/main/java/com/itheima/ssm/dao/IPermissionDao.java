package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findall() throws Exception;

    @Insert("insert into permission(permissionName,url) value(#{permissionName},#{url})")
    public void save(Permission p) throws Exception;

//    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
//    public List<Permission> findotherpermission(String id) throws Exception;

    @Select("select * from permission where id=#{id}")
    Permission findById(String id) throws Exception;

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_Permission(String id) throws Exception;

    @Delete("delete from permission where id=#{id}")
    void deleteById(String id) throws Exception ;
}
