Scala wrapper around java [org.json](http://json.org/java/) lib. Parses json to maps and lists.

## Usage

    scala> import scalajsonwrapper._
    import scalajsonwrapper._

    scala> JSON.parse("""{"a" : "b", "c" : [1, 2, 3]}""")
    res0: Map[String,Any] = Map(a -> b, c -> List(1, 2, 3))

    scala> JSON.toString(Map("a" -> "b", "c" -> List(1, 2, 3)))
    res1: java.lang.String = {"c":[1,2,3],"a":"b"}