package com.lssjzmn.travelgreen;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class TabActivity3 extends Activity {

	public static Boolean isConnected = false;// �Ƿ��������豸�ı�־λ

	private String URLPATH = "http://192.168.1.56/sensor_data/api/12";
	private String JsonString = "";
	private int tmpNum = 10;
	private int CurrentX = 13;
	private int num = 0;
	private int[] x = new int[] { 0, 4, 2, 7, 8, 9, 2, 3, 1, 0 };
	private LinearLayout chartlayout;
	private Timer mTimer;
	private TimerTask mTimerTask;
	private GraphicalView graphicalView;
	private XYSeries xySeries;
	private final static String TAG = "travelgreen";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.tabactivity3);
		chartlayout = (LinearLayout) findViewById(R.id.chartLayout);

	}

	private void getJsonStrFromServer(final String URL) {
		// HttpGet����
		new Thread(new Runnable() {
			public void run() {
				HttpGet httpRequest = new HttpGet(URL);
				try {
					// HttpClient����
					HttpClient httpClient = new DefaultHttpClient();
					// ���HttpResponse����
					HttpResponse httpResponse = httpClient.execute(httpRequest);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						// ȡ�÷��ص�����
						JsonString = EntityUtils.toString(httpResponse
								.getEntity());
					}
					System.out.println("getJson " + JsonString);

					try {
						JSONObject jsonObject = new JSONObject(JsonString)
								.getJSONObject("data");
						String tmp = jsonObject.getString("tmp");
						tmpNum = Integer.valueOf(tmp);
						tmpNum = 10;
						handler.sendEmptyMessage(2);
					} catch (JSONException e) {
					}
				} catch (ClientProtocolException e) {
				} catch (IOException e) {
				}

			}
		}).start();

	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1)
			// getJsonStrFromServer(URLPATH);
			{
				if (num == 10)
					return;
				xySeries.add(CurrentX, tmpNum + x[num]);// ˢ��ͼ������
				graphicalView.repaint();
				CurrentX += 1;
				num += 1;
			}
			super.handleMessage(msg);
		}

	};

	private class RefreshDataFromServer extends TimerTask {
		public void run() {
			handler.sendEmptyMessage(1);
		}

	}

	public void getOBD(View v) {
		mTimer = new Timer();
		mTimerTask = new RefreshDataFromServer();
		mTimer.schedule(mTimerTask, 500, 3000);
	}

	@Override
	protected void onResume() {

		String chartTitle = "My Chart";// ͼ�����
		String xtitle = "Hours";// x����
		String ytitle = "CO2 value";// y����
		int[] X = new int[] { 6, 7, 8, 9, 10, 11, 12 };// ������ֵ
		double[] Y = new double[] { 10, 20, 12, 12, 6, 9, 10 };// ������ֵ
		Arrays.sort(Y);// ��������������
		double maxY = Y[Y.length - 1] + 3;// �����������ֵ���ֵ+5
		int color = Color.BLUE;
		PointStyle pointStyle = PointStyle.CIRCLE;

		xySeries = new XYSeries(chartTitle, 0);// �������ݼ�
		XYMultipleSeriesDataset xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
		addXYSeries(xyMultipleSeriesDataset, xySeries, X, Y);

		XYMultipleSeriesRenderer renderer = buildRenderer(color, pointStyle,
				true);
		setChartSettings(renderer, chartTitle, xtitle, ytitle, 5.5, 25, 0,
				maxY, Color.YELLOW, Color.YELLOW);// ������Ⱦ���Ĳ����趨

		graphicalView = ChartFactory.getLineChartView(getApplicationContext(),
				xyMultipleSeriesDataset, renderer);// ��ȡͼ����ͼ
		graphicalView.setBackgroundColor(Color.BLACK);
		// graphicalView .repaint();
		chartlayout.addView(graphicalView);
		super.onResume();
	}

	private XYMultipleSeriesRenderer buildRenderer(int color,
			PointStyle pointStyle, boolean fill) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// ����ͼ�������߱������ʽ��������ɫ����Ĵ�С�Լ��ߵĴ�ϸ��
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
		renderer.setXLabels(18);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setShowGrid(true);
		renderer.setZoomButtonsVisible(true);
		renderer.setAxisTitleTextSize(25);
		renderer.setChartTitleTextSize(30);
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
		// mTimer.cancel();
		super.onPause();
	}

}
