package com.example.fixtures

import com.example.Postgresql

trait PostgresqlFixture {

    Map<String, Object> getPostgresqlConfiguration() {
        if (Postgresql.postgreSQLContainer == null || !Postgresql.postgreSQLContainer.isRunning()) {
            Postgresql.init()
        }
        [
            'datasources.default.url'     : Postgresql.postgreSQLContainer.getJdbcUrl(),
            'datasources.default.password': Postgresql.postgreSQLContainer.getPassword(),
            'datasources.default.username': Postgresql.postgreSQLContainer.getUsername(),
        ]
    }
}
