import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	boolean showInfo= true;
	//bullets
	List<Bullet> bullets = new ArrayList<Bullet>();
	Color bulletColor;
	long timeOfLastProjectile =  System.currentTimeMillis();
	int timeDelayBullet = 50;
	int speedBullets=10;
	int bulletPower = 1;
	//Aliens
	List<Alien> aliens= new ArrayList<Alien>();
	Color aliensColor;
	int speedAliens=30;
	int AliensLives = 2;
	int AliensHeight=30;
	int AliensWidth=15;
	int numOfAliensPerColumn =10;
	int numOfAliensPerRow =5;
	int marginV = 8;
	int marginH = 20;
	int marginTop =50;
	int marginBottom=50;
	int initialX;
	int initialY;
	int totalMovesVertical ;
	
	//Ship
	Color shipColor;
	int numsOfGuns=1;
	int speedShip=4;
	Ship ship;

	//Wall
	Color wallColor;
	List<Wall> walls = new ArrayList<Wall>();

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
	Window f;
	Random r=new Random();
	Game(Window f) {
		this.f=f;
		this.g=f.g;
	}
	void initialize() {
		//Colors
		bulletColor = new Color(248, 59, 58) ; //red
		shipColor = new Color (83, 83, 241); //lila
		aliensColor = new Color(0, 255, 10);
		bulletColor = new Color(248, 59, 58);
		wallColor= new Color (98, 222, 109);

		ship = new Ship(X,f.HEIGHT / 2,20,50,speedShip, shipColor);

		//Aliens position
	 	initialX = (f.WIDTH- (AliensWidth+marginH)* numOfAliensPerRow)/2;
		initialY = (f.HEIGHT- (AliensHeight+marginV)* numOfAliensPerColumn)/2;
		//Start Button position
		Sx = f.WIDTH / 2 - 200;
		Sy = 3*f.HEIGHT / 4 - 50;
		SW = 250;
		SH = 100;
		//Wall
		//
		totalMovesVertical = initialY * 2 -10;

	

	}

	void updateVarsOnLevelChange() {
		lives+= 2 ;
		bulletPower += 1;
		AliensLives += 1;
		timeDelayBullet -=  1;
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
	void start(int x, int y) {
		if ((x > Sx && x < Sx + SW) && (y > Sy && y < Sy + SH)) {
			gameStart = true;
		}
	}

	void GenerateWall() {
		walls.add(new Wall(200, 150, 20, 10, wallColor));
		walls.add(new Wall(200, 350, 20, 10, wallColor));
		walls.add(new Wall(200, 600, 20, 10, wallColor));
	}
	
	void GenerateAliens() {

		for (int i = 0; i < numOfAliensPerColumn; i++) {
			for (int j = 0; j < numOfAliensPerRow; j++) {
			aliens.add(new Alien(initialX+ j*(AliensWidth+marginH),initialY+i*(AliensHeight+marginV),AliensWidth,AliensHeight, speedAliens, aliensColor,AliensLives, totalMovesVertical));
			}
		}

	}
	

	

	void shoot() {
		for (int i = 1; i < numsOfGuns+1; i++) {
		bullets.add(new Bullet(ship.x+ship.width, ship.y+i*ship.height/(numsOfGuns+1),8,3, speedBullets, bulletColor));
		}
	}

	void randomMove() {

		long timeNow = System.currentTimeMillis();
		long time = timeNow - TimeStart;
		if (time < 0 || time > 200) {
			shoot();
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
		bullets.clear();
		GenerateAliens();
		GenerateWall();
		while (!gameOver()&& gameStart) {
			if ( aliens.size()==0) {
				level++;
				bullets.clear();
				walls.clear();

				try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
				e.printStackTrace();
				}
				updateVarsOnLevelChange();
				GenerateAliens();
				GenerateWall();
			}
			impacts();
			wallImpacts();
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

//Moves

	void move(int k) {
		ship.moveNau(k);
		if (ship.IsOutOfRange(f.WIDTH, f.HEIGHT) ) {
		ship.moveNau(-k);
		}
	}


	void moveAliens() {
		for(int i=0;i<aliens.size();i++){
			if (aliens.get(i).moves > totalMovesVertical) {
				aliens.get(i).moves = 0;
				aliens.get(i).direction *= -1;
				aliens.get(i).move();
			}
			aliens.get(i).moveVertical();
			if (aliens.get(i).IsOutOfRange(f.WIDTH,f.HEIGHT)) {
				aliens.remove(i);
				lives--;
			}
		}
	}
	void moveBullets() {
		for(int i=0;i<bullets.size();i++){
			bullets.get(i).move();
			if (bullets.get(i).IsOutOfRange(f.WIDTH, f.HEIGHT)) {
				bullets.remove(i);
			}
		}
	}
	void wallImpacts() {
		for (int i = 0; i < walls.size(); i++) {
			
			if (bullets.size() > 0 && walls.get(i).bricks.size() > 0) {

				List<Bullet> foundBullets = new ArrayList<Bullet>();
				List<Brick> foundBricks = new ArrayList<Brick>();
				for (Bullet b : bullets) {
					for (Brick a : walls.get(i).bricks) {
						if ((a.x <= b.x + b.width )
								&& (a.y <= b.y && b.y <= a.y + a.height)) {
								foundBricks.add(a);
							foundBullets.add(b);
						}
					}
				}
				bullets.removeAll(foundBullets);
				walls.get(i).bricks.removeAll(foundBricks);
				foundBullets = null;
				foundBricks= null;
			}
		}

	}
	void impacts() {
		if (bullets.size() > 0 && aliens.size() > 0) {

			List<Bullet> foundB = new ArrayList<Bullet>();
			List<Alien> foundA = new ArrayList<Alien>();
			for (Bullet b : bullets) {
				for (Alien a : aliens) {
					if ((a.x <= b.x + b.width && b.x + b.width <= a.x + a.width)
							&& (a.y <= b.y && b.y <= a.y + a.height)) {
						if (a.lives == 1) {
							foundA.add(a);
						} else {
							a.lives--;
						}
						foundB.add(b);
						score++;
					}
				}
			}
			bullets.removeAll(foundB);
			aliens.removeAll(foundA);
			foundA = null;
			foundB = null;
		}

	}

//PAINTING
	void rePaintStart() {
		g.setFont(f.BigFont);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		ship.paint(g);
		for(int i=0;i<bullets.size();i++)
			bullets.get(i).paint(g);
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
		g.setColor(textColor);
		ship.paint(g);
		for(int i=0;i<bullets.size();i++)
			bullets.get(i).paint(g);
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
		g.setColor(textColor);
		if (showInfo) {
			int x=f.WIDTH-400;
			g.drawString("SCORE: " + score, x, 600);
			g.drawString("LEVEL: " + (level), x, 650);
			g.drawString("LIVES : " + lives, x, 700);
		}

		ship.paint(g);
		for (Bullet bullet:bullets) {
			bullet.paint(g);
		}
		for (Alien alien : aliens) {
			alien.paintAlien(g);
		}
		for (Wall wall : walls) {
			wall.paint(g);
		}
	}
}
