package com.hall.garage.sale.ds;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.sql.DataSource;

@DataSourceDefinition(name = "jdbc:/_garage.sale",
minPoolSize = 0,
initialPoolSize = 0,
className = "com.mysql.jdbc.Driver",
user = "adminCEfcLrc",
password = "WBM9W3C5BJea",
databaseName = "garagesale",
properties = {"createDatabase=create"}
)
@Singleton
public class DataSourceEJB {
	
	@Resource(lookup="jdbc:/_garage.sale")
	private DataSource ds;

}
