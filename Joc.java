import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Joc {
	//int y=50;
	Graphics g;
	Color bulletColor;
	Finestra f;
	Aliens c[];
	Nau nau;
	Bullet bullet;
	Random r=new Random();
	Joc(Finestra f) {
		this.f=f;
		this.g=f.g;
	}

	void move(int k) {
		nau.moure(k);
	}

	void shoot() {
		//bullet = new Bullet(nau.x, nau.y, 40, bulletColor);
		//bullet.moure(30);
	
}
	void run() {
		incialitzacio();
		while (true) {
			movimentsAliens();
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
		bullet = new Bullet(20, 20, 40, bulletColor);
		c=new Aliens[4];
		nau = new Nau(f.ALTURA / 2,15, Color.BLUE);
		for(int i=0;i<c.length;i++) {
			c[i]=new Aliens(600,50+i*50,3);
		}
	}
	void movimentsAliens() {
		for(int i=0;i<c.length;i++)
			c[i].moure();
	}
	void xocs() {
		
	}
	void repintar() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, f.AMPLADA, f.ALTURA);
		g.setColor(Color.BLACK);
		g.drawString("hola", 50, 550);
		nau.pintar(g);
		for(int i=0;i<c.length;i++)
			c[i].pinta(g);
	}
}
