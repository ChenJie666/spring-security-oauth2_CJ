package com.cj.oauth2.server.config.service;

import com.cj.oauth2.server.domain.TbPermission;
import com.cj.oauth2.server.domain.TbUser;
import com.cj.oauth2.server.service.impl.TbPermissionServiceImpl;
import com.cj.oauth2.server.service.impl.TbUserServiceImpl;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TbUserServiceImpl tbUserService;

    @Autowired
    private TbPermissionServiceImpl tbPermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TbUser tbUser = tbUserService.getByUsername(username);
        System.out.println("---loadUserByUsername---");
        ArrayList<GrantedAuthority> getAuthorities = Lists.newArrayList();
        if(tbUser != null) {
            Long userId = tbUser.getId();
            System.out.println(userId);
            List<TbPermission> tbPermissions = tbPermissionService.selectByUserId(userId);
            for (TbPermission tbPermission : tbPermissions) {
                if(tbPermission != null && tbPermission.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(tbPermission.getEnname());
                    System.out.println("getAutorities");
                    getAuthorities.add(grantedAuthority);
                }
            }
        }

        User user = new User(username, tbUser.getPassword(), getAuthorities);
        return user;    //TODO User实现了UserDetails接口
    }
}
