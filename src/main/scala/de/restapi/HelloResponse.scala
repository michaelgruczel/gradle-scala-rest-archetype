package main.scala.de.restapi

import com.wordnik.swagger.annotations._

import javax.xml.bind.annotation._

@XmlRootElement(name = "HelloResponse")
class HelloResponse {

  private var content:String = _

  @XmlElement(name="content")
  def getContent():String = {
    content
  }

  def setContent(content:String):Unit = {
    this.content = content
  }

}
