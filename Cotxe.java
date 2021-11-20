import java.awt.Color;
import java.awt.Graphics;

public class Cotxe {
	int x,y,v;
	Cotxe(int x,int y,int v) {
		this.x=x;this.y=y;this.v=v;
	}
	void moure() {
		x-=v;
	}
	void pinta(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(x, y, 30,20);
	}
}
