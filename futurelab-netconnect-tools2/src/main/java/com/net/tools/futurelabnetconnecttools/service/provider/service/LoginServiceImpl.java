package com.net.tools.futurelabnetconnecttools.service.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.net.tools.futurelabnetconnecttools.common.exception.ExceptionProcessUtils;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.service.IUserService;
import com.net.tools.futurelabnetconnecttools.service.provider.converter.UserConverter;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.BaseUser;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper.UserMapper;
import com.net.tools.futurelabnetconnecttools.utils.JwtTokenUtil;
import com.net.tools.futurelabnetconnecttools.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
* @ClassName IStudentService
* @Description  Service实现
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Slf4j
@Service
public class LoginServiceImpl {
    @Autowired(required = false)
    public UserConverter userConverter;
    @Autowired(required = false)
    public UserMapper userMapper;

    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public CommonResponse login(BaseUserDto baseUserDto) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.listRemove::ids = {}",baseUserDto);
        CommonResponse response = null;
        try{
            BaseUser baseUser =  userConverter.dtoToEntity(baseUserDto);
            /*LambdaQueryWrapper<BaseUser> queryWrapper = new LambdaQueryWrapper<BaseUser>(baseUser);
            BaseUserDto baseUserDtto = userConverter.entityToDto(userMapper.selectOne(queryWrapper));
            if(baseUserDtto == null){
                response=CommonResponseFactory.getInstance().error("用户不存在");
            }else {
                response=CommonResponseFactory.getInstance().success("登录成功");
            }*/

            // 用户验证
            Authentication authentication = null;
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(baseUser.getLoginName(),baseUser.getPassword()));
            BaseUser loginUser = (BaseUser) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(loginUser);
            // 生成token
            HashMap<String,Object> result = new HashMap<>();
            result.put("token",token);
            response = CommonResponseFactory.getInstance().success(result);
            return  response;
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.listRemove::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
