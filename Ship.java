import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Ship extends MovingObject implements ImageObserver {
	int lives;
	int numOfGuns;
	Image icon = new ImageIcon(new File("ship2.png").getAbsolutePath()).getImage();

	public Ship(int x, int y, int width, int height, int speed, Color c, int lives, int numOfGuns) {
		super(x, y, width, height, speed, c);
		this.lives = lives;
		this.numOfGuns = numOfGuns;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	void move(int k) {
		y += k * speed;
	}

	void impactShip(List<Bullet> B, List<Audio> audio) {
		if (B.size() > 0) {
			List<Bullet> foundB = new ArrayList<Bullet>();
			for (Bullet b : B) {
				if (this.overlap(b)) {
					foundB.add(b);
					this.lives--;
					audio.add(new Audio(3));
				}
			}
			B.removeAll(foundB);
		}
	}

	void paintShip(Graphics g) {
		// g.setColor(c);
		g.drawImage(icon, x, y, 60, 60, this);
		// g.fillRoundRect(x, y, width, height, 10, 10);
		/*
		 * for (int i = 1; i < numOfGuns + 1; i++) { // draw gun
		 * g.fillRect(x + width, y + i * height / (numOfGuns + 1) + 1, 7, 2);
		 * }
		 */
	}

	void shoot(List<Bullet> bullets, int speedBullets, Color color) {
		for (int i = 1; i < numOfGuns + 1; i++) {
			bullets.add(new Bullet(x + width, y + i * height / (numOfGuns + 1), 8, 4, speedBullets, color));
		}
	}

}
