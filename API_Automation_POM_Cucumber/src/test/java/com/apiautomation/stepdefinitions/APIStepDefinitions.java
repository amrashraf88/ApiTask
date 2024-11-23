package com.apiautomation.stepdefinitions;

import com.apiautomation.pom.BaseAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class APIStepDefinitions {

    private BaseAPI baseAPI;
    private Response response;

    @Given("I set up the API request for {string}")
    public void iSetUpTheAPIRequest(String endpoint) {
        // Initialize BaseAPI and set the base URL
        baseAPI = new BaseAPI(endpoint);
    }

    @When("I send the API request with email {string} and password {string}")
    public void iSendTheAPIRequestWithPayload(String email, String password) {
        // Create the JSON payload dynamically
        String payload = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);
        response = baseAPI.sendPostRequest(payload);

        // Print the response for debugging purposes
        System.out.println("Response Body: ");
        response.prettyPrint();  // Prints the response body in a formatted way
    }

    @Then("I verify the response status code is {int}")
    public void iVerifyTheResponseStatusCodeIs(int statusCode) {
        // Validate the status code
        Assert.assertEquals("Status code does not match!", statusCode, response.getStatusCode());
    }

    @Then("I verify the response contains {string}")
    public void iVerifyTheResponseContains(String expectedKey) {
        // Validate if the response contains the specified key
        Assert.assertTrue("Response does not contain the key: " + expectedKey,
                response.jsonPath().getMap("$").containsKey(expectedKey));
    }

    @Then("I print the full response")
    public void iPrintTheFullResponse() {
        // Additional step to print the full response details if needed
        System.out.println("Full Response:");
        System.out.println(response.asString());  // Prints the raw response as a string
    }
}