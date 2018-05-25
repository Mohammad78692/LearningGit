#language: en
Feature: Enrolling scenarios for Non Macys card 

@Important
Scenario: Enroll Customer with Non Macys Card for POS 
	Given I Enroll Customer with Non Macys card for Star Reward program for POS 
		| requestMethod |AddedByApp | requestFile              |
		| POST          |SPOS       | Request_NonMacysCusenrollOnline.xml |
		
	And I CusLookUp by Account Number 
		| requestMethod | requestFile                    | responseFile               |
		| POST          | Request_cusLookup_Scenario.xml | Response_cusLookup_IRN.xml |
		
		
@NotImportant 
Scenario: Enroll Customer with Non Macys Card for Online 
	Given I Enroll Customer with Non Macys card for Star Reward program for MCOM 
		| requestMethod |AddedByApp | requestFile              |
		| POST          |MCOM       | Request_NonMacysCusenrollOnline.xml |
		
	And I CusLookUp by Account Number 
		| requestMethod | requestFile                    | responseFile               |
		| POST          | Request_cusLookup_Scenario.xml | Response_cusLookup_IRN.xml |
		
@LowPriority 
Scenario: Enroll Customer with Non Macys Card for CustomerService 
	Given I Enroll Customer with Non Macys card for Star Reward program for CustomerService 
		| requestMethod |AddedByApp | requestFile              |
		| POST          |ICMCS      | Request_NonMacysCusenrollOnline.xml |
		
	And I CusLookUp by Account Number 
		| requestMethod | requestFile                    | responseFile               |
		| POST          | Request_cusLookup_Scenario.xml | Response_cusLookup_IRN.xml |
		
		
@HighPriority
Scenario: Trying to enroll customer with already enrolled phone number 
	Given I Enroll Customer with Non Macys card for Star Reward program for POS 
		| requestMethod |AddedByApp | requestFile              |
		| POST          |SPOS       | Request_NonMacysCusenrollOnline.xml |
	And I try to enroll Customer with enrolled phone number 
		| requestMethod | Negative             |  requestFile             |
		| POST          | PHONE ALREADY EXISTS | Request_IRNCusenroll.xml |
		
		

Scenario: Enroll Customer with Non Macys Card for Online with Mailing Address is optional 
	Given I Enroll Customer with Non Macys card for Star Reward program for POS 
		| requestMethod |AddedByApp |Email | requestFile              |
		| POST          |SPOS       |Empty | Request_NonMacysCusenrollOnline.xml |
	And I try to enroll Customer without email 
		| requestMethod |  requestFile              |
		| POST          | Request_IRNCusenroll.xml |
		
@HighPriority
Scenario: Enroll Customer with Non Macys Card for Online with Mailing Address is optional 
	Given I Enroll Customer with Non Macys card for Star Reward program for POS 
		| requestMethod |AddedByApp |Email | requestFile              |
		| POST          |SPOS       |Empty | Request_NonMacysCusenrollOnline.xml |
	And I try to enroll Customer without email 
		| requestMethod |  requestFile              |
		| POST          | Request_IRNCusenroll.xml |
		

@HighPriority
Scenario: Enroll Customer with Non Macys Card for Online with Mailing Address is optional 
	Given I Enroll Customer with Non Macys card for Star Reward program for MCOM 
		| requestMethod |AddedByApp | requestFile              |
		| POST          |MCOM       | Request_NonMacysCusenrollOnline.xml |
		
	And I CusLookUp by Account Number 
		| requestMethod | requestFile                    | responseFile               |
		| POST          | Request_cusLookup_Scenario.xml | Response_cusLookup_IRN.xml |
		
