package com.apiautomation.pom;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {
    private String baseUrl;
    private RequestSpecification request;

    // Constructor
    public BaseAPI(String baseUrl) {
        this.baseUrl = baseUrl;
        this.request = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    // Method to send POST requests
    public Response sendPostRequest(String payload) {
        return request.body(payload)
                .post(baseUrl);
    }
}