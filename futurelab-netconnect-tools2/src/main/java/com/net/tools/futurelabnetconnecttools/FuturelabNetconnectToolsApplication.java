package com.net.tools.futurelabnetconnecttools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper"})
@EnableScheduling
//@EnableAutoConfiguration(exclude = {
//		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
public class FuturelabNetconnectToolsApplication {


	public static void main(String[] args) {
		SpringApplication.run(FuturelabNetconnectToolsApplication.class, args);
		System.out.println(String.format("=========  http://localhost:%s/swagger-ui.html#  ============",8097));
	}


}
