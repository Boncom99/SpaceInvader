import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Finestra extends Frame implements KeyListener, WindowListener {
	Game game;

	int WIDTH=1600,HEIGHT=800;
    List<Integer> keys=new ArrayList<Integer>();	
	//T�cnica de double buffer
	Image im;
	Graphics g;
	Font MainFont;
	Font smallFont;
	Font BigFont;

	public static void main(String[] args) {
		new Finestra();
	}
	
	Finestra() {
		super("Space Invader");
		try{

		MainFont = Font.createFont(Font.TRUETYPE_FONT, new File ("ARCADE.TTF"));
		 smallFont=MainFont.deriveFont(20f);
		 BigFont=MainFont.deriveFont(100f);
		}
		catch (IOException|FontFormatException e) {
     // Handle exception
}

		setSize(WIDTH,HEIGHT);
		setVisible(true);
		this.addKeyListener(this);
		addWindowListener(this);
		im=this.createImage(WIDTH, HEIGHT);
		g=im.getGraphics();
		game=new Game(this);
		game.run();
	}

	public void update(Graphics g) {
		if(keys.contains(KeyEvent.VK_DOWN)){
			game.move(1);
		}
		if (keys.contains(KeyEvent.VK_UP)) {
			game.move(-1);
		}
		if(keys.contains(KeyEvent.VK_SPACE)){
			game.shoot();
		}
		paint(g);
	}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,null);
	}

	

	@Override
	public void keyReleased(KeyEvent e) {
		//Per a saber quina tecla et pitjen, la variable 'e' rebuda cont� la tecla.
 	if(keys.contains(e.getKeyCode())){
        keys.remove(keys.indexOf(e.getKeyCode()));
    }	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if(!keys.contains(e.getKeyCode())){
        keys.add(e.getKeyCode());
    }
	}
	@Override
	public void keyTyped(KeyEvent e) {
	
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
