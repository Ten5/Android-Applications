package hp.demo.graphicssample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("ClickableViewAccessibility")
public class MyView  extends View {

	private int whichColor=Color.BLACK;
	
	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MyView(Context context, AttributeSet attrs) {
		super(context);
		// TODO Auto-generated constructor stub
	}	
	
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint p = new Paint();
		int prev = whichColor;
		while(whichColor == prev)
			whichColor = (int)(Math.random()* 4);
		switch(whichColor) {
			case 0: p.setColor(Color.BLUE);
					break;
			case 1: p.setColor(Color.GREEN);
					break;
			case 2: p.setColor(Color.YELLOW);
					break;
			case 3: p.setColor(Color.RED);
					break;
		}		
		canvas.drawCircle(40, 50, 20, p);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_UP) {
			float x = event.getX();
			float y = event.getY();
			
			if(x >= 20 && x <= 60 && y >= 30 && y <= 70)
				invalidate();
		}
		return true;
	}
}
