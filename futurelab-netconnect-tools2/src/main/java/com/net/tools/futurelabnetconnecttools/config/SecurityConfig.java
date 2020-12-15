package com.net.tools.futurelabnetconnecttools.config;

import com.net.tools.futurelabnetconnecttools.service.provider.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
// https://blog.csdn.net/Cgh_Baby/article/details/108234719
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired(required = false)
    MyUserDetailsService myUserDetailsService;

    @Autowired(required = false)
    UserAuthenticationProvider userAuthenticationProvider;

    @Autowired(required = false)
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired(required = false)
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

   @Autowired(required = false)
   UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;


    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    // 配置Security security的认证策略, 每个模块配置使用and结尾。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http
                 .formLogin()
                .loginPage("/loginIn")
                .loginProcessingUrl("/user/login/come")
                .usernameParameter("loginName")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout()
//                .logoutUrl("/login/out")
                .and()
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/user/login/come","/user/login/come2","login/out","/loginIn").permitAll()
                .antMatchers("/swagger-ui.html#!/**").permitAll()
                .antMatchers(
                         "/swagger-ui.html",
                         "/v2/api-docs", // swagger api json
                         "/swagger-resources/configuration/ui", // 用来获取支持的动作
                         "/swagger-resources", // 用来获取api-docs的URI
                         "/swagger-resources/configuration/security", // 安全选项
                         "/swagger-resources/**"
                 ).permitAll()
                .antMatchers(HttpMethod.GET,  "/*.html", "favicon.ico", "/**/*.html","/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .anyRequest().fullyAuthenticated()// 要求在执行请求要求必须已登录
                .and()
                .csrf().disable();//禁用跨站csrf攻击防御, 否则无法成功登录


//        http
//                 .formLogin()
////                .loginPage("/loginIn")
//              .loginProcessingUrl("/user/login/come")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailureHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/user/login/come1111").permitAll()
//                .antMatchers("/login/login").permitAll()
//                .antMatchers("/getlogin.html").permitAll()
//                .antMatchers(HttpMethod.POST,"/come2").permitAll()
//                .antMatchers("/getlogin.html").permitAll()
//                .antMatchers("/index","/login/out","/loginIn","/system/user1").permitAll()
//                .antMatchers(HttpMethod.GET,  "/*.html", "favicon.ico", "/**/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
//                .and()
//                .httpBasic().and().authorizeRequests().anyRequest().authenticated();




    }

    // 配置是认证信息
    // AuthenticationManagerBuilder 类是AuthenticationManager的建造者, 我们只需要向这个类中, 配置用户信息,
    // 就能生成对应的AuthenticationManager, 这个类也提过,是用户身份的管理者, 是认证的入口,
    // 因此,我们需要通过这个配置,想security提供真实的用户身份。
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }

    @Override
    public  void  configure(AuthenticationManagerBuilder  auth)  throws  Exception{
        //调用DetailsService完成用户身份验证              设置密码加密方式
//        auth.userDetailsService(myUserDetailsService);
//        auth.userDetailsService(myUserDetailsService).passwordEncoder(new CustomPasswordEncoder());
        //这里可启用我们自己的登陆验证逻辑
        auth.authenticationProvider(userAuthenticationProvider);
    }

    // 配置认证方法, http basic登录基础配置
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(getBCryptPasswordEncoder().encode("123456"))
//                .roles("ROLE_user")
//                .and()
//                .withUser("admin")
//                .password(getBCryptPasswordEncoder().encode("123456"))
//                .roles("ROLE_admin")
//                .and()
//                .passwordEncoder(getBCryptPasswordEncoder());//配置BCrypt加密
//    }
//


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public class CustomPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence charSequence) {
            return charSequence.toString();
        }

        @Override
        public boolean matches(CharSequence charSequence, String s) {
            return s.equals(charSequence.toString());
        }
    }
}
