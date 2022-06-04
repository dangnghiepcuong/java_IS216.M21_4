package Process;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart
{
    private DefaultValue dv = new DefaultValue();
    private String ChartTitle;
    private String CategoryAxisLabel;
    private String ValueAxisLabel;
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public BarChart() {
    }

    public BarChart(String chartTitle, String categoryAxisLabel, String valueAxisLabel) {
        ChartTitle = chartTitle;
        CategoryAxisLabel = categoryAxisLabel;
        ValueAxisLabel = valueAxisLabel;
    }

    public String getChartTitle() {
        return ChartTitle;
    }

    public void setChartTitle(String chartTitle) {
        ChartTitle = chartTitle;
    }

    public String getCategoryAxisLabel() {
        return CategoryAxisLabel;
    }

    public void setCategoryAxisLabel(String categoryAxisLabel) {
        CategoryAxisLabel = categoryAxisLabel;
    }

    public String getValueAxisLabel() {
        return ValueAxisLabel;
    }

    public void setValueAxisLabel(String valueAxisLabel) {
        ValueAxisLabel = valueAxisLabel;
    }

    private JFreeChart createChart()
    {
        JFreeChart barChart = ChartFactory.createBarChart(
                ChartTitle, CategoryAxisLabel, ValueAxisLabel,
                dataset, PlotOrientation.VERTICAL, false, false, false);
        barChart.setBorderVisible(false);

        return barChart;
    }

    public void addDataSetValue(Double value, Comparable rowKey, Comparable columnKey)
    {
        dataset.addValue(value, rowKey, columnKey);
    }

    public void addDataSetValue(int value, Comparable rowKey, Comparable columnKey)
    {
        dataset.addValue(value, rowKey, columnKey);
    }

    public JPanel getChartPanel(int width, int height)
    {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new Dimension(width, height));
//        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    }

    public  JPanel getChartPanel2(int width, int height)
    {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setBounds(0,0,width,height);
//        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    }
}
