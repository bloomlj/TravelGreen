package com.lssjzmn.bluetoothtest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class MainActivity extends Activity {

	private InputStream inputStream = null;
	// private OutputStream outputStream = null;
	public static Boolean isConnected = false;// 是否已连接设备的标志位

	private LinearLayout chartlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);
		chartlayout = (LinearLayout) findViewById(R.id.chartLayout);

	}

	@SuppressWarnings("unused")
	public void getData() {
		if (!isConnected)
			return;
		byte[] dataBuffer = new byte[128];
		int length;
		try {
			// inputStream = bluetoothSocket.getInputStream();
			while (true) {
				length = inputStream.read(dataBuffer);
				String data = new String(dataBuffer, "UTF-8");
			}
		} catch (IOException e) {
		}

	}

	@Override
	protected void onResume() {

		String chartTitle = "My Chart";// 图表标题
		String xtitle = "CO2";// x轴名
		String ytitle = "value";// y轴名
		int[] X = new int[] { 1, 2, 3, 4, 5, 6, 7 };// 横坐标值
		double[] Y = new double[] { 5, 5.3, 8, 12, 17, 22, 24.2 };// 纵坐标值
		Arrays.sort(Y);// 纵坐标排序（升）
		double maxY = Y[Y.length - 1] + 3;// 排序后纵坐标值最大值+5
		int color = Color.BLUE;
		PointStyle pointStyle = PointStyle.CIRCLE;

		XYSeries xySeries = new XYSeries(chartTitle, 0);// 坐标数据集
		XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
		addXYSeries(xyMultipleSeriesDataset, xySeries, X, Y);

		XYMultipleSeriesRenderer renderer = buildRenderer(color, pointStyle,
				true);
		setChartSettings(renderer, chartTitle, xtitle, ytitle, 0.5, 8, 0, maxY,
				Color.YELLOW, Color.BLUE);// 坐标渲染器的参数设定

		View view = ChartFactory.getLineChartView(getApplicationContext(),
				xyMultipleSeriesDataset, renderer);// 获取图表视图
		view.setBackgroundColor(Color.TRANSPARENT);
		chartlayout.addView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		super.onResume();
	}

	private XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle pointStyle, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(color);
		r.setPointStyle(pointStyle);
		r.setFillPoints(fill);
		r.setLineWidth(3);
		renderer.addSeriesRenderer(r);

		return renderer;
	}

	private void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
		renderer.setXLabels(8);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setShowGrid(true);
		renderer.setZoomButtonsVisible(true);
		renderer.setAxisTitleTextSize(15);
		renderer.setChartTitleTextSize(20);
		renderer.setShowLegend(false);
	}

	private void addXYSeries(XYMultipleSeriesDataset dataset,
			XYSeries xySeries, int[] x, double[] y) {
		int dataLength = x.length;
		for (int i = 0; i < dataLength; i++) {
			xySeries.add(x[i], y[i]);
		}
		dataset.addSeries(xySeries);
	}

	@Override
	protected void onPause() {
		try {
			if (inputStream != null)
				inputStream.close();
		} catch (IOException e) {
		}
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

}
