import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	boolean showInfo = true;
	boolean showLog= true;
	//bullets
	List<Bullet> bullet = new ArrayList<Bullet>();
	Color bulletColor;
	long timeOfLastProjectile =  System.currentTimeMillis();
	int timeDelayBullet = 50;
	int speedBullets=10;
	int MaxBullets = 30;
	int bulletPower = 1;
	//Aliens
	List<Aliens> aliens= new ArrayList<Aliens>();
	Color aliensColor;
	int speedAliens=1;
	int MaxAliens= 25;
	int AliensLives = 10;
	
	//Ship
	Color shipColor;
	int numsOfGuns=1;
	int speedShip=7;
	Ship ship;
	
	int level=0;
	int lives=5000;
	int score = 0;
	long durationOfLevel = 30000;
	long timeOfLastLevel= 0;
	int X=70;
	Graphics g;
	Color textColor= new Color(255,255 , 255);
	Color backgroundColor= new Color(0,0 , 0);
	Color groundColor= new Color(147, 81, 22);
	Finestra f;
	int numOfRails;
	Random r=new Random();
	Game(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void updateVarsOnLevelChange() {
		MaxBullets += 10;
		MaxAliens+=  10;
		lives+= 2 ;
		bulletPower += 1;
		AliensLives += 2;
		timeDelayBullet -=  2;
		durationOfLevel += level * 400;
		speedAliens += 1;
		speedShip+= 1;
		speedBullets+= 2;
			numsOfGuns +=1;


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
		while(aliens.size() <= MaxAliens) {

			aliens.add(new Aliens(f.WIDTH,45+(45*(Math.abs(r.nextInt())%(numOfRails-1))),80,40, speedAliens, aliensColor,AliensLives));
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
				for (int i = 0; i < numsOfGuns; i++) {
				bullet.add(new Bullet(ship.x+ship.width, ship.y+i*ship.height/numsOfGuns,8,3, speedBullets, bulletColor));
				}
			}
}
	}


	void run() {
		initialize();
		while (!gameOver()) {
		long Now = System.currentTimeMillis();
			if ((Now - timeOfLastLevel) > durationOfLevel) {
				level++;
				updateVarsOnLevelChange();
  			  	timeOfLastLevel = Now;
			}
			GenerateAliens();
			impacts();
			moveAliens();
			moveBullets();
			rePaint(Now);
			f.repaint();
			try {
				Thread.sleep(15);
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
		ship = new Ship(X,f.HEIGHT / 2,25,100,speedShip, shipColor);
		numOfRails = f.HEIGHT / (40 + 5);
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
					if (a.lives == 1) {
					foundA.add(a);
					}
					else {
						a.lives--;
					}
					foundB.add(b);
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
		if (showInfo) {
			g.drawString("Score: " + score, 20, 500);
			g.drawString("Level: " + (level), 20, 550);
			g.drawString("time left: " + (-Now + timeOfLastLevel + durationOfLevel), 20, 600);
			g.drawString("❤️ : " + lives, 20, 520);
			g.drawString("bullets left: " + (MaxBullets - bullet.size()), 20, 650);
		}
		if (showLog) {
		g.drawString("speed of bullets "+ speedBullets, 20, 100);
		g.drawString("max num of aliens "+ MaxAliens, 20, 150);
		g.drawString("Ship speed "+ speedShip, 20, 200);
		g.drawString("Aliens speed "+ speedAliens, 20, 250);
		g.drawString("numOfrails "+ numOfRails, 20, 300);
	
		}
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paintAlien(g);
	}
}
