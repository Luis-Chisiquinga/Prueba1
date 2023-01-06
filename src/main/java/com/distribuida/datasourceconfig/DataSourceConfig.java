package com.distribuida.datasourceconfig;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import javax.sql.DataSource;

@ApplicationScoped
public class DataSourceConfig {

    static final String URL = "jdbc:postgresql://localhost:5432/distribuida";
    static final String USER = "postgres";
    static final String PASSWORD = "nelita2108";

    @ApplicationScoped
    @Produces
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);

        return ds;
    }

}
