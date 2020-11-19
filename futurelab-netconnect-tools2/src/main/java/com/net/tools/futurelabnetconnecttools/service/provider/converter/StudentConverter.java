package com.net.tools.futurelabnetconnecttools.service.provider.converter;

import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.Student;
import com.china.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface StudentConverter {

    public Page<StudentDto> entityToDto(IPage<Student> parkIPage);

    public StudentDto entityToDto(Student dto);

    public List<StudentDto> entityToDto(List<Student> list);

    public Student dtoToEntity(StudentDto dto);

}