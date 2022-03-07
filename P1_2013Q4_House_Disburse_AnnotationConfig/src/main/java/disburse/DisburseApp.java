package disburse;

import java.beans.Beans;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import config.HouseDisburseConfig;
import disburse.dao.JdbcHouseDisburseDAO;
import disburse.vo.HouseDisburseDetail;

public class DisburseApp {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(HouseDisburseConfig.class);
		JdbcHouseDisburseDAO dao = (JdbcHouseDisburseDAO) context.getBean("dao");
		System.out.println(dao.getTotalAmountSpent());
		
		List<HouseDisburseDetail> hddList = dao.selectQuery("A000055");
		for (HouseDisburseDetail hdd : hddList) {
			System.out.println(hdd.toString());
		}
		
		Map<String, Double> hddMap = dao.getTotalAmountSpentByBIOGUIDE_ID();
		for (Map.Entry<String, Double> hdMap : hddMap.entrySet()) {     
			System.out.println("Key = " + hdMap.getKey() + ", Value = " + hdMap.getValue()); 
		}
		
		System.out.println("Total Records before delete: " + dao.getTotalRecords());
		System.out.println("Total Records deleted: " + dao.deleteRecordsByBIOGUIDEID("A000055"));
		System.out.println("Total Records after delete: " + dao.getTotalRecords());
	}

}
