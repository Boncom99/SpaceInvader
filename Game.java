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
	List<Bullet> bulletsAliens = new ArrayList<Bullet>();
	Color aliensColor;
	int ShootingRate=800;
	int speedBulletsAlien=5;
	int speedAliens=30;
	int AliensLives = 2;
	int AliensHeight=30;
	int AliensWidth=15;
	int numOfAliensPerColumn = 10;
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
	int lives=15;
	int score = 0;
	long durationOfLevel = 30000;
	long timeOfLastLevel= 0;
	int X=50;
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

		ship = new Ship(X,f.HEIGHT / 2,20,45,speedShip, shipColor);

		//Aliens position
	 	initialX = 2*(f.WIDTH- (AliensWidth+marginH)* numOfAliensPerRow)/3;
		initialY = (f.HEIGHT- (AliensHeight+marginV)* numOfAliensPerColumn)/2;
		//Start Button position
		Sx = f.WIDTH / 2 - 200;
		Sy = 3*f.HEIGHT / 4 - 50;
		SW = 250;
		SH = 100;
		//Wall
		//
		totalMovesVertical = initialY * 2 -12;

	

	}

	void updateVarsOnLevelChange() {
		lives= 3 ;
		bulletPower += 1;
		AliensLives += 1;
		timeDelayBullet -=  1;
		speedShip+= 1;
		speedBullets+= 1;
		speedBulletsAlien += 1;
		ShootingRate += -50;
		if (numsOfGuns < 3) {
		numsOfGuns +=1;
		}


	}
	boolean gameOver() {
		if (lives <= 0) {
			return true;
		}
		return false;
	}
	void start(int x, int y) {
		if ((x >= Sx && x <= Sx + SW) && (y >= Sy && y <= Sy + SH)) {
			gameStart = true;
		}
	}

	void GenerateWall() {
		walls.add(new Wall(X+50, 100, 5, 6, wallColor));
		walls.add(new Wall(X+50, 250, 5, 6, wallColor));
		walls.add(new Wall(X+50, 450, 5, 6, wallColor));
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
		bullets.add(new Bullet(ship.x+ship.width, ship.y+i*ship.height/(numsOfGuns+1),8,4, speedBullets, bulletColor, 1));
		}
	}
	void aliensShoot(Alien a) {
		bulletsAliens.add(new Bullet(a.x+a.width, a.y+ship.height/2,8,4, speedBulletsAlien, (Color.ORANGE),-1));
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
		impactsShip();
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
				bulletsAliens.clear();
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
			impactBullets();
			impactsShip();
			wallImpacts();
			impactAlien();
			long timeNow = System.currentTimeMillis();
			long time = timeNow - TimeStart;
			if (time < 0 || time > ShootingRate) {
				TimeStart = timeNow;
				if(aliens.size()>0){
					int a = Math.abs(r.nextInt()) % (aliens.size());
					aliensShoot(aliens.get(a));
				}
			}
			moveAliens();
			moveBullets();
			rePaint();
			f.repaint();
			try {
				Thread.sleep(25);
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
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
			if (bullets.get(i).IsOutOfRange(f.WIDTH, f.HEIGHT)) {
				bullets.remove(i);
			}
		}
		for (int i = 0; i < bulletsAliens.size(); i++) {
			bulletsAliens.get(i).move();
			if (bulletsAliens.get(i).IsOutOfRange(f.WIDTH, f.HEIGHT)) {
				bulletsAliens.remove(i);
			}
		}
	}
//IMPACTS
	void wallImpacts() {
		for (int i = 0; i < walls.size(); i++) {
			
			if (bullets.size() > 0 && walls.get(i).bricks.size() > 0) {

				List<Bullet> foundBullets = new ArrayList<Bullet>();
				for (Bullet b : bullets) {
					for (Brick a : walls.get(i).bricks) {
						if ((a.x <= b.x + b.width )
								&& (a.y <= b.y+b.height && b.y <= a.y + a.height)) {
								walls.get(i).bricks.remove(a);
								foundBullets.add(b);
								break;
						}
					}
				}
				bullets.removeAll(foundBullets);
				foundBullets = null;
			}
			if (bulletsAliens.size() > 0 && walls.get(i).bricks.size() > 0) {

				List<Bullet> foundBullets = new ArrayList<Bullet>();
				for (Bullet b : bulletsAliens) {
					for (Brick a : walls.get(i).bricks) {
						if ((a.x >= b.x )&& (a.y <= b.y+b.height && b.y <= a.y + a.height)) {
							walls.get(i).bricks.remove(a);
							foundBullets.add(b);
							break;
						}
					}
				}
				bulletsAliens.removeAll(foundBullets);
				foundBullets = null;
			}
		}

	}

	void impactAlien() {
			List<Bullet> foundB = new ArrayList<Bullet>();
		for (Bullet b : bulletsAliens) {
			if (ship.x <= b.x  && b.x  <= ship.x + ship.width   && (ship.y <= b.y+b.height && b.y <= ship.y + ship.height)){
				foundB.add(b);
				lives--;
			}
		}
		bulletsAliens.removeAll(foundB);
		foundB = null;
	

	}
	void impactsShip() {
		if (bullets.size() > 0 && aliens.size() > 0) {

			List<Bullet> foundB = new ArrayList<Bullet>();
			for (Bullet b : bullets) {
				for (Alien a : aliens) {
					if ((a.x <= b.x + b.width && b.x + b.width <= a.x + a.width)
							&& (a.y <= b.y+b.height && b.y <= a.y + a.height)) {
						if (a.lives <= 1) {
							aliens.remove(a);
						} else {
							a.lives--;
						}
						foundB.add(b);
						score++;
						break;
					}
				}
			}
			bullets.removeAll(foundB);
			foundB = null;
		}

	}
void impactBullets() {
		if (bullets.size() > 0 && bulletsAliens.size() > 0) {
			List<Bullet> foundB = new ArrayList<Bullet>();
			for (Bullet b : bullets) {
				for (Bullet a : bulletsAliens) {
					if ((a.x >= b.x  && b.x + b.width+2 >= a.x )
							&& (a.y+a.height >= b.y && a.y <= b.y + b.height)) {
						foundB.add(b);
						bulletsAliens.remove(a);
						break;
					}
				}
			}
			bullets.removeAll(foundB);
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
		g.drawString("SPACE INVADERS", f.WIDTH/2-350, f.HEIGHT/2);
		//g.fillRect(Sx, Sy, SW, SH);
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
		g.drawString("SPACE INVADERS", f.WIDTH/2-350, f.HEIGHT/2-100);
		g.setColor(new Color(235, 223, 100));
		g.drawString("GAME OVER ", f.WIDTH/2-200, f.HEIGHT/2+200);
	}
	void rePaint() {
		g.setFont(f.smallFont);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(new Color(66, 233, 244));
		g.drawString("SPACE INVADERS", 550, 100);
		g.setColor(textColor);
		if (showInfo) {
			int x=f.WIDTH-150;
			g.drawString("SCORE: " + score, x, 400);
			g.drawString("LEVEL: " + (level), x, 450);
			g.drawString("LIVES : " + lives, x, 500);
		}

		ship.paint(g);
		for (Bullet bullet:bullets) {
			bullet.paint(g);
		}
		for (Bullet bullet:bulletsAliens) {
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
