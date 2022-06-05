import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.bsf.BSFManager;
import java.util.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.LegendItem;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.util.ShapeUtilities;
import org.jfree.data.category.CategoryDataset;
import java.awt.BasicStroke;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Shawn Fu (shawn13aby@gmail.com)
 * created on 2021/09/01
 */
    private final Log log = LogFactory.getLog(BSFManager.class);

    CategoryPlot plot = chart.getCategoryPlot();
    BarRenderer barRenderer = plot.getRenderer(0); // get bar's renderer
    LineAndShapeRenderer lineRenderer = plot.getRenderer(1); // get line's renderer
    CategoryDataset barData = plot.getDataset(0); // get bar's data set
    CategoryDataset lineData = plot.getDataset(1); // get line's data set

    lineRenderer.setSeriesShape(0, ShapeUtilities.createDiamond(0)); // remove line marker
    lineRenderer.setStroke(null);
    lineRenderer.setSeriesStroke(0, new BasicStroke(1.0F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0F, new float[] {6.0F, 6.0F}, 0.0F)); // change line-style to dash

    ValueAxis y1Axis = plot.getRangeAxis(0);
    ValueAxis y2Axis = plot.getRangeAxis(1);

    lineRenderer.setSeriesVisible(2, false); // hidden line
    double line = lineData.getValue(2, 0) == null ? 0 : lineData.getValue(2, 0).doubleValue();
    log.info("line: " + String.valueOf(line));
    double bar1 = barData.getValue(0, 0) == null ? 0 : barData.getValue(0, 0).doubleValue();
    double bar2 = barData.getValue(0, 1) == null ? 0 : barData.getValue(0, 1).doubleValue();
    double bar3 = barData.getValue(0, 2) == null ? 0 : barData.getValue(0, 2).doubleValue();
    double bar4 = barData.getValue(0, 3) == null ? 0 : barData.getValue(0, 3).doubleValue();
    double bar5 = barData.getValue(0, 4) == null ? 0 : barData.getValue(0, 4).doubleValue();
    double bar6 = barData.getValue(0, 5) == null ? 0 : barData.getValue(0, 5).doubleValue();
    double bar7 = barData.getValue(0, 6) == null ? 0 : barData.getValue(0, 6).doubleValue();
    double bar8 = barData.getValue(0, 7) == null ? 0 : barData.getValue(0, 7).doubleValue();
    double bar9 = barData.getValue(0, 8) == null ? 0 : barData.getValue(0, 8).doubleValue();
    double bar10 = barData.getValue(0, 9) == null ? 0 : barData.getValue(0, 9).doubleValue();
    double bar11 = barData.getValue(0, 10) == null ? 0 : barData.getValue(0, 10).doubleValue();
    double bar12 = barData.getValue(0, 11) == null ? 0 : barData.getValue(0, 11).doubleValue();
    double barMax = NumberUtils.max(new double[] {bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9, bar10, bar11, bar12});
    log.info("barMax: " + String.valueOf(barMax));
    if (line == 0) {
        lineRenderer.setSeriesVisible(1, false); // hidden line
        y1Axis.setRange(0D, barMax); // set 0 ~ barMax
        y2Axis.setRange(0D, 1D); // set 0 % ~ 100 %
        y2Axis.setTickUnit(new NumberTickUnit(0.1D));
    } else {
        lineRenderer.setSeriesVisible(3, false); // hidden line
        if (line > barMax) {
            y1Axis.setRange(0D, line); // set 0 ~ line
            // y1Axis.setTickUnit(new NumberTickUnit(line/10)); // set fixed interval
            y2Axis.setRange(0D, 1D); // set 0 % ~ 100 %
            y2Axis.setTickUnit(new NumberTickUnit(0.1D));
        } else {
            y1Axis.setRange(0D, barMax); // set 0 ~ barMax
            // y1Axis.setTickUnit(new NumberTickUnit(0.1 * barMax)); // set fixed interval
            y2Axis.setRange(0D, barMax/line); // set 0 % ~ barMax/line %
        }
    }
    plot.setRangeAxis(0, y1Axis);
    plot.setRangeAxis(1, y2Axis);