package hp.demo.servicesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceActivity extends ActionBarActivity implements OnClickListener {

	Button start, stop;
	Intent ob;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		start = (Button)findViewById(R.id.start);
		stop = (Button)findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		ob = new Intent(this, MusicService.class);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
			case R.id.start:
					startService(ob);
					break;
			case R.id.stop:
					stopService(ob);
					break;
		}
	}
}
