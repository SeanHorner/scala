import com.cibo.evilplot.colors.HSLA
import com.cibo.evilplot.colors.RGB
import com.cibo.evilplot.geometry.{Align, Drawable, Extent, Rect, Text}
import com.cibo.evilplot.plot._
import com.cibo.evilplot.plot.aesthetics.DefaultTheme.{DefaultFonts, DefaultTheme}
import com.cibo.evilplot.plot.aesthetics.Theme
import com.cibo.evilplot.plot.renderers.BarRenderer

import java.io.File

object BarChartTester extends App {



  implicit val theme: Theme = DefaultTheme.copy(
    fonts = DefaultFonts
      .copy(tickLabelSize = 14, legendLabelSize = 14, fontFace = "'Lato', sans-serif")
  )

  val percentChange = Seq[Double](-10, 5, 12, 68, -22)
  val labels = Seq("one", "two", "three", "four", "five")

  val labeledByColor: BarRenderer = new BarRenderer {
    val positive: HSLA = RGB(241, 121, 6)
    val negative: HSLA = RGB(226, 56, 140)
    def render(plot: Plot, extent: Extent, category: Bar): Drawable = {
      val rect = Rect(extent)
      val value = category.values.head
      val color = if (value >= 0) positive else negative
      Align.center(rect filled color, Text(s"$value%", size = 20)
        .filled(theme.colors.label)
      ).group
    }
  }

  BarChart
    .custom(percentChange.map(Bar.apply), spacing = Some(20),
      barRenderer = Some(labeledByColor)
    )
    .standard(xLabels = labels)
    .hline(0)
    .render()
    .write(new File("output/BarChartTest/bar1.png"))
}
