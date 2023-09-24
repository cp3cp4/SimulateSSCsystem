package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IUserService extends UserDetailsService {
        public List<UserInfo> findall() throws Exception;
        public void save(UserInfo userInfo) throws Exception;
        public UserInfo findById(String id) throws Exception;
        public void addRoleToUser(String userId,String [] ids) throws Exception;
        List<Role> findOtherRoles(String userId) throws Exception;

}
