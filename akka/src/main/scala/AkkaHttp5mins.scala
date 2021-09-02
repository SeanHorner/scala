import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethod, HttpMethods, HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import java.net.URLEncoder
import scala.concurrent.Future
import scala.concurrent.duration._

object AkkaHttp5mins {

  implicit val system = ActorSystem()  //  Akka actors
  implicit val materializer = ActorMaterializer()  //  Akka Streams
  import system.dispatcher  //  "thread pool"

  val source =
    """
      |object SimpleApp {
      |  val aField = 2
      |
      |  def aMethod(x: Int) = x + 1
      |
      |  def main(args: Array[String]): Unit = println(aField)
      |}
    """.stripMargin

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "http://markup.su/api/highlighter",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`,  //  application/json is the more common option.
      s"source=${URLEncoder.encode(source, "UTF-8")}&language=Scala&theme=Sunburst"
    )
  )

  def sendRequest(): Future[String] = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    val entityFuture: Future[HttpEntity.Strict] = responseFuture.flatMap(response => response.entity.toStrict(2.seconds))
    entityFuture.map(entity => entity.data.utf8String)
  }

  def main(args: Array[String]): Unit = {
    sendRequest().foreach(println)
  }
}
