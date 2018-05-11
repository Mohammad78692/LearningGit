package com.macys.marketing.loyalty.LtyIntegrationTest.steps.ltyLookUp;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.macys.loyalty.schemas.loyalty_account.ltyaccountresponse.v12_0.LtyLookupOfferInfoResp;
import com.macys.marketing.loyalty.LtyIntegrationTest.response.validators.LtyLookUpInfoValidator;
import com.macys.marketing.loyalty.LtyIntegrationTest.rest.model.offertool.OfferResponse;
import com.macys.marketing.loyalty.LtyIntegrationTest.util.CommonUtility;
import com.macys.marketing.loyalty.LtyIntegrationTest.wrapper.LtyLookUpWrapper;
import com.macys.marketing.loyalty.LtyIntegrationTest.wrapper.OfferServiceWrapper;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class LtyLookUpSteps {
	private static final Logger LOGGER = LoggerFactory.getLogger(LtyLookUpSteps.class);
	Map<String, Object> response;
	OfferServiceWrapper offerService = new OfferServiceWrapper();
	LtyLookUpWrapper lookUp = new LtyLookUpWrapper();
	LtyLookUpInfoValidator validator = new LtyLookUpInfoValidator();
	String offerCode ;
	
	@Given("^I Create Offer for LookUp$")
	public void createOffer(DataTable arg1) {
		//DataTable table = null;
		LOGGER.info("Start of method :: createOffer");
		//Map<String, String> dynamicReq = new HashMap<String, String>();
		
		
		
		
		Map<String, Object> response =offerService.hitExternalService();
		
		Response actualResponse = (Response) response.get("ACTUAL_RESPONSE");
		System.out.println("the output is "+ actualResponse);
		/*OfferResponse offerResponse = actualResponse.as(OfferResponse.class);
		offerCode = offerResponse.getRewardOffer().getOfferHeader().getOfferCode();*/

		LOGGER.info("End of method :: createOffer");
	}
	
	@Then("^I wait for (\\d+).*$")
	public void i_wait_some_time(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			LOGGER.error("LtyTransactionSteps.i_wait_some_time():::", e);
				throw new RuntimeException();
		}
	}
	
	@And("^I update offer status for LookUp$")
	public void approveOffer(DataTable table) {
		LOGGER.info("Start of method :: approveOffer");
		
		offerService.updateOfferStatus(table, offerCode);

		LOGGER.info("End of method :: approveOffer");
	}
	
	@Then("^I LookUp Offer$")
	public void i_LookUp_Offer(DataTable table) {
		LOGGER.info("Start of Offer LookUp");
		response=lookUp.lookupOfferInfo(table, offerCode);
		String expected = (String) response.get("EXPECTED_RESPONSE");
		LOGGER.info("Replacing Offer code in the lookup requestfile" );
		expected=expected.replace("OfferCodeValue", offerCode);
		response.put("EXPECTED_RESPONSE", expected);
		LOGGER.info("End of offer Lookup");
	}
	
	//For validating looked up offer from BE database
	@And("^I verify LookUp Offer$")
	public void ValidatelookupOffer() {
		
		LOGGER.info("Start of Validate lookup offer");
		Response actual = (Response) response.get("ACTUAL_RESPONSE");
		LtyLookupOfferInfoResp actualReponse=(LtyLookupOfferInfoResp)CommonUtility.getObjectFromXmlString(actual.asString(), LtyLookupOfferInfoResp.class);
		String expected = (String) response.get("EXPECTED_RESPONSE");
		LtyLookupOfferInfoResp expectedResponse=(LtyLookupOfferInfoResp)CommonUtility.getObjectFromXmlString(expected, LtyLookupOfferInfoResp.class);
		LOGGER.info("calling validateLtyLookUpInfo");
		validator.validateLtyLookUpInfo(actualReponse, expectedResponse);
	}
	
	//@Given("^I Create Offer for LookUp$")
	public void hitTheService(DataTable table) {
		LOGGER.info("Start of method :: createOffer");
		Map<String, String> dynamicReq = new HashMap<String, String>();
		
		Map<String, Object> response =offerService.createOffer(table, dynamicReq);
		
		Response actualResponse = (Response) response.get("ACTUAL_RESPONSE");
		OfferResponse offerResponse = actualResponse.as(OfferResponse.class);
		offerCode = offerResponse.getRewardOffer().getOfferHeader().getOfferCode();

		LOGGER.info("End of method :: createOffer");
	}
}
