package com.net.tools.futurelabnetconnecttools.controller;


import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.service.provider.service.LoginServiceImpl;
import com.net.tools.futurelabnetconnecttools.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    // 登录成功首页
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    //用户管理
    @GetMapping("/system/user")
    public String userList(){
        return "user";
    }

    @ResponseBody
    @GetMapping("/system/user1")
    public Map<String,Object> userList2(){
        return ResultUtil.resultCode(200,"success");
    }

    //角色管理
    @GetMapping("/system/role")
    public String roleList(){
        return "role";
    }

    //菜单管理
    @GetMapping("/system/menu")
    public String menuList(){
        return "menu";
    }

    //订单管理
    @GetMapping("/order")
    public String orderList(){
        return "order";
    }


//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
    @GetMapping("/loginIn")
    public String loginIn(){
//        return "user";
        return "loginin";
    }

    @GetMapping("/getlogin.html")
    public String loginInhtml(){
        return "menu";
//        return "loginin";
    }

    @GetMapping("/login/out")
    public String loginOut(){
        return "loginout";
    }

    @Autowired(required = false)
    private LoginServiceImpl loginService;

    /**
     * @Description 列表分页查询
     * @Param [num, size, keyWords,dto]
     * @Author yuyahao
     * @Date 2020-11-18
     */
    @ResponseBody
    @GetMapping("/come")
    @ApiOperation(value = "列表查询", httpMethod = "GET")
    public CommonResponse<StudentDto> login(BaseUserDto baseUserDto) {
        return loginService.login(baseUserDto);
    }
    @ResponseBody
    @PostMapping("/come2")
    @ApiOperation(value = "列表查询", httpMethod = "POST")
    public CommonResponse<StudentDto> login2(@RequestBody  BaseUserDto baseUserDto) {
        return loginService.login(baseUserDto);
    }

}
