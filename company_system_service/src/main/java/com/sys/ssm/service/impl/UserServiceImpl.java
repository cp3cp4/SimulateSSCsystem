package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert userInfo != null;
        return new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(), userInfo.getStatus() != 0, true, true, true,getauthority(userInfo.getRoles()));
    }
    public List<SimpleGrantedAuthority> getauthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;

    }

    @Override
    public List<UserInfo> findall() {
        return userDao.findall();
    }

    @Override
    public void save(UserInfo user) throws Exception {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String id : ids) {
            userDao.addRoleToUser(userId,id);
        }
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }


}
