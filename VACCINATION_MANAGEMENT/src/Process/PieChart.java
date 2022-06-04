package Process;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class PieChart
{
    private DefaultValue dv = new DefaultValue();
    private String ChartTitle;
    private DefaultPieDataset dataset = new DefaultPieDataset();

    public String getChartTitle() {
        return ChartTitle;
    }

    public void setChartTitle(String chartTitle) {
        ChartTitle = chartTitle;
    }

    public PieChart() {
    }

    public PieChart(String chartTitle) {
        ChartTitle = chartTitle;
    }

    public JFreeChart createChart() {
        JFreeChart pieChart = ChartFactory.createPieChart(ChartTitle,
                dataset, true, true, true);
        return pieChart;
    }

    public void addDataSetValue(Comparable key, Double value)
    {
        dataset.setValue(key, value);
    }

    public void addDataSetValue(Comparable key, int value)
    {
        dataset.setValue(key, value);
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
