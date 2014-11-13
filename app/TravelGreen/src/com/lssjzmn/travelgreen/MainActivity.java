package com.lssjzmn.travelgreen;

import android.R.color;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		TabHost tabHost = this.getTabHost();
		tabHost.setBackgroundColor(color.holo_blue_bright);

		tabHost.addTab(tabHost
				.newTabSpec("1")
				.setIndicator("",
						getResources().getDrawable(R.drawable.home))
				.setContent(new Intent(this, TabActivity1.class)));
		tabHost.addTab(tabHost
				.newTabSpec("2")
				.setIndicator("",
						getResources().getDrawable(R.drawable.share))
				.setContent(new Intent(this, TabActivity2.class)));
		tabHost.addTab(tabHost
				.newTabSpec("3")
				.setIndicator("",
						getResources().getDrawable(R.drawable.now))
				.setContent(new Intent(this, TabActivity3.class)));
		tabHost.addTab(tabHost
				.newTabSpec("4")
				.setIndicator("",
						getResources().getDrawable(R.drawable.tips))
				.setContent(new Intent(this, TabActivity4.class)));

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabName) {
				int tabNumber = Integer.valueOf(tabName);
				switch (tabNumber) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				default:
					break;
				}
			}
		});
	}

}
