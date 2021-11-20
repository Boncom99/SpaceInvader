import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Joc {
	//int y=50;
	Graphics g;
	Color bulletColor;
	Finestra f;
	Aliens c[];
	Nau nau;
	ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	Random r=new Random();
	Joc(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void move(int k) {
		nau.moure(k);
	}

	void deleteBullet( int i) {
		bullet.remove(i);
	}

	void deleteAliens(Aliens a) {
		a = null;
	}
	void shoot() {

			bullet.add(new Bullet(nau.x, nau.y, 10, bulletColor));
			System.out.println("create bullet"+bullet.size());
	}

	int IsOutOfRange(int x, int y) {
		if (x < 0 || x > f.AMPLADA || y < 0 || y > f.ALTURA) {
			return 1;
		}
			return 0;

	}
	void run() {
		incialitzacio();
		while (true) {
			movimentsAliens();
			movimentsBullets();
			xocs();
			repintar();
			
			f.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void incialitzacio() {
		bulletColor = new Color(255, 100, 0);
		c=new Aliens[4];
		nau = new Nau(f.ALTURA / 2,15, Color.BLUE);
		for(int i=0;i<c.length;i++) {
			c[i]=new Aliens(600,Math.abs(r.nextInt())%f.ALTURA,3);
		}
	}
	void movimentsAliens() {
		for(int i=0;i<c.length;i++){
			c[i].moure();
			if (IsOutOfRange(c[i].x, c[i].y)==1) {
				deleteAliens(c[i]);
			}
		}
	}
	void movimentsBullets() {
		for(int i=0;i<bullet.size();i++){
			bullet.get(i).moure();
			if (IsOutOfRange(bullet.get(i).x, bullet.get(i).y)==1) {
				deleteBullet(i);
			}
		}
	}
	void xocs() {
		
	}
	void repintar() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, f.AMPLADA, f.ALTURA);
		g.setColor(Color.BLACK);
		g.drawString("hola", 50, 550);
		nau.pintar(g);
		for(int i=0;i<bullet.size();i++)
			bullet.get(i).pintar(g);
		for(int i=0;i<c.length;i++)
			c[i].pinta(g);
	}
}
