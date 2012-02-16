package scalajsonwrapper

import org.json._
import collection.mutable.ListBuffer
import scala.collection.JavaConversions._

object JSON {
  def convertFromJson(obj: Any): Any = obj match {
    case json: JSONObject => {
      var map = Map[String, Any]()
      for (key <- json.sortedKeys.map(_.asInstanceOf[String])) {
        map += (key -> convertFromJson(json.get(key)))
      }
      map
    }
    case jsonArray: JSONArray => {
      var i = 0
      var listBuffer = new ListBuffer[Any]()
      while (i < jsonArray.length) {
        val obj = jsonArray.get(i)
        listBuffer += convertFromJson(obj)
        i += 1
      }
      listBuffer.toList
    }
    case _ => obj
  }

  def convertToJson(obj: Any): Any = obj match {
    case map: Map[String, Any] => {
      val json = new JSONObject()
      for (key <- map.keys) {
        json.put(key, convertToJson(map(key)))
      }
      json
    }
    case iter: Iterable[Any] => {
      val json = new JSONArray()
      for (x <- iter) {
        json.put(convertToJson(x))
      }
      json
    }
    case _ => {
      obj
    }
  }

  def parse(string: String) =
    convertFromJson(new JSONObject(string)).asInstanceOf[Map[String, Any]]

  def toString(map : Map[String, Any], indentFactor : Int = -1) = {
    val json = convertToJson(map).asInstanceOf[JSONObject]
    if (indentFactor == -1)
      json.toString
    else
      json.toString(indentFactor)
  }

}