package Snake;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Snake {
 
	public static void main(String args[]) {
		JFrame frame =new JFrame();
		frame.setBounds(10, 10, 900, 620);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MPanel mp =new MPanel();
		frame.setContentPane(mp);
		
		frame.setVisible(true);
		
	}
}
