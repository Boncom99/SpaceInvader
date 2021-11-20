import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class Finestra extends Frame implements ActionListener, KeyListener, WindowListener {

	Joc j;
	int AMPLADA=800,ALTURA=600;
	
	//Tecnica de double buffer
	Image im;
	Graphics g;
	public static void main(String[] args) {
		new Finestra();
	}
	
	Finestra() {
		super("Joc");
		setSize(AMPLADA,ALTURA);
		setVisible(true);
		im=this.createImage(AMPLADA, ALTURA);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		 System.exit(0); //tencar tot
        dispose(); // tencar una finestra
		
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
