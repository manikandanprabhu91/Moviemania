//package com.carefirst.auth.config;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.util.StringUtils;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@ConfigurationProperties("datasources")
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.carefirst.auth.repository", entityManagerFactoryRef = "appEntityManagerFactory", transactionManagerRef = "appTransactionManager")
//public class DatabaseConfig {
//
//	private static final Logger logger = LogManager.getLogger(DatabaseConfig.class);
//
//	private String driver;
//	private String url;
//	private String vaultquery;
//	private long connTimeOut;
//	private int maxPoolSize;
//	private String hbm2ddlauto;
//	private String showsql;
//	private String schema;
//
//	private static HikariDataSource ds;
//
//	/**
//	 * This Bean returns the datasource used internally by Spring Boot.
//	 * 
//	 * @return DataSource
//	 * @Throws SQLException
//	 */
//	@Bean
//	@Primary
//	public DataSource hsqldbDataSource() throws SQLException {
//		logger.info(" > hsqldbDataSource");
//		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		dataSource.setDriver(new org.hsqldb.jdbcDriver());
//		dataSource.setUrl("jdbc:hsqldb:mem:mydb");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("");
//		logger.info(" < hsqldbDataSource");
//		return dataSource;
//	}
//
//	/**
//	 * This Bean returns the DB Configuration used internally by Spring Boot.
//	 * 
//	 * @return DataSource
//	 * @Throws ApplicationException
//	 */
//	@Bean
//	public HikariDataSource appDataSource() throws Exception {
//		logger.info(" > appDataSource");
//		HikariConfig config = new HikariConfig();
//		config.setJdbcUrl(getUrl());
//		config.setDriverClassName(getDriver());
//		//		config.setUsername(PMConnector.getConnector().getUserName(getVaultquery()));
//		//		config.setPassword(PMConnector.getConnector().getContent(getVaultquery()));
//		//		String pwd = PMConnector.getConnector().getContent(getVaultquery());
//		//		config.setPassword(pwd);
//		//		logger.info("Database coneection: connecting EAPM ID ::"+config.getUsername() +":::"+pwd.length());
//		//		logger.info("Database coneection: connecting EAPM ID ::"+config.getUsername());
//		if (StringUtils.isEmpty(config.getUsername()) || StringUtils.isEmpty(config.getPassword())) {
//			logger.info("Error: DB credentials not retrieved from EAPM");
//		}
//		config.setConnectionTimeout(getConnTimeOut());
//		config.setMaximumPoolSize(getMaxPoolSize());
//		logger.info(" < appDataSource");
//		return ds = new HikariDataSource(config);
//	}
//
//	/**
//	 * This Bean returns the JpaTransaction Manager used internally by Spring Boot.
//	 * 
//	 * @return JpaTransactionManager
//	 * @Throws ApplicationException
//	 */
//	@Bean
//	public PlatformTransactionManager appTransactionManager() throws Exception {
//		logger.info(" > appTransactionManager");
//		EntityManagerFactory factory = appEntityManagerFactory().getObject();
//		logger.info(" < appTransactionManager");
//		return new JpaTransactionManager(factory);
//	}
//
//	/**
//	 * This Bean returns the JpaTransaction Manager used internally by Spring Boot.
//	 * 
//	 * @return JpaProperties
//	 * @Throws ApplicationException
//	 */
//	@Bean(name="batchEntityManagerFactory")
//	@Primary
//	public LocalContainerEntityManagerFactoryBean appEntityManagerFactory() throws Exception {
//		logger.info(" > appEntityManagerFactory");
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setDataSource(appDataSource());
//		//In case we use entity project
//		factory.setPackagesToScan("com.carefirst.auth.entity");
//		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		Properties jpaProperties = new Properties();
//		jpaProperties.put("hibernate.default_schema", getSchema());
//		factory.setJpaProperties(jpaProperties); 
//		logger.info(" < appEntityManagerFactory");
//		return factory;
//	}
//
//	/**
//	 * @return the driver
//	 */
//	public String getDriver() {
//		return driver;
//	}
//
//	/**
//	 * @param driver the driver to set
//	 */
//	public void setDriver(String driver) {
//		this.driver = driver;
//	}
//
//	/**
//	 * @return the url
//	 */
//	public String getUrl() {
//		return url;
//	}
//
//	/**
//	 * @param url the url to set
//	 */
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	/**
//	 * @return the vaultquery
//	 */
//	public String getVaultquery() {
//		return vaultquery;
//	}
//
//	/**
//	 * @param vaultquery the vaultquery to set
//	 */
//	public void setVaultquery(String vaultquery) {
//		this.vaultquery = vaultquery;
//	}
//
//	/**
//	 * @return the connTimeOut
//	 */
//	public long getConnTimeOut() {
//		return connTimeOut;
//	}
//
//	/**
//	 * @param connTimeOut the connTimeOut to set
//	 */
//	public void setConnTimeOut(long connTimeOut) {
//		this.connTimeOut = connTimeOut;
//	}
//
//	/**
//	 * @return the maxPoolSize
//	 */
//	public int getMaxPoolSize() {
//		return maxPoolSize;
//	}
//
//	/**
//	 * @param maxPoolSize the maxPoolSize to set
//	 */
//	public void setMaxPoolSize(int maxPoolSize) {
//		this.maxPoolSize = maxPoolSize;
//	}
//
//
//	/**
//	 * @return the showsql
//	 */
//	public String getShowsql() {
//		return showsql;
//	}
//
//
//	/**
//	 * @param showsql the showsql to set
//	 */
//	public void setShowsql(String showsql) {
//		this.showsql = showsql;
//	}
//
//
//	/**
//	 * @return the hbm2ddlauto
//	 */
//	public String getHbm2ddlauto() {
//		return hbm2ddlauto;
//	}
//
//
//	/**
//	 * @param hbm2ddlauto the hbm2ddlauto to set
//	 */
//	public void setHbm2ddlauto(String hbm2ddlauto) {
//		this.hbm2ddlauto = hbm2ddlauto;
//	}
//
//
//	/**
//	 * @return the schema
//	 */
//	public String getSchema() {
//		return schema;
//	}
//
//
//	/**
//	 * @param schema the schema to set
//	 */
//	public void setSchema(String schema) {
//		this.schema = schema;
//	}
//
//	public Connection getConnection() throws SQLException {
//		return ds.getConnection();
//	}
//
//}
