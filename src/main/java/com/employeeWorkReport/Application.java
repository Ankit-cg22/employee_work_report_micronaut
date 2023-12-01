package com.employeeWorkReport;

import io.micronaut.context.annotation.Bean;
import io.micronaut.http.server.HttpServerConfiguration;
import io.micronaut.http.server.cors.CorsFilter;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);


    }
}