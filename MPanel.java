package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MPanel extends JPanel implements KeyListener ,ActionListener{
	ImageIcon title = new ImageIcon("title.png");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon food = new ImageIcon("food.png");
	
	int len=3;
	int score=0;
	int[] snakex= new int[750];
	int[] snakey =new int[750];
	int foodx;
	int foody;
	Random rand = new Random();
	String fx="R"; //方向:R.L.U.D
	boolean isStarted = false;
	boolean isFailed = false;
	
	Timer timerSlow =new Timer(119,this);
	Timer timerNormal =new Timer(89,this);
	Timer timerFast =new Timer(70,this);
	
	
	public MPanel() {
		initSnake();
		this.setFocusable(true);
		this.addKeyListener(this);
		
	}
	
	public void paintComponent(Graphics g) {
     	super.paintComponent(g);
     	this.setBackground(Color.WHITE);
     	title.paintIcon(this, g, 25, 11);
     	g.fillRect(25, 75, 850, 500);
     	
     	switch (fx) {
     	case "R":
     		right.paintIcon(this, g, snakex[0], snakey[0]);
     		break;
     	case "L":
     		left.paintIcon(this, g, snakex[0], snakey[0]);
     		break;
     	case "U":
     		up.paintIcon(this, g, snakex[0], snakey[0]);
     		break;
     	case "D":
     		down.paintIcon(this, g, snakex[0], snakey[0]);
     		break;
     	}
     	
     	for(int i=1;i<len;i++) {
     		body.paintIcon(this, g, snakex[i], snakey[i]);
     	}
     	
     	if(isStarted == false) {
     		g.setColor(Color.white);
         	g.setFont(new Font("arial",Font.BOLD,40));
     		g.drawString("Press space to start", 300, 300);
     	}
     	food.paintIcon(this, g, foodx, foody);
     	if(isFailed == true) {
     		g.setColor(Color.RED);
     		g.setFont(new Font("arial",Font.BOLD,40));
     		g.drawString("Failed:press space to restart",200,300);
     	}
     	g.setColor(Color.BLACK);
     	g.setFont(new Font("arial",Font.BOLD,15));
     	g.drawString("Length: "+len, 750, 35);
     	g.drawString("Score: "+score, 750, 60);
     	
     }
	public void initSnake() {
		len=3;
		snakex[0]=100;
		snakey[0]=100;
		snakex[1]=75;
		snakey[1]=100;
		snakex[2]=50;
		snakey[2]=100;
		foodx=25+25*rand.nextInt(34);
		foody=75+25*rand.nextInt(19);
		fx="R";
		
		timerSlow.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode) {
		case KeyEvent.VK_SPACE:
			if(isFailed==false) {
			isStarted=!isStarted;
			}else {
			isFailed=!isFailed;
			isStarted=true;
			initSnake();
			}
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			if(fx=="L") {
				
			}else
			  fx="R";
			break;
		case KeyEvent.VK_LEFT:
			if(fx=="R") {
				
			}else
			  fx="L";
			break;
		case KeyEvent.VK_UP:
			if(fx=="D") {
				
			}else
			 fx="U";
			break;
		case KeyEvent.VK_DOWN:
			if(fx=="U") {
				
			}else
			  fx="D";
			break;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	if(isStarted && !isFailed) {
      for(int i=len-1;i>0;i--) {
    	  
    	   snakex[i]=snakex[i-1];
    	   snakey[i]=snakey[i-1];
       }
      switch(fx) {
      case "R":
    	  snakex[0]=snakex[0]+25;
  	    if(snakex[0]>850) {
  	       snakex[0]=25;}
  	      break;
      case "L":
    	  snakex[0]-=25;
    	  if(snakex[0]<25)
    		  snakex[0]=850;
    	  break;
      case "U":
    	  snakey[0]-=25;
    	  if(snakey[0]<75)
    		  snakey[0]=550;
    	  break;
      case "D":
    	  snakey[0]+=25;
    	  if(snakey[0]>550)
    		  snakey[0]=75;
    	  break;
    
      }
      if(snakex[0] == foodx && snakey[0] == foody) {
    	  len++;
    	  score+=10;
    	  foodx=25+25*rand.nextInt(34);
  		  foody=75+25*rand.nextInt(19);
  		
      }
      for(int i=1;i<len;i++) {
    	  if(snakex[i]==snakex[0] && snakey[i]==snakey[0]) {
    		  isFailed=!isFailed;
    		  if(score>70 && score<=160)
    			  timerNormal.stop();
    		  else if(score>160)
    			  timerFast.stop();
    		  else 
    			  timerSlow.stop();
    		  score=0;
    		  
    	  }
      }
	}
	  
		repaint();
		if(score>70 && score<=160) {
			timerSlow.stop();
			 
			 timerNormal.start();
		 }else if(score>160) {
			 timerNormal.stop();
			 
			 timerFast.start();
		 }else
			 timerSlow.start();
	 
	    
	}
	
	
	
}
        
