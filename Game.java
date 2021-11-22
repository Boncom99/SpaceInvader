import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	int level=0;
	int score = 0;
	long durationOfLevel = 30000;
	long timeOfLastLevel= 0;
	int X=70;
	Graphics g;
	Color bulletColor;
	Color textColor= new Color(255,255 , 255);
	Color backgroundColor= new Color(0,0 , 0);
	Color groundColor= new Color(147, 81, 22);
	Color shipColor;
	Color aliensColor;
	long timeOfLastProjectile = 0;
	int timeDelayBullet = 50;
	Finestra f;
	List<Aliens> aliens= new ArrayList<Aliens>();
	Ship ship;
	List<Bullet> bullet = new ArrayList<Bullet>();
	Random r=new Random();
	int lives=5;
	int MaxBullets = 30;
	Game(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void updateVarsOnLevelChange() {
		MaxBullets += level * 10;
		lives+= level ;
		timeDelayBullet -= level * 2;
		durationOfLevel += level * 1000;

	}
	boolean gameOver() {
		if (lives <= 0) {
			return true;
		}
		return false;
	}
	void move(int k) {
		ship.moveNau(k);
		if (ship.IsOutOfRange(f.WIDTH, f.HEIGHT) ) {
		ship.moveNau(-k);
		}
	}

	void GenerateAliens() {
		if ((Math.abs(r.nextInt())% 100) >= 98) {
			for (int i = 0; i < level; i++) {
			aliens.add(new Aliens(f.WIDTH,Math.abs(r.nextInt())%f.HEIGHT,40,40, 3, aliensColor));
			}
		}

	}
	void deleteBullet( int i) {
		bullet.remove(i);
	}

	void deleteAliens(int i) {
		aliens.remove(i);
	}
	void shoot() {
		long timeNow = System.currentTimeMillis();
		long time = timeNow - timeOfLastProjectile;
		if (bullet.size() < MaxBullets - 1) {
			if (time < 0 || time > timeDelayBullet) {
  			  	timeOfLastProjectile = timeNow;
				bullet.add(new Bullet(ship.x+ship.width, ship.y+ship.height/2,10,3, 10, bulletColor));
			}
}
	}


	void run() {
		initialize();
		while (!gameOver()) {
		long Now = System.currentTimeMillis();
		long duration = Now - timeOfLastLevel;
			if ( duration > durationOfLevel) {
  			  	timeOfLastLevel = Now;
				level++;
				updateVarsOnLevelChange();
			}
			GenerateAliens();
			impacts();
			moveAliens();
			moveBullets();
			rePaint(Now);
			f.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void initialize() {
		bulletColor = new Color(255, 100, 0);
		shipColor = new Color(0, 50, 255);
		aliensColor = new Color(0, 255, 10);
		bulletColor = new Color(255, 100, 0);
		ship = new Ship(X,f.HEIGHT / 2,40,40,15, shipColor);
		for(int i=0;i<10;i++) {
			aliens.add(new Aliens(f.WIDTH,Math.abs(r.nextInt())%f.HEIGHT,40,40, 3, aliensColor));
		}
	}
	void moveAliens() {
		for(int i=0;i<aliens.size();i++){
			aliens.get(i).move();
			if (aliens.get(i).IsOutOfRange(f.WIDTH,f.HEIGHT)) {
				deleteAliens(i);
				lives--;
			}
		}
	}
	void moveBullets() {
		for(int i=0;i<bullet.size();i++){
			bullet.get(i).move();
			if (bullet.get(i).IsOutOfRange(f.WIDTH, f.HEIGHT)) {
				deleteBullet(i);
			}
		}
	}

	void impacts() {
		if (bullet.size() > 0 && aliens.size() > 0) {

		List<Bullet> foundB = new ArrayList<Bullet>();
		List<Aliens> foundA = new ArrayList<Aliens>();
		for (Bullet b : bullet) {
			for (Aliens a : aliens) {
				if ((a.x <= b.x + b.width) && (a.y <= b.y && b.y <= a.y + a.height)) {
					foundB.add(b);
					foundA.add(a);
					score++;
				}
			}
		}
			bullet.removeAll(foundB);
			aliens.removeAll(foundA);
		foundA = null;
		foundB = null;
		}

	}

	void rePaint(long Now) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(groundColor);
		g.fillRect(0, 0, X-10, f.HEIGHT);
		g.setColor(textColor);
		g.drawString("Score: "+ score, 20, 500);
		g.drawString("Level: "+ level, 20, 550);
		g.drawString("time left: "+(-Now+timeOfLastLevel+durationOfLevel), 20, 600);
		g.drawString("❤️ : "+lives, 20, 520);
		g.drawString("bullets left: " +(MaxBullets-bullet.size()), 20, 650);
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paint(g);
	}
}
