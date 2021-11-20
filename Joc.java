import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Joc {
	int X=70;
	Graphics g;
	Color bulletColor;
	Color nauColor;
	Color aliensColor;
	Finestra f;
	ArrayList<Aliens> aliens= new ArrayList<Aliens>();
	Nau nau;
	ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	Random r=new Random();
	int lvl=3;
	Joc(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void createAlines() {

	}
	void move(int k) {
		nau.moveNau(k);
		if (nau.IsOutOfRange(f.WIDTH, f.HEIGHT) ) {
		nau.moveNau(-k);
		}
	}

	void GenerateAliens() {
		if ((Math.abs(r.nextInt())% 10) >= 8.5) {
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

			bullet.add(new Bullet(nau.x+nau.width, nau.y+nau.height/2,10,3, 10, bulletColor));
			//System.out.println("create bullet"+bullet.size());
	}


	void run() {
		incialitzacio();
		while (true) {
			GenerateAliens();
			movimentsAliens();
			movimentsBullets();
			xocs();
			repintar();
			
			f.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void incialitzacio() {
		bulletColor = new Color(255, 100, 0);
		nauColor = new Color(0, 50, 255);
		aliensColor = new Color(0, 255, 10);
		bulletColor = new Color(255, 100, 0);
		nau = new Nau(X,f.HEIGHT / 2,40,40,15, nauColor);
		for(int i=0;i<10;i++) {
			aliens.add(new Aliens(f.WIDTH,Math.abs(r.nextInt())%f.HEIGHT,40,40, 3, aliensColor));
		}
	}
	void movimentsAliens() {
		for(int i=0;i<aliens.size();i++){
			aliens.get(i).move();
			if (aliens.get(i).IsOutOfRange(f.WIDTH,f.HEIGHT)) {
				deleteAliens(i);
			}
		}
	}
	void movimentsBullets() {
		for(int i=0;i<bullet.size();i++){
			bullet.get(i).move();
			if (bullet.get(i).IsOutOfRange(f.WIDTH, f.HEIGHT)) {
				deleteBullet(i);
			}
		}
	}
	void xocs() {
		
	}
	void repintar() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, f.WIDTH, f.HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString("hola", 50, 550);
		nau.paint(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).paint(g);
		for(int i=0;i<aliens.size();i++)
			aliens.get(i).paint(g);
	}
}
