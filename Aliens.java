import java.awt.Color;
import java.awt.Graphics;

public class Aliens extends MovingObject{
	int totalLives;
	int lives;
	@Override
	void move() {
		x-=speed;
	}
	Aliens(int x,int y,int width, int height , int speed,Color c, int totalLives ) {
		super(x, y,width,height, speed, c);
		this.totalLives = totalLives;
		this.lives = totalLives;
		}

		void paintAlien(Graphics g){
		g.setColor(Color.ORANGE);
		int w = (width / totalLives) * (totalLives - lives);
		System.out.println(w);
		g.fillRect(x, y,w,height);
		//g.setColor(c);
		g.setColor(Color.MAGENTA);
		g.fillRect(x+w, y, (width/totalLives) *(lives),height);
		}
	}
