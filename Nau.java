import java.awt.Color;
import java.awt.Graphics;

public class Nau{
	int y;
    int x = 100;
    Color c;
	Nau(int y, Color c) {
		this.y=y;
        this.c=c;
	}
	void moure(int k) {
		y+=k;
	}
	void pintar(Graphics g) {
		g.setColor(c);
		g.drawRect(x, y, 30,20);
	}
}
