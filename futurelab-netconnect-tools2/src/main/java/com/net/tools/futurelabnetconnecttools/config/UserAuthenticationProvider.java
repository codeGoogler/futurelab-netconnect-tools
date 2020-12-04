package com.net.tools.futurelabnetconnecttools.config;

import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.BaseUser;
import com.net.tools.futurelabnetconnecttools.service.provider.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义登录验证
 *
 * @Author SunJ
 * @CreateTime 2019/10/1 19:11
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired(required = false)
    MyUserDetailsService myUserDetailsService;
    //    @Autowired
//    private SysUserService sysUserService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();

//        password = AuthenticationUtil.getPasswordAESde(password);

        // 查询用户是否存在
        BaseUser userInfo = (BaseUser) myUserDetailsService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        /*if (!userInfo.getPassword().equals(AuthenticationUtil.getPassword(password))) {
            throw new BadCredentialsException("密码不正确");
        }*/

        if (!userInfo.getPassword().equals(password)) {
            throw new BadCredentialsException("密码不正确");
        }

//        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
//        if (userInfo.getStatus().equals("PROHIBIT")){
//            throw new LockedException("该用户已被冻结");
//        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();

        // 查询用户角色
//        List<SysRoleEntity> sysRoleEntityList = sysUserService.selectSysRoleByUserId(userInfo.getUserId());
//        for (SysRoleEntity sysRoleEntity: sysRoleEntityList){
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
//        }
        //userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
