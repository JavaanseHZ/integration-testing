Feature: Calculate Contract Risk SOAP

Background:
* url 'http://localhost:8080/services/contracts'
* configure logPrettyRequest = true
* configure logPrettyResponse = true

Scenario: Risk Medium
    Given request read('classpath:contract/request.xml')
    When soap action 'calculateContract'
    Then status 200
    And match /Envelope/Body/calculateContractResponse/contract/risk == 'medium'