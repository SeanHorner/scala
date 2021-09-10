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
  def plotBar(title: String, fname: String, path: String): Unit = {
    var yAxis = Seq[Double]()
    var labels = Seq[String]()

    // Populate x-axis and y-axis
    for (i <- seq.indices) {
      if (i % 2 == 0) {
        labels = labels :+ seq(i).toInt.toString
      }
    }
    for (i <- seq.indices) {
      if (i % 2 != 0) {
        yAxis = yAxis :+ seq(i)
      }
    }

    val customRenderer: BarRenderer = new BarRenderer {
      def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
        val rect = Rect(extent)
        val value = category.values.head
        val color = RGB(0, 102, 255)
        Align.center(rect filled color, Text(s"testTest$value", size = 20)
          .filled(theme.colors.label)
        ).group
      }
    }

    BarChart
      .custom(yAxis.map(Bar.apply), spacing = Some(20), barRenderer = Some(customRenderer))
      .standard(xLabels = labels)
      .hline(0)
      .render()
      .write(new File(s"output/$path/$fname.png"))
  }

  val spark: SparkSession = SparkSession.builder()
    .appName("Meetup Trends Analysis Engine")
    .master("local[4]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")


  val df = spark.read.csv("output/question_07/by_count.csv")

  plotBar(df, "Ranked Event Duration in Minutes", "by_count", "question_07")

}
