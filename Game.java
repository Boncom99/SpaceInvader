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
	int bulletPower = 1;
	//Aliens
	List<Aliens> aliens= new ArrayList<Aliens>();
	Color aliensColor;
	int speedAliens=30;
	int AliensLives = 4;
	int AliensHeight=15;
	int AliensWidth=30;
	int numOfAliensPerColumn =20;
	int numOfAliensPerRow =10;
	int marginV = 10;
	int marginH = 10;
	int marginTop =50;
	int marginBottom=50;
	int initialX;
	int initialY;
	int totalMovesVertical ;
	
	//Ship
	Color shipColor;
	int numsOfGuns=1;
	int speedShip=7;
	Ship ship;

	//StartButton;
	int Sx;
	int SW;
	int Sy;
	int SH;
	//Game
	boolean gameStart=false;
	long TimeStart = 0;
	int randZERO_ONE = 1;
	int level=0;
	long NumOfFrames=0;
	int lives=50;
	int score = 0;
	long durationOfLevel = 30000;
	long timeOfLastLevel= 0;
	int X=70;
	Graphics g;
	Color textColor= new Color(255,255 , 255);
	Color backgroundColor= new Color(0,0 , 0);
	Color groundColor= new Color(98, 222, 109);
	Finestra f;
	Random r=new Random();
	Game(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void updateVarsOnLevelChange() {
		lives+= 2 ;
		bulletPower += 1;
		AliensLives += 2;
		timeDelayBullet -=  2;
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

		for (int i = 0; i < numOfAliensPerColumn; i++) {
			for (int j = 0; j < numOfAliensPerRow; j++) {
			aliens.add(new Aliens(initialX+ j*(AliensWidth+marginH),initialY+i*(AliensHeight+marginV),AliensWidth,AliensHeight, speedAliens, aliensColor,AliensLives, totalMovesVertical));
			}
		}

	}
	void deleteBullet( int i) {
		bullet.remove(i);
	}

	void start(int x, int y) {
		if ((x > Sx && x < Sx + SW) && (y > Sy && y < Sy + SH)) {
			gameStart = true;
		}
	}

	void deleteAliens(int i) {
		aliens.remove(i);
	}
	void shoot() {
		long timeNow = System.currentTimeMillis();
		long time = timeNow - timeOfLastProjectile;
			if (time < 0 || time > timeDelayBullet) {
  			  	timeOfLastProjectile = timeNow;
				for (int i = 1; i < numsOfGuns+1; i++) {
				bullet.add(new Bullet(ship.x+ship.width, ship.y+i*ship.height/(numsOfGuns+1),8,3, speedBullets, bulletColor));
				}
			}
	}

	void randomMove() {
		shoot();
		long timeNow = System.currentTimeMillis();
		long time = timeNow - TimeStart;
		if (time < 0 || time > 200) {
			TimeStart = timeNow;
			randZERO_ONE = Math.abs(r.nextInt()) % 2 - 1;
			if (randZERO_ONE == 0) {
				randZERO_ONE = 1;
			}
		}
		move(randZERO_ONE);
		impacts();
		moveAliens();
		moveBullets();
}

	void run() {

		initialize();

		GenerateAliens();
		while (!gameStart) {
			numsOfGuns = 2;
			rePaintStart();
			f.repaint();
			randomMove();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		numsOfGuns = 1;
		aliens.clear();
		GenerateAliens();
		while (!gameOver()&& gameStart) {
			if ( aliens.size()==0) {
				level++;
				updateVarsOnLevelChange();
				try {
				Thread.sleep(500);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				GenerateAliens();
			}
			impacts();
			moveAliens();
			moveBullets();
			rePaint();
			f.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (gameOver()) {
		rePaintEND();
			f.repaint();	
		}
	}
	void initialize() {
		bulletColor = new Color(248, 59, 58) ; //red
		shipColor = new Color (83, 83, 241); //lila
		aliensColor = new Color(0, 255, 10);
		bulletColor = new Color(248, 59, 58);

		ship = new Ship(X,f.HEIGHT / 2,25,100,speedShip, shipColor);
	 	initialX = (f.WIDTH- (AliensWidth+marginH)* numOfAliensPerRow)/2;
		initialY = (f.HEIGHT- (AliensHeight+marginV)* numOfAliensPerColumn)/2;
		//Start Button position
		Sx = f.WIDTH / 2 - 200;
		Sy = 3*f.HEIGHT / 4 - 50;
		SW = 250;
		SH = 100;
		//
		totalMovesVertical = initialY * 2 -10;
	

	}
	void moveAliens() {
	
		for(int i=0;i<aliens.size();i++){
			if (aliens.get(i).moves > totalMovesVertical) {
				aliens.get(i).moves = 0;
				aliens.get(i).direction *= -1;
				aliens.get(i).move();
			}
			
			aliens.get(i).moveVertical();
			/*if (aliens.get(i).IsOutOfRange(f.WIDTH,f.HEIGHT)) {
				deleteAliens(i);
				lives--;
			}*/
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
				if ((a.x <= b.x + b.width && b.x + b.width <=a.x+a.width) && (a.y <= b.y && b.y <= a.y + a.height)) {
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
void rePaintStart() {
		g.setFont(f.BigFont);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(groundColor);
		g.fillRect(0, 0, X-10, f.HEIGHT);
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paintAlien(g);
		g.setColor(new Color(66, 233, 244));
		g.drawString("SPACE INVADERS", f.WIDTH/2-400, f.HEIGHT/2);
		g.setColor(new Color(235, 223, 100));
		g.drawString("START", Sx, Sy+100);

	}
	void rePaintEND() {
		g.setFont(f.BigFont);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(groundColor);
		g.fillRect(0, 0, X-10, f.HEIGHT);
		g.setColor(textColor);
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paintAlien(g);
		g.setColor(new Color(66, 233, 244));
		g.fillRect(f.WIDTH/4, f.HEIGHT/4, 2*(f.WIDTH/4), 2*(f.HEIGHT/4));
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER ", f.WIDTH/2, f.HEIGHT/2);
	}
		void rePaint() {
		g.setFont(f.smallFont);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(groundColor);
		g.fillRect(0, 0, X-10, f.HEIGHT);
		g.setColor(textColor);
		if (showInfo) {
			g.drawString("Score: " + score, 20, 500);
			g.drawString("Level: " + (level), 20, 550);
			g.drawString("❤️ : " + lives, 20, 520);
		}
		if (showLog) {
		g.drawString("speed of bullets "+ speedBullets, 20, 100);
		g.drawString("Ship speed "+ speedShip, 20, 200);
		g.drawString("Aliens speed "+ speedAliens, 20, 250);
		g.drawString("numOfAliens per column"+ numOfAliensPerColumn , 20, 300);
	
		}
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paintAlien(g);
	}
}
