import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

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
    boolean IsOutOfRange(int w_width, int w_height) {
		if (x < 0 || x > w_width || y < 0 || y+height >w_height) {
			return true;
		}
			return false;

	}

	boolean overlap(MovingObject object) {
		if((x <= object.x + object.width && object.x <= x + width)
				&& (y <= object.y + object.height && object.y <= y + height)) {
			return true;
			}
			return false;
	}
	boolean impact(List <Bullet> A) {
		if (A.size() > 0) {
			for (Bullet a : A) {
				if (a.overlap(this)) {
					A.remove(a);
					return true;
				}
			}
		}
		return false;
	}	
    abstract void move(int k);
	void paint(Graphics g) {
		g.setColor(c);
		g.fillRoundRect(x, y, width,height,10,10);
	}

}
