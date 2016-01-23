package com.benlay.tictactoe;

import android.graphics.Rect;

public class Box {
	protected int xoro;
	protected Quad touchQuad;
	protected Rect xRect,oRect;
	public Box() {
		touchQuad=new Quad(0,0,0,0,0,0,0,0);
		xRect=new Rect(0,0,0,0);
		oRect=new Rect(0,0,0,0);
		xoro=0;
	}
}
