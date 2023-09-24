package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"),
               @Result(column = "username", property = "username"),
               @Result(column = "email", property = "email"),
               @Result(column = "password", property = "password"),
               @Result(column = "phoneNum", property = "phoneNum"),
               @Result(column = "status", property = "status"),
               @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId")) })
    public UserInfo findById(String id) throws Exception;


    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class, many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId")) })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findall();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo user);

    @Insert("insert into users_role(userId,roleId) value(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId);

}
