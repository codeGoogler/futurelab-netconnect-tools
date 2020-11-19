package com.net.tools.futurelabnetconnecttools.service.api.service;

import com.china.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;

/**
* @ClassName IStudentService
* @Description  Service接口
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
public interface IStudentService {

  CommonResponse<StudentDto> get(Integer id);

  CommonResponse save(StudentDto dto);

  CommonResponse<StudentDto> page(int num,int size,String keyWords,StudentDto dto);

  CommonResponse remove(Integer id);

  CommonResponse listRemove(String ids);

}
