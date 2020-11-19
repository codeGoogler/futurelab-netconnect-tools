package com.net.tools.futurelabnetconnecttools.service.provider.service;

import com.net.tools.futurelabnetconnecttools.common.exception.ExceptionProcessUtils;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponse;
import com.net.tools.futurelabnetconnecttools.common.req.CommonResponseFactory;
import com.net.tools.futurelabnetconnecttools.service.provider.converter.StudentConverter;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.entity.Student;
import com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper.StudentMapper;
import com.net.tools.futurelabnetconnecttools.service.api.service.IStudentService;
import com.china.futurelabnetconnecttools.service.api.dto.StudentDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
* @ClassName IStudentService
* @Description  Service实现
* @Author yuyahao
* @Date 2020-11-18
* @Version 1.0
*/
@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired(required = false)
    private StudentConverter studentConverter;

    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Override
    public CommonResponse<StudentDto> get(Integer id) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.get::id = {}",id);
        CommonResponse<StudentDto> response = null;
        try{
            Student entity = studentMapper.selectById(id);
            StudentDto dto = studentConverter.entityToDto(entity);
            response = CommonResponseFactory.getInstance().success("成功",dto);
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.get::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public CommonResponse save(StudentDto dto) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.save::Enterprise = [{}]",dto);
        CommonResponse response = null;
        try{
            Student entity = studentConverter.dtoToEntity(dto);
//            Student entity = new Student();
            entity.setAge(dto.getAge());
            entity.setName(dto.getName());
            if(entity.getId() == null){
                studentMapper.insert(entity);
            }else {
                studentMapper.updateById(entity);
            }
            response = CommonResponseFactory.getInstance().success("成功",null);
        }catch (Exception e){
        log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.save::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public CommonResponse<StudentDto> page(int num,int size,String keyWords,StudentDto dto) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.page::pageInfo =[{}]",dto);
        CommonResponse<StudentDto> response = null;
        try{
            Student entity = studentConverter.dtoToEntity(dto);
            LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>(entity);
            if(null != keyWords && !keyWords.equals("")){
                //queryWrapper.and(wrapper->wrapper.like(Student::getName,keyWords).or().like(Student::getMobile,keyWords));
            }
            IPage<StudentDto> pageDto= studentConverter.entityToDto(studentMapper.selectPage(new Page<>(num,size),queryWrapper));
            response = CommonResponseFactory.getInstance().success("成功",pageDto);
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.page::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public CommonResponse remove(Integer id) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.remove::id = {}",id);
        CommonResponse<StudentDto> response = null;
        try{
            studentMapper.deleteById(id);
            response = CommonResponseFactory.getInstance().success("成功",null);
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.remove::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public CommonResponse listRemove(String ids) {
        log.info("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.listRemove::ids = {}",ids);
        CommonResponse<StudentDto> response = null;
        try{
            LambdaQueryWrapper wrapper = new LambdaQueryWrapper<Student>().apply("name IN("+ids+")");
            studentMapper.delete(wrapper);
            response = CommonResponseFactory.getInstance().success("成功",null);
        }catch (Exception e){
            log.error("com.china.futurelabnetconnecttools.service.provider.service.StudentServiceImpl.listRemove::Exception ::[{}]",e);
            response=CommonResponseFactory.getInstance().error("失败");
            ExceptionProcessUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
