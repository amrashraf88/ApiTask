Feature: API Testing with Foodics Endpoints

    Scenario: Verify login API response
        Given I set up the API request for "https://pay2.foodics.dev/cp_internal/login"
        When I send the API request with email "merchant@foodics.com" and password "123456"
        Then I verify the response status code is 200
        And I verify the response contains "token"