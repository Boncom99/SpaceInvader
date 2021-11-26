import java.awt.Color;
import java.awt.Graphics;

public class Alien extends MovingObject{
	int totalLives;
	int lives;
	int moves;
	int direction;
	int verticalSpeed;
	@Override
	void move(int k) {
		x-=speed;
	}

	void moveVertical() {
		y+=verticalSpeed* direction;
		moves++;
	}
	Alien(int x,int y,int width, int height , int speed,Color c, int totalLives , int totalMovesVertical) {
		super(x, y,width,height, speed, c);
		this.totalLives = totalLives;
		this.lives = totalLives;
		direction = 1;
		moves = totalMovesVertical/2;
		verticalSpeed = 1;
	
		}

		void paintAlien(Graphics g){
		g.setColor(c); //magenta
		g.fillRoundRect(x, y, width,height,10,10);
		g.setColor(new Color(235, 223, 100)); //groc
		int w = (width / totalLives) * (totalLives - lives);
		g.fillRoundRect(x, y,w,height,10,10);
		}
	}
