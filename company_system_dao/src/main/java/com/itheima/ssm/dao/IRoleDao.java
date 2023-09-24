package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results(
            {
                    @Result(id=true,column="id",property="id"),
                    @Result(column="roleName",property="roleName"),
                    @Result(column="roleDesc",property="roleDesc"),
                    @Result(column="id",property="permissions",javaType=List.class,many=@Many(select="com.itheima.ssm.dao.IPermissionDao.findByRoleId"))
            })
    public List<Role> findRoleByUserId(String id) throws Exception;



    @Select("select * from role")
    public List<Role>findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role r) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    public List<Role> findotherrole(String id) throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IPermissionDao.findByRoleId"))
    })
    public Role findbyid(String id) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    public void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId);
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId);

    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId);
}
