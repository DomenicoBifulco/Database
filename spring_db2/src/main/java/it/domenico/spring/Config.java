package it.domenico.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import it.domenico.Db;

@Configuration
@ComponentScan(basePackageClasses = Db.class)
public class Config {
	
	@Bean
	public Db getDb()
	{
		return new Db("com.mysql.jdbc.Driver","jdbc:mysql://localhost/officina","admin","admin");
	}

}
