package com.example.fixtures

trait ConfigurationFixture implements PostgresqlFixture {

    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [:] as HashMap

        if (specName) {
            m['spec.name'] = specName
        }

        m += postgresqlConfiguration

        m
    }

    String getSpecName() {
        null
    }

}
