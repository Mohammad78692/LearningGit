#language: en
Feature: FeatureNotes


Scenario: Passes scenario which has time wait..
Then I wait for 4000 millis
	
		
 Then I wait for 4000 millis

 
Scenario: Failed scenario which LookUps..
	Then I LookUp Offer
		|requestMethod	|requestFile			   			|responseFile|
		|POST			|Request_LTYLookUP_Scenario1.xml	|Response_LTYLookUP_Scenario1.xml|
	
  #And I verify LookUp Offer
