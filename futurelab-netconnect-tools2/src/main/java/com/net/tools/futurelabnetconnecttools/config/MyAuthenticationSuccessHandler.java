package com.net.tools.futurelabnetconnecttools.config;

import com.net.tools.futurelabnetconnecttools.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HashMap<String,Object> resuleMap = new HashMap<>();
        resuleMap.put("message","登录成功");
        resuleMap.put("code", 200);
        ResultUtil.responseJson(httpServletResponse,resuleMap);
//        ResultUtil.resultSuccess(resuleMap);
    }
}
