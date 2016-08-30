package hp.demo.pictureviewer;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	ImageView iv;
	int prev;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.back);
		rl.setBackgroundColor(Color.BLACK);
		prev = -1;
		iv = (ImageView)findViewById(R.id.imageView1);
	}
	
	public void onClick(View v) {
		int random;
		do {
			random = (int)(Math.random()*7);
		}while(prev == random);
		prev = random;
		switch(random) {
			case 0: iv.setBackgroundResource(R.drawable.chrysanthemum);
					break;
			case 1: iv.setBackgroundResource(R.drawable.desert);
					break;
			case 2: iv.setBackgroundResource(R.drawable.hydrangeas);
					break;
			case 3: iv.setBackgroundResource(R.drawable.jellyfish);
					break;
			case 4: iv.setBackgroundResource(R.drawable.koala);
					break;
			case 5: iv.setBackgroundResource(R.drawable.lighthouse);
					break;
			case 6: iv.setBackgroundResource(R.drawable.penguins);
					break;
			case 7: iv.setBackgroundResource(R.drawable.tulips);
		}
	}
	
	public void onSet(View v) {
		WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
		Bitmap bmpImg = null;
		try {
			bmpImg = ((BitmapDrawable)iv.getBackground()).getBitmap();
		} catch(Exception e) {
			Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		try {
			wm.setBitmap(bmpImg);
			Toast.makeText(MainActivity.this, "Wallpaper Set Successfully", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, "Setting Wallpaper Failed", Toast.LENGTH_SHORT).show();
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
