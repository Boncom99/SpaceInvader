import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Finestra extends Frame implements KeyListener, WindowListener {
	Joc j;

	int WIDTH=1000,HEIGHT=800;
	
	//T�cnica de double buffer
	Image im;
	Graphics g;
	public static void main(String[] args) {
		new Finestra();
	}
	
	Finestra() {
		super("Joc");
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		this.addKeyListener(this);
		addWindowListener(this);
		im=this.createImage(WIDTH, HEIGHT);
		g=im.getGraphics();
		j=new Joc(this);
		j.run();
	}
	public void update(Graphics g) {	//millora la sincronia del pintat
		paint(g);
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,null);
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		//Per a saber quina tecla et pitjen, la variable 'e' rebuda cont� la tecla.
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
	if (e.getKeyCode()==KeyEvent.VK_UP) {
			System.out.println("up");
			j.move(-1);
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			System.out.println("down");
			j.move(1);
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("space");
			j.shoot();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			System.out.println("up");
			j.move(-1);
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			System.out.println("down");
			j.move(1);
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			System.out.println("space");
			j.shoot();
		}
	}
		@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	  System.exit(0);// tencar tot
       // dispose(); // tencar una finestra	
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
