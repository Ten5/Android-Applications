package hp.demo.activitycomm;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class NewActivity extends ActionBarActivity {

	TextView tv1;
	RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		Toast.makeText(this, "NewActivity onCreate", Toast.LENGTH_SHORT).show();
		tv1 = (TextView)findViewById(R.id.move);
		rl = (RelativeLayout)findViewById(R.id.back);
		rl.setBackgroundColor(Color.parseColor("#fcbcbf"));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(NewActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home)
			finish();
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "NewActivity onStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "NewActivity onResume", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "NewActivity onPause", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "NewActivity onStop", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "NewActivity onDestroy", Toast.LENGTH_SHORT).show();
	}
}
