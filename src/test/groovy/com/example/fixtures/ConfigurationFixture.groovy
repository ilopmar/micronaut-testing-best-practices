package com.example.fixtures

trait ConfigurationFixture implements PostgresqlFixture {

    abstract int getOmdbServerPort()

    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [
            'mockSecurityService': mockSecurityServiceEnabled()
        ] as HashMap

        if (specName) {
            m['spec.name'] = specName
        }

        if (omdbSpecName) {
            m['omdb.base-url'] = "http://localhost:$omdbServerPort"
        }

        m += postgresqlConfiguration

        m
    }

    String getSpecName() {
        null
    }

    boolean mockSecurityServiceEnabled() {
        true
    }

    String getOmdbSpecName() {
        null
    }

}
