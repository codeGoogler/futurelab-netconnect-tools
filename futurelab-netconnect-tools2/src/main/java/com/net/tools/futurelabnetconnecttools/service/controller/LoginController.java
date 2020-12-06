package com.net.tools.futurelabnetconnecttools.service.controller;

import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.service.api.service.IStudentService;
import com.net.tools.futurelabnetconnecttools.service.provider.service.LoginServiceImpl;
import com.net.tools.futurelabnetconnecttools.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/user/login")
public class LoginController {

    @Autowired(required = false)
    private LoginServiceImpl loginService;

    /**
     * @Description 列表分页查询
     * @Param [num, size, keyWords,dto]
     * @Author yuyahao
     * @Date 2020-11-18
     */
    @RequestMapping("/come")
    @ApiOperation(value = "列表查询", httpMethod = "POST")
    @ResponseBody
    public CommonResponse<StudentDto> login(@RequestBody  BaseUserDto baseUserDto) {
        return loginService.login(baseUserDto);
    }

    @PostMapping("/come2")
    @ApiOperation(value = "列表查询", httpMethod = "POST")
    @ResponseBody
    public CommonResponse<Map<String,Object>> login2(@RequestBody  BaseUserDto baseUserDto) {

        return CommonResponseFactory.getInstance().success(ResultUtil.resultCode(200,"SUCCESS"));
//        return loginService.login(baseUserDto);
    }

}