package com.net.tools.futurelabnetconnecttools.service.api.dto;


import com.net.tools.futurelabnetconnecttools.common.req.AbstractResponse;
import lombok.Data;
import lombok.ToString;
import io.swagger.annotations.ApiModelProperty;

/**
* @ClassName StudentDto
* @Description TODO
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Data
@ToString
public class StudentDto extends AbstractResponse {

    private static final long serialVersionUID = 1L;
    /**
    * 姓名
    */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
    * 年龄
    */
    @ApiModelProperty(value = "年龄")
    private Integer age;
}
