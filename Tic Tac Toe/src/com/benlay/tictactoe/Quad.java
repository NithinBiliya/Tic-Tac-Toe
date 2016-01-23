package com.benlay.tictactoe;

public class Quad {
	protected double x1,y1,x2,y2,x3,y3,x4,y4;
	
	public Quad(double xx1,double yy1,double xx2,double yy2,double xx3,double yy3,double xx4,double yy4) {
		x1=xx1;
		y1=yy1;
		x2=xx2;
		y2=yy2;
		x3=xx3;
		y3=yy3;
		x4=xx4;
		y4=yy4;
	}
	public void insertValues(double xx1,double yy1,double xx2,double yy2,double xx3,double yy3,double xx4,double yy4) {
		x1=xx1;
		y1=yy1;
		x2=xx2;
		y2=yy2;
		x3=xx3;
		y3=yy3;
		x4=xx4;
		y4=yy4;
	}
	public boolean pointInside(double px,double py) {
		
		if(pointInTriangle(px, py, x1, y1, x2, y2, x3, y3))
			return true;
		if(pointInTriangle(px, py, x2, y2, x3, y3, x4, y4))
			return true;
		if(pointInTriangle(px, py, x3, y3, x4, y4, x1, y1))
			return true;
		if(pointInTriangle(px, py, x4, y4, x1, y1, x2, y2))
			return true;
		return false;
	}
	public double sign(double p1x,double p1y,double p2x,double p2y,double p3x,double p3y) {
		return (p1x - p3x) * (p2y - p3y) - (p2x - p3x) * (p1y - p3y);
	}
	public boolean pointInTriangle(double ptx,double pty,double  v1x,double v1y,double v2x,double v2y,double v3x,double v3y) {
	  boolean b1, b2, b3;

	  b1 = sign(ptx,pty, v1x,v1y, v2x,v2y) < 0.0f;
	  b2 = sign(ptx,pty, v2x,v2y, v3x,v3y) < 0.0f;
	  b3 = sign(ptx,pty, v3x,v3y, v1x,v1y) < 0.0f;

	  return ((b1 == b2) && (b2 == b3));
	}
}
