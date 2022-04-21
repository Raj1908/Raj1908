package com.msf.ds;

import javax.annotation.PreDestroy;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.msf.jdbc.ds.MSFGenericDataSource;

@Configuration
public class MSFjdbcMonitorDataSource implements MSFGenericDataSource{
	private DataSource pool;
	
	@Bean(name = "jdbcMonitor") 
	@Primary
	public DataSource dataSource() { 
	    this.pool = new org.apache.tomcat.jdbc.pool.DataSource();
	    this.pool.setDriverClassName("com.mysql.jdbc.Driver");
	    this.pool.setUrl("jdbc:mysql://localhost:3306/msf_ui?useSSL=false&useJDBCCompliantTimezoneShift=false&useLegacyDatetimeCode=false&serverTimezone=EST5EDT");
	    this.pool.setUsername("root");
	    this.pool.setPassword("orcl");
	    
	    return this.pool;

	} 

	@PreDestroy
	public void close() {
	    if (this.pool != null) {
	        this.pool.close();
	    }
	}

	@Override
	@Bean(name = "jdbcMonitor") 	
	public JdbcTemplate jdbcTemplate(@Qualifier("jdbcMonitor")DataSource dsMSFJDBCDataSource) { 
	    return new JdbcTemplate(dsMSFJDBCDataSource); 
	}	
}
