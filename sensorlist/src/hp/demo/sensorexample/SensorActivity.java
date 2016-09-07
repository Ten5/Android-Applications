package hp.demo.sensorexample;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SensorActivity extends Activity {

	Button sensorList;
	SensorManager sm;
	String sensorNames[];
	ArrayList<String> sensors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		sensorList = (Button)findViewById(R.id.sensorList);
		
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		sensorList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SensorActivity.this);
				alertBuilder.setTitle("List of sensors: ");
				List<Sensor> ob = sm.getSensorList(Sensor.TYPE_ALL);
				sensors = new ArrayList<String>();
				int i=0;
				for(Sensor s:ob) {
					sensors.add(s.getName());
					i++;
				}
				sensorNames = new String[i];
				int j=0;
				while(j<i) {
					sensorNames[j] = sensors.get(j);
					j++;
				}
				alertBuilder.setItems(sensorNames, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(SensorActivity.this, sensorNames[which], Toast.LENGTH_SHORT).show();
					}
				});
				alertBuilder.show();
			}
		});
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
}
