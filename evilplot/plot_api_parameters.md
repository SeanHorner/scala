##Annotations
Annotations are text (or other Drawables) placed on top of the plot area. The X and Y 
coordinate parameters provide the relative position of the annotation (in the range 0 to 1).
####annotate 
Add an annotation

##Axes
The following methods (from the package object) are available for adjusting axes. 
These take arguments to alter the type of axis.
####xAxis 
Add an x-axis
####yAxis
Add a y-axis

##Bounds
By default, the bounds of the plot area are determined by the data and plot constructor. However, it's possible to alter the bounds explicitly:
####xbounds
Set the min/max x bounds
####ybounds
Set the min/max y bounds

##Grids
####xGrid
Add vertical grid lines
####yGrid
Add horizontal grid lines

##Labels
###Labels for a single plot:
####title
Add a title to the top of the plot area
####topLabel
Add a label above the plot area
####bottomLabel
Add a label below the plot area
####leftLabel
Add a label to the left of the plot area
####rightLabel
Add a label to the right of the plot area

###Labels for faceted plots:
####topLabels
Add a label for each facet above the plot area
####bottomLabels
Add a label for each facet below the plot area
####leftLabels
Add a label for each facet to the left of the plot area
####rightLabels
Add a label for each facet to the right of the plot area

##Legends
####topLegend
Add a legend above the plot area
####bottomLegend
Add a legend below the plot area
####leftLegend
Add a legend to the left of the plot area
####rightLegend
Add a legend to the right of the plot area
####overlayLegend
Overlay a legend on the plot area
####renderLegend
Return a drawable to represent the legend


##Lines
####hline
Draw a horizontal line on the plot area
####vline
Draw a vertical line on the plot area
####trend
Draw a trend line on the plot area
####function
Draw a function on the plot area

##Padding
Add padding around a plot area. This is mostly used for lining up plot areas of multiple plots in facets.
####padTop
####padBottom
####padLeft
####padRight