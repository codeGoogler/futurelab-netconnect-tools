package com.net.tools.futurelabnetconnecttools.service.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.net.tools.futurelabnetconnecttools.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class BaseUserDto extends BaseEntity  {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     */
    private String loginName;
    /**
     *密碼
     */
    private String password;
}
