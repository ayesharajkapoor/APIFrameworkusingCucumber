Feature: To Validate the Place API
@AddPlaceAPI 
Scenario Outline: To verify Addplace API is working or not

Given Addplace API PAyload with "<name>" "<address>" "<language>"
When  User calls the "AddPlaceAPI" using http "POST" method
Then User gets status code as 200 if success add the place in aPI
And  "status" in  reponse message could be as "OK"
And  "scope" in  reponse message could be as "APP"
And Verify the place id from created maps  to verify "<name>" by using "getPlaceAPI"
Examples:

|name | address | language|

|AAhouse| World cross center | English|

#|BBhouse| Sea cross center | French|

@DeletePlaceAPI
Scenario: To verify the Delete API functionality is working or not
Given Delete Place API Payload
When User calls the "deletePlaceAPI" using http "POST" method
Then "status" in  reponse message could be as "OK"
