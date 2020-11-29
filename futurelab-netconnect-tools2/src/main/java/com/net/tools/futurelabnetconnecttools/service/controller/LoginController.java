package com.net.tools.futurelabnetconnecttools.service.controller;

import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.service.api.service.IStudentService;
import com.net.tools.futurelabnetconnecttools.service.provider.service.LoginServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "LoginController" , tags = "前端控制器")
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
    @PostMapping("/come")
    @ApiOperation(value = "列表查询", httpMethod = "POST")
    public CommonResponse<StudentDto> login(@RequestBody  BaseUserDto baseUserDto) {
        return loginService.login(baseUserDto);
    }

    @PostMapping("/come2")
    @ApiOperation(value = "列表查询", httpMethod = "POST")
    public CommonResponse<StudentDto> login2(@RequestBody  BaseUserDto baseUserDto) {
        return loginService.login(baseUserDto);
    }

}