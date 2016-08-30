package hp.demo.camerasample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class CameraActivity extends ActionBarActivity {

	ImageButton imgb;
	static int IMAGE_CAPTURE;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		imgb = (ImageButton)findViewById(R.id.imageButton1);
		imgb.setImageResource(R.drawable.ic_launcher);
	}
	
	public void takePic(View v) {
		Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i, IMAGE_CAPTURE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		if(resultCode == RESULT_OK && requestCode == IMAGE_CAPTURE) {
			Bundle b = intent.getExtras();
			Bitmap bmp = (Bitmap)b.get("data");
			imgb.setImageBitmap(bmp);			
			Toast.makeText(getApplicationContext(), "Image Captured Successfully", Toast.LENGTH_SHORT).show();
		}
		else
			Toast.makeText(getApplicationContext(), "Image Captured Failed!", Toast.LENGTH_SHORT).show();
	}
}
