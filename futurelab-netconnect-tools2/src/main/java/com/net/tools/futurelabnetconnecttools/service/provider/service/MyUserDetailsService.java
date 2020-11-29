package com.net.tools.futurelabnetconnecttools.service.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.net.tools.futurelabnetconnecttools.common.exception.ExceptionProcessUtils;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.provider.converter.UserConverter;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.BaseUser;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements  UserDetailsService {

    @Autowired(required = false)
    public UserConverter userConverter;
    @Autowired(required = false)
    public UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.login.loadUserByUsername::ids = {}",s);
        try{
            BaseUser baseUser = new BaseUser();
            LambdaQueryWrapper<BaseUser> queryWrapper = new LambdaQueryWrapper<BaseUser>(baseUser)
                    .eq(BaseUser::getLoginName,s);
            BaseUser loginUser =  userMapper.selectOne(queryWrapper);
            if (loginUser == null) {
                throw new UsernameNotFoundException(String.valueOf(s));
            }
            return  loginUser;
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.login.listRemove::Exception ::[{}]",e);
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
