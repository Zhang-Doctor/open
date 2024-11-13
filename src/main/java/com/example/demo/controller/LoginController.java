package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.entry.SysUser;
import com.example.demo.params.LoginParam;
import com.example.demo.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 根据用户名密码登录获取token
     */
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginParam loginParam) {
        String token = sysUserService.login(loginParam);
        return CommonResult.success(token);
    }
}
