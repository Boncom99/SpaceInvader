import java.awt.Color;
import java.awt.Graphics;

public class Brick{
	int x;
	int y;
	int width;
	int height;
	Color c;
	public Brick(int x, int y,int width, int height, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.c = c;

	}
	void paint(Graphics g){
		g.setColor(c); 
		g.fillRect(x, y,width,height);
		}

	
}
