import java.awt.Color;
import java.awt.Graphics;

public class Ship extends MovingObject{
	public Ship(int x, int y,int width, int height, int speed, Color c) {
		super(x, y,width,height, speed, c);
	}
	@Override
	void move() {
	}

	void moveNau(int k){
		y+= k*speed;
	}

	
	
}
