package hp.demo.activitycomm;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {
	
	TextView tv;
	Button bt, call, sms;
	EditText ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(this, "MainActivity onCreate", Toast.LENGTH_SHORT).show();
		tv = (TextView)findViewById(R.id.move);
		bt = (Button)findViewById(R.id.button1);
		ed = (EditText)findViewById(R.id.editText1);
		call = (Button)findViewById(R.id.button2);
		sms = (Button)findViewById(R.id.button3);
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("ListActivity");
				startActivity(i);
			}
		});
		
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, NewActivity.class);
				startActivity(i);
			}
		});
		 
		call.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phnum = ed.getText().toString().trim();
				Uri num = Uri.parse("tel:"+phnum);
				Intent call = new Intent(Intent.ACTION_CALL, num);
				startActivity(call);
			}
		});
		
		sms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phNum = ed.getText().toString().trim();
				Uri sms = Uri.parse("sms:"+phNum);
				Intent i = new Intent(Intent.ACTION_SENDTO, sms);
				i.putExtra("sms_body", "-_-");
				startActivity(i);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "MainActivity onStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "MainActivity onResume", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "MainActivity onPause", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "MainActivity onStop", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onStart();
		Toast.makeText(this, "MainActivity onDestroy", Toast.LENGTH_SHORT).show();
	}
}
