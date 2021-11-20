import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Joc {
	//int y=50;
	Graphics g;
	Finestra f;
	Cotxe c[];
	Nau nau;
	Random r=new Random();
	Joc(Finestra f) {
		this.f=f;
		this.g=f.g;
	}
	
	void run() {
		incialitzacio();
		while (true) {
			moviments();
			xocs();
			repintar();
			
			f.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void incialitzacio() {
		c=new Cotxe[4];
		nau = new Nau(f.ALTURA / 2, Color.BLUE);
		for(int i=0;i<c.length;i++) {
			c[i]=new Cotxe(600,50+i*50,Math.abs(r.nextInt())%10+1);
		}
	}
	void moviments() {
		for(int i=0;i<c.length;i++)
			c[i].moure();
	}
	void xocs() {
		
	}
	void repintar() {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, f.AMPLADA, f.ALTURA);
		nau.pintar(g);
		for(int i=0;i<c.length;i++)
			c[i].pinta(g);
	}
}
