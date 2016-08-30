package hp.demo.sensoruse;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends ActionBarActivity implements SensorEventListener {

	TextView tv;
	SensorManager sm;
	Sensor s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		tv = (TextView)findViewById(R.id.textView1);
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if(s == null) {
			Toast.makeText(SensorActivity.this, "No Accelerometer detected!", Toast.LENGTH_SHORT).show();
			finish();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
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
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(SensorActivity.this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float li = event.values[1];
		tv.setText("Accelerometer value: " + " " +event.values[0] + " " + event.values[1] + " " + event.values[2]);
		if(li == SensorManager.AXIS_Y)
			Toast.makeText(SensorActivity.this, "Get the hell outta here!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		Toast.makeText(SensorActivity.this, "Light Sensor will are not working properly!", Toast.LENGTH_SHORT).show(); 
	}
}
