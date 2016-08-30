package com.example.progresstimer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {

	RelativeLayout rl;
	ProgressBar pb;
	AnalogClock clk;
	Button start, stop, reset;
	private int progressStatus;
	private Handler handler= new Handler();
	Thread t;
	private boolean suspend;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rl = (RelativeLayout)findViewById(R.id.back);
		pb = (ProgressBar)findViewById(R.id.progressBar1);
		clk = (AnalogClock)findViewById(R.id.analogClock1);
		start = (Button)findViewById(R.id.button1);
		stop = (Button)findViewById(R.id.button2);
		reset = (Button)findViewById(R.id.button3);		
		rl.setBackgroundColor(Color.BLACK);
		pb.setProgress(0);
		pb.setMax(100);
		
		t = new Thread(new Runnable() {
			public void run() {
				progressStatus = pb.getProgress();
				while(progressStatus <= pb.getMax()) {
					progressStatus += 1;
					//pb.setProgress(progressStatus);
					
					handler.post(new Runnable() {
						public void run() {
							pb.setProgress(progressStatus);
						}
					});
					try {
						Thread.sleep(50);
					} catch(InterruptedException e) {
						handler.post(new Runnable() {
							public void run() {
								Toast.makeText(MainActivity.this, "Timer interrupted", Toast.LENGTH_SHORT).show();
								progressStatus = pb.getProgress();
								synchronized(this) {
									while(suspend) {
										try {
											wait();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											Toast.makeText(MainActivity.this, "Timer interrupted", Toast.LENGTH_SHORT).show();
										}
									}
								}
							}
						});
						//Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
					}
					handler.post(new Runnable() {
						public void run() {
							if(progressStatus == 100)
								Toast.makeText(MainActivity.this, "Process Started!", Toast.LENGTH_SHORT).show();
						}
					});
				}						
			}
		});
		
		start.setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) {
				if(suspend)
					resume();
				Toast.makeText(MainActivity.this, "Loading....", Toast.LENGTH_SHORT).show();
				t.start();
			}
		});
		
		stop.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				//t.interrupt();
				t.suspend();
				suspend = true;
			}
		});
		
		reset.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				progressStatus = 0;
				pb.setProgress(progressStatus);
			}
		});
	}
	
	synchronized void resume() {
		suspend = false;
		notify();
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
