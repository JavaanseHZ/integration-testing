Feature: Calculate Contract Risk SOAP

Background:
* url 'http://localhost:8080/services/contracts'

Scenario: Risk Medium
    Given request read('classpath:soap/request.xml')
    When soap action 'calculateContract'
    Then status 200
