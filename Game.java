import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	int X=70;
	Graphics g;
	Color bulletColor;
	Color textColor= new Color(255,255 , 255);
	Color backgroundColor= new Color(0,0 , 0);
	Color groundColor= new Color(147, 81, 22);
	Color shipColor;
	Color aliensColor;
	Finestra f;
	ArrayList<Aliens> aliens= new ArrayList<Aliens>();
	Ship ship;
	ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	Random r=new Random();
	int lvl=1;
	int lives=5;
	Game(Finestra f) {
		this.f=f;
		this.g=f.g;
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
			for (int i = 0; i < lvl; i++) {
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

			bullet.add(new Bullet(ship.x+ship.width, ship.y+ship.height/2,10,3, 10, bulletColor));
			//System.out.println("create bullet"+bullet.size());
	}


	void run() {
		initialize();
		while (!gameOver()) {
			GenerateAliens();
			impacts();
			moveAliens();
			moveBullets();
			rePaint();
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

			for (int b = 0; b < bullet.size(); b++) {
				for (int a = 0; a < aliens.size();a++) {
					Bullet B=bullet.get(b);
					Aliens A=aliens.get(a);
					if ((A.x<= B.x+B.width&& B.x+B.width<= A.x+ A.width) && (A.y<=B.y &&B.y <=A.y+A.height) ) {
					System.out.println(aliens.size() + " delete alien "+a);
						deleteAliens(a);
					System.out.println(bullet.size() + " delete bullet"+b);
						deleteBullet(b);
					}
			}
		}
		}
		
	}

	void rePaint() {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(groundColor);
		g.fillRect(0, 0, X-10, f.HEIGHT);
		g.setColor(textColor);
		g.drawString("Level: "+ lvl, 20, 550);
		g.drawString("❤️: "+lives, 20, 520);
		g.drawString("bullets: " + bullet.size(), 20, 600);
		ship.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paint(g);
	}
}
