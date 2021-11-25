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
		void impact(ArrayList <MovingObject> A, ArrayList <MovingObject> B) {
		if (A.size() > 0 && B.size() > 0) {

			List<MovingObject> foundB = new ArrayList<MovingObject>();
			for (MovingObject b : B) {
				for (MovingObject a : A) {
					if ((a.x <= b.x + b.width && b.x <= a.x + a.width)
							&& (a.y <= b.y+b.height && b.y <= a.y + a.height)) {

							A.remove(a);
						foundB.add(b);
						break;
					}
				}
			}
			B.removeAll(foundB);
			foundB = null;
		}

	}
    abstract void move();
	void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width,height);
	}

}
