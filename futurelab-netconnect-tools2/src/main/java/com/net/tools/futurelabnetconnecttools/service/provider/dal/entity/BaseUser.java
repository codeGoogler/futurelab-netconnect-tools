package com.net.tools.futurelabnetconnecttools.service.provider.dal.entity;

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
@TableName("base_user")
public class BaseUser extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id ;
    /**
     * 姓名
     */
    private String loginName;

    /**
     *密碼
     */
    private String password;
    private String phone;


    @TableField(exist = false)
    private Integer stuId;

    @ApiModelProperty(value = "删除标志【0：是未刪除，1：正常使用】")
    private Integer isDeleted;



    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.isDeleted.equals("1")){
            return false;
        }
        return true;
    }
}
