import java.awt.Color;
import java.awt.Graphics;

public class Alien extends MovingObject{
	int totalLives;
	int lives;
	int moves;
	int direction;
	int verticalSpeed;
	@Override
	void move() {
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
		g.setColor(new Color(235, 223, 100)); //groc
		int w = (width / totalLives) * (totalLives - lives);
		g.fillRect(x, y,w,height);
		//g.setColor(c);
		g.setColor( new Color(219, 85, 221)); //magenta
		g.fillRect(x+w, y, (width/totalLives) *(lives),height);
		}
	}
