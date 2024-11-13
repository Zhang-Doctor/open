package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entry.SysUser;
import com.example.demo.params.LoginParam;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.provisioning.UserDetailsManager;

public interface SysUserService extends IService<SysUser>, UserDetailsManager, UserDetailsPasswordService {

    String login(LoginParam loginParam);
}
