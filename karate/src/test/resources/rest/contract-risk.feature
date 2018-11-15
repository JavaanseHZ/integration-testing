Feature: Calculate Contract Risk REST
Background:
* url 'http://localhost:8081/contract/contract'

Scenario: Risk Medium
    Given request read('classpath:rest/request.json')
    When method POST
    Then status 200