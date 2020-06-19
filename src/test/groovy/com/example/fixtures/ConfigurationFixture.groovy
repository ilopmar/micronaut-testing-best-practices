package com.example.fixtures

trait ConfigurationFixture {

    Map<String, Object> getConfiguration() {
        Map<String, Object> m = [:] as HashMap

        if (specName) {
            m['spec.name'] = specName
        }
        m
    }

    String getSpecName() {
        null
    }

}
