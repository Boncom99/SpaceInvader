import java.awt.Color;
import java.awt.Graphics;

public class Nau{
	int y;
    int x = 100;
	int speed;
    Color c;
	Nau(int y,int speed, Color c) {
		this.y=y;
		this.speed = speed;
        this.c=c;

	}
	void moure(int k) {
		y+=k*speed;
	}
	void pintar(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, 30,20);
	}
}
