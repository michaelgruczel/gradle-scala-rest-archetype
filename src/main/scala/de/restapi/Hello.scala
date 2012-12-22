package main.scala.de.restapi

import scala.util.parsing.json.JSONArray
import javax.ws.rs._
import com.yammer.metrics._
import com.yammer.metrics.scala._
import com.yammer.metrics.scala.Instrumented
import com.wordnik.swagger.annotations._
import com.wordnik.swagger.jaxrs.JavaApiListing
import javax.ws.rs.core.Response


//@Path("hello.xml")
@Path("hello.json")
@Api(value="/hello", description = "hello world desc")
class Hello extends Instrumented {

  val counter = metrics.counter("helloCalls")


  @Path("/info")
  @ApiOperation(value = "return hello world", notes = "no extra notes", responseClass = "main.scala.de.restapi.HelloResponse")
  @GET
  //@Produces(Array("text/xml"))
  @Produces(Array("application/json"))
  def doGet: HelloResponse = {
    counter += 1;

    val resp = new HelloResponse()
    resp.setContent("Hello World ! check also rest/metrics, rest/api-docs.json, rest/api-docs.xml, rest/api-docs.xml/hello")
    resp
    //Response.ok.entity(resp).build

  }


}
