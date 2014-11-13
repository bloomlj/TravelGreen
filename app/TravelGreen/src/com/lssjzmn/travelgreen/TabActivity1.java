package com.lssjzmn.travelgreen;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.LinearLayout;

public class TabActivity1 extends Activity {

	private LinearLayout recordlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabactivity1);
		recordlayout = (LinearLayout) findViewById(R.id.recordLayout);
	}

	@Override
	protected void onResume() {
		String title = " My Record";
		double[] values = new double[] { 10, 22, 12, 23, 21, 15, 16};
		XYMultipleSeriesDataset xyMultipleSeriesDataset = buildBarDataset(
				title, values);
		int color = Color.YELLOW;
		XYMultipleSeriesRenderer xyMultipleSeriesRenderer = buildBarRenderer(color);
		xyMultipleSeriesRenderer.setChartTitle("Daily Pollutant");
		xyMultipleSeriesRenderer.setXTitle("Day");
		xyMultipleSeriesRenderer.setYTitle("Value");
		xyMultipleSeriesRenderer.setXAxisMin(0);
		xyMultipleSeriesRenderer.setXAxisMax(8);
		xyMultipleSeriesRenderer.setYAxisMin(0);
		xyMultipleSeriesRenderer.setYAxisMax(25);
		xyMultipleSeriesRenderer.setAxesColor(Color.GREEN);
		xyMultipleSeriesRenderer.setLabelsColor(Color.YELLOW);
		xyMultipleSeriesRenderer.setXLabels(8);
		xyMultipleSeriesRenderer.setShowGrid(true);
		xyMultipleSeriesRenderer.setZoomButtonsVisible(true);
		xyMultipleSeriesRenderer.setAxisTitleTextSize(25);
		xyMultipleSeriesRenderer.setChartTitleTextSize(30);
		xyMultipleSeriesRenderer.setShowLegend(false);
		Type type = Type.DEFAULT;
		GraphicalView graphicalView = ChartFactory.getBarChartView(this,
				xyMultipleSeriesDataset, xyMultipleSeriesRenderer, type);
		recordlayout.addView(graphicalView);
		super.onResume();
	}

	protected XYMultipleSeriesDataset buildBarDataset(String title,
			double[] values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		CategorySeries series = new CategorySeries(title);
		int seriesLength = values.length;
		for (int k = 0; k < seriesLength; k++) {
			series.add(values[k]);
		}
		dataset.addSeries(series.toXYSeries());

		return dataset;
	}

	protected XYMultipleSeriesRenderer buildBarRenderer(int colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(25);
		renderer.setChartTitleTextSize(30);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(colors);
		renderer.addSeriesRenderer(r);

		return renderer;
	}

}
