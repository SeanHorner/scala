import com.cibo.evilplot.colors.RGB
import com.cibo.evilplot.geometry.{Align, Drawable, Extent, Rect, Text}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme.{DefaultFonts, DefaultTheme}
import com.cibo.evilplot.plot.aesthetics.Theme
import com.cibo.evilplot.plot.renderers.BarRenderer

import java.io.File

object DataPlottingTester extends App {
  implicit val theme: Theme = DefaultTheme.copy(
    fonts = DefaultFonts
      .copy(tickLabelSize = 14, legendLabelSize = 14, fontFace = "'Lato', sans-serif")
  )
  def plotBar(title: String, fname: String, path: String, in_path: String): Unit = {

    var yAxis = Seq[Double]()
    var labels = Seq[String]()

    val bufferedSource = Source.fromFile(in_path)
    val points = bufferedSource.getLines.drop(1).map { line =>
      val columns = line.split(",").map{_.trim()}
      labels += seq(s"${columns.head.toInt}.${columns(1).toInt}")
      yAxis += seq(columns(2).toDouble)

    val customRenderer: BarRenderer = new BarRenderer {
      def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
        val rect = Rect(extent)
        val value = category.values.head
        val color = RGB(0, 102, 255)
        Align.center(rect filled color, Text(s"$value", size = 20)
          .filled(theme.colors.label)
        ).group
      }
    }

    BarChart
      .custom(yAxis.map(Bar.apply), spacing = Some(20), barRenderer = Some(customRenderer))
      .standard(xLabels = labels)
      .title(title)
      .hline(0)
      .render()
      .write(new File(s"output/$path/$fname.png"))
  }

  plotBar("input_data/question_01/results.csv", "Event Count Over Time", "results", "question_01")

}
