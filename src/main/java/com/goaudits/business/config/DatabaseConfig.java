package com.goaudits.business.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	public Properties getProperties() throws FileNotFoundException, IOException {
//		String path = System.getProperty("user.home") + "/" + "goaudits.properties";
		String filePath = ConnectionUtils.propertiesurl + "/" + "goaudits.properties";
		System.out.println("Properties file path:" + filePath);
		Properties prop = new Properties();
		prop.load(new FileInputStream(filePath.replace('\\', '/')));
		return prop;
	}
	
	@Bean
	public DriverManagerDataSource dataSource() throws FileNotFoundException, IOException, SQLException {		
	
		DriverManagerDataSource dataSource = null;
		try {		    
			Properties prop = getProperties();
			System.out.println("Database URL: "+prop.getProperty("url"));
			dataSource = new DriverManagerDataSource();			
			dataSource.setDriverClassName(prop.getProperty("driver"));
			dataSource.setUrl(prop.getProperty("url"));
			dataSource.setUsername(prop.getProperty("user"));
			dataSource.setPassword(prop.getProperty("password"));		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return dataSource;
	}		
}