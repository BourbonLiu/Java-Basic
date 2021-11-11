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
	
//	��datasource(interface)�@�w�n���Xconnection pooling
	public static DataSource getDataSource() {
		// ConnectionFactory���󪺥\��O�Ψӫإ�real Connections
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(DB_URL, USER, PASSWORD);

		// PoolableConnectionFactory���󪺥\��O�ΨӱNreal Connections�]�˦�poolable Connections
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		// GenericObjectPool���󪺥\��O�ΨӲ��ͳs�u������,�ñNpoolable Connections�[�J�䤤
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

		// �]�wfactory��pool�ݩʬ����ӳs�u������
		poolableConnectionFactory.setPool(connectionPool);

		// �z�L�s�u�����󲣥ͤ@��PoolingDataSource����,��YPoolingDriver
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

		return dataSource;
	}
}
