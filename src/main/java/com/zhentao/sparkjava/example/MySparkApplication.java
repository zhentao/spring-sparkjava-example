package com.zhentao.sparkjava.example;

import static spark.Spark.get;

import spark.servlet.SparkApplication;

public class MySparkApplication implements SparkApplication {

    private ExampleService exampleService;

    public MySparkApplication(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @Override
    public void init() {
        get("/current-date", (req, res) -> {
            return exampleService.getDate();
        });
    }
}
