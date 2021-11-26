import java.awt.Color;

public class Ship extends MovingObject{
	public Ship(int x, int y,int width, int height, int speed, Color c) {
		super(x, y,width,height, speed, c);
	}
	@Override
	void move(int k) {
		y+= k*speed;
	}

	
	
}
