
Feature: Post Request fr Products End Point

   @myTest
   Scenario: Create new product
     Given Best Buy API is up and running
     When I set up Headers for the service
      And I setup payload for product post request with unique name
      And I submit postRequest to Service Endpoint
     Then API returns the response with status code as 201
     #here getting the newly created product
      And  new product is created in the system
   # And new product is correctly saved in database

  @myTest
  Scenario: Get product details service
    Given Best Buy API is up and running
    When I set up Headers for the service
    And I submit get request with product "wtcju" returns 200 status code
    Then I verify product details in response