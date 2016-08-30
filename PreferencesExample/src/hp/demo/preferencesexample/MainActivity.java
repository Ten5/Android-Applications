package hp.demo.preferencesexample;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

public class MainActivity extends ActionBarActivity {

	CheckBox c1, c2, c3, c4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		c1 = (CheckBox)findViewById(R.id.checkBox1);
		c2 = (CheckBox)findViewById(R.id.checkBox2);
		c3 = (CheckBox)findViewById(R.id.checkBox3);
		c4 = (CheckBox)findViewById(R.id.checkBox4);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SharedPreferences sp =  getSharedPreferences("MyPreferences", MODE_PRIVATE);
		c1.setChecked(sp.getBoolean("cricket", false));
		c2.setChecked(sp.getBoolean("football", false));
		c3.setChecked(sp.getBoolean("tennis", false));
		c4.setChecked(sp.getBoolean("hockey", false));
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences sp = getSharedPreferences("MyPreferences", MODE_PRIVATE);
		SharedPreferences.Editor ed = sp.edit();
		if(c1.isChecked())
			ed.putBoolean("cricket", true);
		else
			ed.remove("cricket");
		
		if(c2.isChecked())
			ed.putBoolean("football", true);
		else
			ed.remove("football");
		
		if(c3.isChecked())
			ed.putBoolean("tennis", true);
		else
			ed.remove("tennis");
		
		if(c4.isChecked())
			ed.putBoolean("hockey", true);
		else
			ed.remove("hockey");
		
		ed.commit();
	}
}
