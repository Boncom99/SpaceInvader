import java.awt.Color;
import java.awt.Graphics;

public class Brick extends MovingObject{
;
	@Override
	void move(int k) {
		x += k*speed;
		
	}
	public Brick(int x, int y,int width, int height,int speed, Color c){

		super(x, y,width,height, speed, c);

	}
	void paint(Graphics g){
		g.setColor(c); 
		g.fillRect(x, y,width,height);
		}

	
}
