package com.itheima.ssm.controller;


import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserService;
import com.itheima.ssm.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService user;

    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findall() throws Exception{
        List<UserInfo> findall = user.findall();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",findall);
        mv.setViewName("user-list");
        return mv;

    }

    @RequestMapping("/save.do")
    public ModelAndView save(UserInfo users) throws Exception {
       user.save(users);
       ModelAndView mv = new ModelAndView();
        List<UserInfo> findall = user.findall();
        mv.addObject("userList",findall);
        mv.setViewName("user-list");
        return mv;
//        return "redirect:findAll.do";

    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        UserInfo byId = user.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", byId);
        mv.setViewName("user-show");
        return mv;

    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = user.findById(id);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = user.findOtherRoles(id);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;

    }


    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] ids) throws Exception {
        user.addRoleToUser(userId, ids);
        return "redirect:findAll.do";
    }


}
