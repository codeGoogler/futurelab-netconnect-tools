package com.net.tools.futurelabnetconnecttools.service.api.service;

import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;

/**
* @ClassName IStudentService
* @Description  Service接口
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
public interface IUserService {

  CommonResponse<BaseUserDto> get(Integer id);

  CommonResponse save(BaseUserDto dto);

  CommonResponse<BaseUserDto> page(int num, int size, String keyWords, BaseUserDto dto);

  CommonResponse remove(Integer id);

  CommonResponse listRemove(String ids);

  CommonResponse login(BaseUserDto baseUserDto);

  CommonResponse loginOut(BaseUserDto baseUserDto);
}
