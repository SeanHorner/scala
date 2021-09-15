import com.cibo.evilplot.plot._

object ClusteredBarTester {
  val data: Seq[Seq[Double]] = Seq[Seq[Double]](
    Seq(1, 2, 3),
    Seq(4, 5, 6),
    Seq(3, 4, 1),
    Seq(2, 3, 4)
  )
  BarChart
    .clustered(
      data,
      labels = Seq("one", "two", "three") )
    .title("Clustered Bar Chart Demo")
    .xAxis(Seq("a", "b", "c", "d"))
    .yAxis()
    .frame()
    .bottomLegend()
    .render()
}
