import com.cibo.evilplot.numeric.Point
import com.cibo.evilplot.colors._
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.renderers._

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

import java.io.File

object OverlayingPlotTester extends App {
  new File("output/OverlayingPlotTest/").mkdirs()

  val path: String = "input_data/"
  val data: Seq[Point] = {
    val bufferedSource = Source.fromFile(path + "Wicked_Free_Wifi_Locations.csv")
    val points = bufferedSource.getLines.drop(1).map { line =>
      val columns = line.split(",").map{_.trim()}
      //The coordinates are found in the first and second columns
      Point(columns.head.toDouble, columns(1).toDouble)
    }.toSeq
    points
  }

  def contourPlot(seq: Seq[Point]): Plot = {
    ContourPlot(
      seq,
      surfaceRenderer = Some(SurfaceRenderer.contours(
        color = Some(HTMLNamedColors.dodgerBlue))
      ))
  }

  contourPlot(data)
    .xLabel("Lon")
    .yLabel("Lat")
    .xbounds(-71.2, -71)
    .ybounds(42.20, 42.4)
    .xAxis()
    .yAxis()
    .frame()
    .render()
    .write(new File("output/OverlayingPlotTest/overlay_plot.png"))
}
