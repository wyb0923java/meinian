package com.atguigu.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.PermissionService;
import com.atguigu.UserService;
import com.atguigu.pojo.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpringSecurityUserService implements UserDetailsService {

    @Reference
    private UserService userService;

    @Reference
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserInforByUsername(username);
        if (user == null){
            return null;
        }
        List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>();

        List<String> permissions=permissionService.getAuthoritiesByUid(user.getId());
        for (String permission : permissions){
            authorityList.add(new SimpleGrantedAuthority(permission));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }
}
