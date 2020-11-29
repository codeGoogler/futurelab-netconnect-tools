package com.net.tools.futurelabnetconnecttools.service.provider.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.net.tools.futurelabnetconnecttools.service.api.dto.BaseUserDto;
import com.net.tools.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.BaseUser;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

/**
* @ClassName StudentConverter
* @Description 转换器
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Mapper(componentModel = "spring")
public interface UserConverter {

    public Page<BaseUserDto> entityToDto(IPage<BaseUser> parkIPage);

    public BaseUserDto entityToDto(BaseUser dto);

    public List<BaseUserDto> entityToDto(List<BaseUser> list);

    public BaseUser dtoToEntity(BaseUserDto dto);

}