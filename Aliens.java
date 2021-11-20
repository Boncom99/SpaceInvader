import java.awt.Color;

public class Aliens extends MovingObject{
	@Override
	void move() {
		x-=speed;
	}
	Aliens(int x,int y,int width, int height , int speed,Color c ) {
		super(x, y,width,height, speed, c);
		}
	}
