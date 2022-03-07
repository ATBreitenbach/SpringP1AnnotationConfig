package config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.stereotype.Repository;

import disburse.dao.JdbcHouseDisburseDAO;

@Configuration
@ComponentScan({"Disburse"})
public class HouseDisburseConfig {

	@Bean
	public DataSource ds() throws SQLException {
		DataSource ds = new EmbeddedDatabaseBuilder().addScript("classpath:2013Q4_HOUSE_DISBURSE.sql").build();
		ds.getConnection().setAutoCommit(true);
		return ds;
	}
	
	@Repository
	public JdbcHouseDisburseDAO dao(DataSource ds) {
		JdbcHouseDisburseDAO dao = new JdbcHouseDisburseDAO(ds);
		return dao;
	}

	

}
