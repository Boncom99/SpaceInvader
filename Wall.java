import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class Wall extends MovingObject{
	List <Brick> bricks;
	public Wall(int x, int y,int width, int height,int speed, Color c) {

		super(x, y,width,height, speed, c);
		int h=10;
		int w=4;
		bricks =new ArrayList<Brick>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
			bricks.add(new Brick(x+i*w,y+ j*h,w,h,speed, c));
			}
		}

	}

	@Override
	void move(int k) {
		for (Brick brick : bricks) {
			brick.move(-1);
		}	
	}
	void paint(Graphics g) {
		for (Brick brick : bricks) {
			brick.paint(g);
		}
	}

	
}
