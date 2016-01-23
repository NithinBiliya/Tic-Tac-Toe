package com.benlay.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = "TTTGamePanel";
	protected static String textDisplayed;
	
	Bitmap imageBackGround,imageX,imageO;
	Rect holderBackGround,holderXO;
	Box boxes[][];
	public final static int X=-1;
	public final static int O=1;
	public static boolean move=false;
	public static int type=O;
	Paint textPaint;
	//public static GameMenu gm=new GameMenu();
Context c;

	public GamePanel(Context context) {
		super(context);
		c=context;
		getHolder().addCallback(this);
		setFocusable(true);
		imageBackGround=BitmapFactory.decodeResource(getResources(), R.drawable.grid);
		imageX=BitmapFactory.decodeResource(getResources(), R.drawable.x);
		imageO=BitmapFactory.decodeResource(getResources(), R.drawable.o);
		holderBackGround=new Rect(0,0,0,0);
		holderXO=new Rect(0,0,0,0);
		boxes=new Box[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				boxes[i][j]=new Box();
		textDisplayed="Not Connected";
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setStyle(Style.FILL);
		textPaint.setColor(Color.BLACK);
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

		holderBackGround.top=0;
		holderBackGround.bottom=height;
		holderBackGround.left=0;
		holderBackGround.right=width;
		//Log.d(TAG,0+"  "+ 0.06*height+"  "+0.24*width+"  "+0.08*height+"  "+0.23*width+"  "+0.29*height+"  "+0+"  "+0.33*height);
		
		boxes[0][0].touchQuad.insertValues(0, 0.06*height, 0.24*width, 0.08*height, 0.23*width, 0.29*height, 0, 0.33*height);
		boxes[0][1].touchQuad.insertValues(0.26*width,0.08*height,0.62*width,0.03*height,0.6*width,0.23*height,0.27*width,0.29*height);
		boxes[0][2].touchQuad.insertValues(0.66*width,0,width,0,width,0.19*height,0.64*width,0.22*height);
		boxes[1][0].touchQuad.insertValues(0,0.37*height,0.22*width,0.31*height,0.27*width,0.54*height,0,0.65*height);
		boxes[1][1].touchQuad.insertValues(0.27*width,0.32*height,0.6*width,0.25*height,0.62*width,0.45*height,0.29*width,0.53*height);
		boxes[1][2].touchQuad.insertValues(0.65*width,0.25*height,width,0.21*height,width,0.43*height,0.65*width,0.45*height);
		boxes[2][0].touchQuad.insertValues(0,0.66*height,0.27*width,0.56*height,0.36*width,0.75*height,0,0.75*height);
		boxes[2][1].touchQuad.insertValues(0.3*width,0.56*height,0.63*width,0.47*height,0.7*width,0.75*height,0.39*width,0.75*height);
		boxes[2][2].touchQuad.insertValues(0.65*width, 0.47*height, width, 0.45*height, width, 0.75*height, 0.72*width, 0.75*height);
		
		//		ltrb

		
		//Log.d(TAG,0+"  "+ 0.1*height+"  "+ 0.23*width+"  "+ 0.33*height);
		
		boxes[0][0].xRect.set((int)0,(int) (0.1*height),(int) (0.23*width),(int) (0.33*height));
		boxes[0][1].xRect.set((int)(0.26*width),(int) (0.08*height),(int) (0.6*width),(int) (0.29*height));
		boxes[0][2].xRect.set((int)(0.63*width),(int) 0,(int) (0.92*width),(int) (0.25*height));
		boxes[1][0].xRect.set((int)0,(int) (0.34*height),(int) (0.27*width),(int) (0.58*height));
		boxes[1][1].xRect.set((int)(0.27*width),(int) (0.29*height),(int) (0.62*width),(int) (0.5*height));
		boxes[1][2].xRect.set((int)(0.65*width),(int) (0.21*height),(int) width,(int) (0.47*height));
		boxes[2][0].xRect.set((int)(0.08*width),(int) (0.6*height),(int) (0.32*width),(int) (0.75*height));
		boxes[2][1].xRect.set((int)(0.3*width),(int) (0.52*height),(int) (0.7*width),(int) (0.75*height));
		boxes[2][2].xRect.set((int)(0.65*width),(int) (0.45*height),(int) width,(int) (0.71*height));
		
		boxes[0][0].oRect.set((int)0,(int) (0.12*height),(int) (0.26*width),(int) (0.33*height));
		boxes[0][1].oRect.set((int)(0.26*width),(int) (0.08*height),(int) (0.63*width),(int) (0.29*height));
		boxes[0][2].oRect.set((int)(0.63*width),(int) (0.03*height),(int) width,(int) (0.24*height));
		boxes[1][0].oRect.set((int)0,(int) (0.36*height),(int) (0.3*width),(int) (0.58*height));
		boxes[1][1].oRect.set((int)(0.28*width),(int) (0.29*height),(int) (0.66*width),(int) (0.5*height));
		boxes[1][2].oRect.set((int)(0.63*width),(int) (0.23*height),(int) width,(int) (0.47*height));
		boxes[2][0].oRect.set((int)(0.1*width),(int) (0.6*height),(int) (0.37*width),(int) (0.75*height));
		boxes[2][1].oRect.set((int)(0.3*width),(int) (0.52*height),(int) (0.75*width),(int) (0.75*height));
		boxes[2][2].oRect.set((int)(0.65*width),(int) (0.46*height),(int) width,(int) (0.71*height));
	}
	public void surfaceCreated(SurfaceHolder holder) {
		//	Initialize game values

		invalidate();
		this.setBackgroundColor(Color.WHITE);
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
		//	Destroy game values
	}
	public void iTouch(int i,int j) {
		boxes[i][j].xoro=type;
		((GameMenu) c).sendMessage(i,j);
		move=false;
		invalidate();
	}
	public void uTouch(int i,int j) {
		if(type==O)
			boxes[i][j].xoro=X;
		else if(type==X)
			boxes[i][j].xoro=O;
		//((GameMenu) c).sendMessage(i,j);
		move=true;
		invalidate();
	}
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN) {
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(boxes[i][j].touchQuad.pointInside(event.getX(), event.getY())) {
						if(boxes[i][j].xoro==0 && move) {
							iTouch(i,j);
						}
						//Log.d(TAG, i+"  "+j+"  "+boxes[i][j].xoro);
						else {
							Toast.makeText(c, "Wrong Input!!!", Toast.LENGTH_LONG).show();
						}
					}
			//Log.d(TAG,event.getX()+"  "+event.getY());
		}
		return super.onTouchEvent(event);
	}
	protected void onDraw(Canvas canvas) {
		//canvas.drawColor(Color.BLACK);
		canvas.drawText(textDisplayed, 10, 10, textPaint);
		canvas.drawBitmap(imageBackGround, null, holderBackGround, null);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				if(boxes[i][j].xoro==0)
					continue;
				else if(boxes[i][j].xoro==X) {
					Log.d(TAG,i+"  "+j);
					
					holderXO.set(boxes[i][j].xRect);
					canvas.drawBitmap(imageX, null, holderXO, null);
					//canvas.drawBitmap(imageO, 10, 10, null);
				}
				else if(boxes[i][j].xoro==O) {
					holderXO.set(boxes[i][j].oRect);
					canvas.drawBitmap(imageO, null, holderXO, null);
				}
			}
	}
}
