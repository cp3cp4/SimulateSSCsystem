package com.itheima.ssm.controller;


import com.itheima.ssm.dao.IPermissionDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IPermissionService;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> all = roleService.findAll();
        mv.addObject("roleList", all);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role r) throws Exception{
        roleService.save(r);

        return "redirect:findAll.do";
    }
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public  ModelAndView findrolebyidandallpermission(String id) throws Exception {
        Role findbyid = roleService.findbyid(id);
        List<Permission> findotherpermission = permissionService.findotherpermission(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role", findbyid);
        mv.addObject("permissionList", findotherpermission);
        mv.setViewName("role-permission-add");
        return mv;

    }
    @RequestMapping("/addPermissionToRole.do")
    public String addRoleToUser(@RequestParam(name = "roleId")String id, @RequestParam(name = "ids")String[] ids) throws Exception{
//    public String addRoleToUser(String id, String[] ids) throws Exception{
        roleService.addPermissionToRole(id,ids);
        return "redirect:findAll.do";

    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name="id",required = true) String id) throws Exception {
        roleService.deleteRoleById(id);
        return "redirect:findAll.do";
    }

    //角色详情查询
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findbyid(id);

        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }
}
