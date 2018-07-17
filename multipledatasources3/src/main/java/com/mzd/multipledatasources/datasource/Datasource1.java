package com.mzd.multipledatasources.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties注解的作用: 
 * 根据application.properties配置文件前缀为prefix值的属性映射成一个实体类
 * 只要prefix后面的名与实体对象的数据名一致就能自动注入进去
 * @Value 
 * 读取配置文件中的单个参数赋值给一个变量
 * 这个变量可以和配置文件中的值不一样
 * 但是@Value("${参数值}")必须和配置文件中的参数值保持一致
 */
@ConfigurationProperties(prefix = "spring.datasource.test1")
public class Datasource1 {
	private String url;
	private String username;
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
