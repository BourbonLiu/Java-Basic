package com.lcpan.m08;

import javax.sql.DataSource;

// Here are the dbcp-specific classes.
// Note that they are only used in the setupDataSource
// method. In normal use, your classes interact
// only with the standard JDBC API
//
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;

public class DataSourceFactory {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
	private static final String USER = "allen";
	private static final String PASSWORD = "allen";
	
//	用datasource(interface)一定要做出connection pooling
	public static DataSource getDataSource() {
		// ConnectionFactory物件的功能是用來建立real Connections
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(DB_URL, USER, PASSWORD);

		// PoolableConnectionFactory物件的功能是用來將real Connections包裝成poolable Connections
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		// GenericObjectPool物件的功能是用來產生連線池物件,並將poolable Connections加入其中
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

		// 設定factory的pool屬性為那個連線池物件
		poolableConnectionFactory.setPool(connectionPool);

		// 透過連線池物件產生一個PoolingDataSource物件,亦即PoolingDriver
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

		return dataSource;
	}
}
