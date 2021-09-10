import java.io.File
import com.cibo.evilplot.numeric.Point
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.renderers.PointRenderer
import com.cibo.evilplot.plot.aesthetics.DefaultTheme._
import scala.util.Random

object ScatterPlotTester extends App {


  new File("output/ScatterPlotTest/").mkdirs()

  val qualities = Seq("good", "bad")

  final case class MyFancyData(x: Double, y: Double, quality: String)
  val data: Seq[MyFancyData] = Seq.fill(100) {
    MyFancyData(Random.nextDouble(), Random.nextDouble(), qualities(Random.nextInt(2)))
  }
  val points: Seq[Point] = data.map(d => Point(d.x, d.y))

  ScatterPlot(
    points
  ).xAxis()
    .yAxis()
    .frame()
    .rightLegend()
    .render()
    .write(new File(s"output/ScatterPlotTest/scatter1.png"))



}
