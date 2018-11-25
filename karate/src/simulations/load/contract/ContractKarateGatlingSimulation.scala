package contract

import com.intuit.karate.gatling.KarateProtocol
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ScenarioBuilder
import scala.concurrent.duration._

class ContractKarateGatlingSimulation extends Simulation {

  val rampUpTimeSecs = 5
  val testTimeSecs = 20
  val noOfUsers = 10

  val protocol = karateProtocol(
    "/contract/contract" -> pauseFor("post" -> 25)
  )

  val riskMedium = scenario("Risk Medium")
    .during(testTimeSecs) {
      exec(karateFeature("classpath:contract/contract-risk-rest.feature")).pause(5 seconds)
    }

  setUp(
    riskMedium.inject(rampUsers(noOfUsers) over (rampUpTimeSecs)).protocols(protocol)
  )


//  val rampUpTimeSecs = 5
//  val testTimeSecs = 20
//  val noOfUsers = 10
//  val minWaitMs = 1000 milliseconds
//  val maxWaitMs = 3000 milliseconds
//
//  val baseURL = "http://localhost:8080"
//  val baseName = "webservice-call-greeting"
//  val requestName = baseName + "-request"
//  val scenarioName = baseName + "-scenario"
//  val URI = "/greeting"
//
//  val httpConf = http
//    .baseURL(baseURL)
//
//  val scn = scenario(scenarioName)
//    .during(testTimeSecs) {
//      exec(http(requestName).get(URI).check(status.is(200))      ).pause(minWaitMs, maxWaitMs)
//    }
//
//  setUp(
//    scn.inject(rampUsers(noOfUsers) over (rampUpTimeSecs))
//  ).protocols(httpConf)

}