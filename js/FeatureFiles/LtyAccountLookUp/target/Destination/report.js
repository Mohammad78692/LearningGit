$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/LtyLookUp/LoyaltyLookUpScenarios.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#language: en"
    }
  ],
  "line": 2,
  "name": "FeatureNotes",
  "description": "",
  "id": "featurenotes",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "Passes scenario which has time wait..",
  "description": "",
  "id": "featurenotes;passes-scenario-which-has-time-wait..",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I wait for 4000 millis",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I wait for 4000 millis",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "4000",
      "offset": 11
    }
  ],
  "location": "LtyLookUpSteps.i_wait_some_time(int)"
});
formatter.result({
  "duration": 4291203108,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4000",
      "offset": 11
    }
  ],
  "location": "LtyLookUpSteps.i_wait_some_time(int)"
});
formatter.result({
  "duration": 3997130439,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "Failed scenario which LookUps..",
  "description": "",
  "id": "featurenotes;failed-scenario-which-lookups..",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "I LookUp Offer",
  "rows": [
    {
      "cells": [
        "requestMethod",
        "requestFile",
        "responseFile"
      ],
      "line": 14
    },
    {
      "cells": [
        "POST",
        "Request_LTYLookUP_Scenario1.xml",
        "Response_LTYLookUP_Scenario1.xml"
      ],
      "line": 15
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "LtyLookUpSteps.i_LookUp_Offer(DataTable)"
});
formatter.result({
  "duration": 73891830,
  "error_message": "java.lang.NullPointerException\r\n\tat java.lang.String.replace(String.java:2240)\r\n\tat com.macys.marketing.loyalty.LtyIntegrationTest.Client.impl.RestAssuredClientImpl.updateDynamicRequestBody(RestAssuredClientImpl.java:304)\r\n\tat com.macys.marketing.loyalty.LtyIntegrationTest.Client.impl.RestAssuredClientImpl.getRequestSpecificationBuildData(RestAssuredClientImpl.java:240)\r\n\tat com.macys.marketing.loyalty.LtyIntegrationTest.Client.impl.RestAssuredClientImpl.invokeService(RestAssuredClientImpl.java:93)\r\n\tat com.macys.marketing.loyalty.LtyIntegrationTest.wrapper.LtyLookUpWrapper.lookupOfferInfo(LtyLookUpWrapper.java:79)\r\n\tat com.macys.marketing.loyalty.LtyIntegrationTest.steps.ltyLookUp.LtyLookUpSteps.i_LookUp_Offer(LtyLookUpSteps.java:71)\r\n\tat ✽.Then I LookUp Offer(features/LtyLookUp/LoyaltyLookUpScenarios.feature:13)\r\n",
  "status": "failed"
});
});