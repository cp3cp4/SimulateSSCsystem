package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findall() throws Exception;

    public void save(Permission p) throws Exception;

    public Permission findById(String id) throws Exception;

    public void deleteById(String id) throws Exception;
}
