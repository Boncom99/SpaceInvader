import java.awt.Color;
import java.awt.Graphics;

public abstract class MovingObject{
	int y;
    int x;
    int width;
    int height;
	int speed;
    Color c;

	public MovingObject(int x ,int y,int width, int height,int speed, Color c) {

        this.x = x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.speed = speed;
        this.c=c;

	}
    int IsOutOfRange(int w_width, int w_height) {
		if (x < 0 || x > w_width || y < 0 || y >w_height) {
			return 1;
		}
			return 0;

	}
    abstract void move();
	void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width,height);
	}

}
