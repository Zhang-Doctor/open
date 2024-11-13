package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.component.JwtTokenUtil;
import com.example.demo.params.LoginParam;
import com.example.demo.service.SysUserService;
import com.example.demo.entry.SysUser;
import com.example.demo.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private SysUserMapper sysUserMapper;

    private static Map<String, SysUser> userMap = new HashMap<>();

    static {
        userMap.put("name1", new SysUser("name1", "password"));
        userMap.put("name2", new SysUser("name2", "password"));
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("找不到用户名");
        }
        return user;
    }

    @Override
    public String login(LoginParam loginParam) {
        UserDetails userDetails = loadUserByUsername(loginParam.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }
}
