package com.net.tools.futurelabnetconnecttools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.net.tools.futurelabnetconnecttools.service.provider.dal.mapper"})
@EnableScheduling
public class FuturelabNetconnectToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuturelabNetconnectToolsApplication.class, args);
		System.out.println("=========  http://localhost:8097/swagger-ui.html#  ============");
	}

}
