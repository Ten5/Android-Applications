package hp.demo.bluetoothsample;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BluetoothActivity extends ActionBarActivity {

	ToggleButton blue;
	BluetoothAdapter blueA;
	CheckBox cb;
	Button search;
	AlertDialog.Builder alertBuilder;
	ArrayList<String> devices = new ArrayList<String>();
	ListView lv;
	BroadcastReceiver br;
	final int BLUETOOTH_ON = 1, BLUETOOTH_DISCOVERABLE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		blue = (ToggleButton)findViewById(R.id.toggle1);
		blueA = BluetoothAdapter.getDefaultAdapter();
		cb = (CheckBox)findViewById(R.id.cbox1);
		search = (Button)findViewById(R.id.search);
		lv = (ListView)findViewById(R.id.listView1);
		br = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if(action.equals(BluetoothDevice.ACTION_FOUND)) {
					BluetoothDevice b = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					devices.add(b.getName() + " " + b.getAddress());
					//Add devices to view.
				}				
			};
		};
		alertBuilder = new AlertDialog.Builder(BluetoothActivity.this);
		alertBuilder.setTitle("Paired Bluetooth Devices: ");
		
		if(blueA == null) {
			Toast.makeText(BluetoothActivity.this, "No bluetooth device found!", Toast.LENGTH_SHORT).show();
			finish();
		}
		if(blueA.isEnabled())
			blue.toggle();
		
		blue.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					if(!blueA.isEnabled()) {
						Intent blueIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
						startActivityForResult(blueIntent, BLUETOOTH_ON);
					}
				}
				else {
					if(blueA.isEnabled())
						blueA.disable();
				}
			}
		});
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					if(blueA.isEnabled()) {
						Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
						intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
						startActivityForResult(intent, BLUETOOTH_DISCOVERABLE);
					}
					else {
						Toast.makeText(BluetoothActivity.this, "Please Enable Bluetooth!", Toast.LENGTH_SHORT).show();
						cb.setChecked(false);
					}
				}
			}
		});
		
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(blueA.isEnabled()) {
					blueA.startDiscovery();
				}
				else
					Toast.makeText(BluetoothActivity.this, "Please Enable Bluetooth!", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(br, filter);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(br);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if(requestCode == BLUETOOTH_ON) {
			if(resultCode == RESULT_OK)
				Toast.makeText(BluetoothActivity.this, "Bluetooth is now enabled!", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(BluetoothActivity.this, "Bluetooth is not enabled!", Toast.LENGTH_SHORT).show();
		}
		if(requestCode == BLUETOOTH_DISCOVERABLE) {
			if(resultCode == RESULT_OK)
				Toast.makeText(BluetoothActivity.this, "Bluetooth is now discoverable!", Toast.LENGTH_SHORT).show();
			else {
				Toast.makeText(BluetoothActivity.this, "Bluetooth is not discoverable!", Toast.LENGTH_SHORT).show();
				cb.setChecked(false);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bluetooth, menu);
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
