import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class Ship extends MovingObject{
	int lives;
	int numOfGuns;
	public Ship(int x, int y,int width, int height, int speed, Color c, int lives,int numOfGuns) {
		super(x, y,width,height, speed, c);
		this.lives = lives;
		this.numOfGuns=numOfGuns;
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
	void paintShip(Graphics g) {
		g.setColor(c);
		g.fillRoundRect(x, y, width,height,10,10);
		for (int i = 1; i < numOfGuns+1; i++) {     //draw gun
			g.fillRect(x+width, y+ i*height/(numOfGuns+1)+1,7,2);
		}
	}

	void shoot(List <Bullet> bullets , int speedBullets, Color color) {
		Audio a = new Audio();
		for (int i = 1; i < numOfGuns+1; i++) {
			bullets.add(new Bullet(x+width, y+i*height/(numOfGuns+1),8,4, speedBullets, color));
		}
	}

	
	
}
