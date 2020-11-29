package com.net.tools.futurelabnetconnecttools.service.controller;


import com.net.tools.futurelabnetconnecttools.common.IDRequest;
import com.net.tools.futurelabnetconnecttools.common.RequestJson;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.service.api.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @ClassName StudentController
* @Description  前端控制器
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Api(value = "StudentController" , tags = "前端控制器")
@RestController
@RequestMapping("/futurelabnetconnecttools/student")
public class StudentController {

    @Autowired(required = false)
    private IStudentService studentService;

    /**
    *@Description 列表分页查询
    *@Param [num, size, keyWords,dto]
    *@Author yuyahao
    *@Date 2020-11-18
    */
    @GetMapping("/page/{num}/{size}")
    @ApiOperation(value = "列表查询",httpMethod = "GET")
    public CommonResponse<StudentDto> page(@PathVariable int num, @PathVariable int size, String keyWords, StudentDto dto){
        return studentService.page(num,size,keyWords,dto);
    }


    /**
    *@Description 根据ID查询
    *@Param [id]
    *@Author yuyahao
    *@Date 2020-11-18
    */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "根据id获取数据",httpMethod = "GET")
    public CommonResponse<StudentDto> get(@PathVariable Integer id){
        return studentService.get(id);
    }

    /**
    *@Description 新增/修改
    *@Param [dto]
    *@Return com.china.soft.commons.response.CommonResponse
    *@Author yuyahao
    *@Date 2020-11-18
    */
    @PostMapping("/save")
    @ApiOperation(value = "新增/修改",httpMethod = "POST")
    public CommonResponse save(@RequestBody StudentDto dto){
        return studentService.save(dto);
    }

    /**
    *@Description 删除
    *@Param [id]
    *@Return com.china.soft.commons.response.CommonResponse
    *@Author yuyahao
    *@Date 2020-11-18
    */
    @PostMapping("/remove")
    @ApiOperation(value = "删除",httpMethod = "POST")
    public CommonResponse remove(@RequestBody IDRequest id){
        return studentService.remove(id.getId());
    }

    /**
    *@Description 批量删除
    *@Param [id]
    *@Return com.china.soft.commons.response.CommonResponse
    *@Author yuyahao
    *@Date 2020-11-18
    */
    @PostMapping("/listRemove")
    @ApiOperation(value = "批量删除",httpMethod = "POST")
    public CommonResponse listRemove(@RequestJson("ids") String ids){
        return studentService.listRemove(ids);
    }

}
