package com.multi_tenant_share_db.share_db.config;

import com.multi_tenant_share_db.share_db.config.multitenancy.TenantAwareDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Configuration
public class DataSourceConfiguration {

  public DataSourceConfiguration() {
  }

  @Bean
  @ConfigurationProperties("multitenancy.master.datasource")
  public DataSourceProperties masterDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @LiquibaseDataSource
  @ConfigurationProperties("multitenancy.master.datasource.hikari")
  public DataSource masterDataSource() throws Exception {
    HikariDataSource dataSource = masterDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("masterDataSource");
    return dataSource;
  }

  @Bean
  @Primary
  @ConfigurationProperties("multitenancy.tenant.datasource")
  public DataSourceProperties tenantDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("multitenancy.tenant.datasource.hikari")
  public DataSource tenantDataSource() throws Exception {
    HikariDataSource dataSource = tenantDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("tenantDataSource");
    return new TenantAwareDataSource(dataSource);
  }
}
