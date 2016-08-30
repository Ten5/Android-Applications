package hp.demo.dialogboxexample;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	Button b, b1;
	String arr[] = {"No.7!","Pondra","Seniors","Pen Handler","Midnight Scope","X-Ray Vision","Bhaat Boka Skill","Home Fantasy", "Neighbourhood Love"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		b1 = (Button)findViewById(R.id.button2);
		b1.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		int arg = v.getId();
		AlertDialog.Builder ob = new AlertDialog.Builder(MainActivity.this);
		switch(arg) {
			case R.id.button1:				
				ob
				.setTitle("Floodlight!!")
				.setMessage("Warning!! Floodlight and No. 7 nearby!")
				.setPositiveButton("Runnn!!", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Let them do 69!", Toast.LENGTH_SHORT).show();
						dialog.cancel();				
					}
				})
				.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Take their pic! :D!", Toast.LENGTH_SHORT).show();
						dialog.cancel();
					}
				})
				.show()
				.setInverseBackgroundForced(true);
				break;
			case R.id.button2:
				ob
				.setMessage("Floodlight Contents:\nPresenting:")
				.setItems(arr, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), arr[which], Toast.LENGTH_LONG).show();
					}
				})
				.setPositiveButton("Fuck Yeah!", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				})
				.show();
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
