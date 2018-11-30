package com.gl.weave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(value="com.gl.weave.dao")
public class WeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeaveApplication.class, args);
	}
}
