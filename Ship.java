import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Ship extends MovingObject{
	int lives;
	public Ship(int x, int y,int width, int height, int speed, Color c, int lives) {
		super(x, y,width,height, speed, c);
		this.lives = lives;
	}
	@Override
	void move(int k) {
		y+= k*speed;
	}

	void impactShip(List<Bullet> B){
		if (B.size() > 0) {
			List<Bullet> foundB = new ArrayList<Bullet>();
			for (Bullet b : B) {
				if (this.overlap(b)) {
					foundB.add(b);
					this.lives--;
				}
			}
			B.removeAll(foundB);
		}
	}

	
	
}
