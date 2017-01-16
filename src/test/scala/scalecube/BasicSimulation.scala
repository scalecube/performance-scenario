package scalecube

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
    .exec(http("localhost").get("http://localhost:8080/do"))
    
  
   setUp( 
    scn.inject(
	    nothingFor(1 seconds), // 1
	    atOnceUsers(1000), // 2
	    rampUsers(2000) over(20 seconds), // 3
	    constantUsersPerSec(2000) during(20 seconds), // 4
	    rampUsers(4000) over(20 seconds), // 3
	    constantUsersPerSec(5000) during(120 seconds) randomized // 5
    )).protocols(httpConf)
  
   
}