Feature: Calculate Contract Risk REST YAML
Background:
* url 'http://localhost:8081/contract/contract'
* configure logPrettyRequest = true
* configure logPrettyResponse = true
* def TOKEN = java.lang.System.getenv('TOKEN')
* print 'the token is: ', TOKEN

Scenario: Risk Medium
    Given request read('classpath:contract/request.yaml')
    When method POST
    Then status 200
    And match response.risk == 'medium'