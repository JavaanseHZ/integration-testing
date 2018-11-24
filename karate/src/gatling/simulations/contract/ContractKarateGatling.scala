package contract

import com.intuit.karate.gatling.KarateProtocol
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import scala.concurrent.duration._

class ContractKarateGatling extends Simulation {

  val protocol: KarateProtocol = karateProtocol(
    "/contract/contract" -> pauseFor("post" -> 25)
  )

  val riskMedium: ScenarioBuilder = scenario("Risk Medium")
    .exec(karateFeature("classpath:contract/contract-risk-rest.feature"))
    .pause(5)

  setUp(riskMedium.inject(rampUsers(5) during(5 seconds))
  ).protocols(protocol).maxDuration(1 minutes).assertions(global.responseTime.mean.lt(1000))

  after {
    println("Simulation is finished!")
  }

}