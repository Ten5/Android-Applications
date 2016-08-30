package com.hp.firstapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	RelativeLayout rl;
	int prev;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prev = 0;
		rl = (RelativeLayout)findViewById(R.id.back);
	}
	
	public void onClick(View v) {
		int random;
		do {
			random = (int)(Math.random()*10) % 7;
		}while(prev == random);
		prev = random;
		switch(random) {
			case 0: rl.setBackgroundColor(Color.RED);
					break;
			case 1: rl.setBackgroundColor(Color.BLUE);
					break;
			case 2: rl.setBackgroundColor(Color.YELLOW);
					break;
			case 3: rl.setBackgroundColor(Color.MAGENTA);
					break;
			case 4: rl.setBackgroundColor(Color.GREEN);
					break;
			case 5: rl.setBackgroundColor(Color.CYAN);
					break;
			case 6: rl.setBackgroundColor(Color.DKGRAY);
					break;
			default: rl.setBackgroundColor(Color.WHITE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
