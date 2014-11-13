package com.lssjzmn.travelgreen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Init extends Activity {
	Handler handler = new Handler();

	public Init() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.init);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		handler.postDelayed(runnable, 3000); // ÿ��1sִ��

	}

	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// handler�Դ�����ʵ�ֶ�ʱ��
			try {
				finish();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("exception...");
			}
		}
	};

}
