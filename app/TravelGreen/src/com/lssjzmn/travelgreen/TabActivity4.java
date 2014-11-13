package com.lssjzmn.travelgreen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TabActivity4 extends Activity {
	Button tip1btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tabactivity4);

		tip1btn = (Button) findViewById(R.id.tip1);
		tip1btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TabActivity4.this,
						Tip1Activity.class);
				startActivity(intent);
			}

		});
	}

}
