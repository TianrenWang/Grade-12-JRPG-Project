/*********************************************************
*  Name: Frank T. Wang & Justin Arita                    *
*  Course: ICS 4U  Pd. 3                                 *
*  Assignment: Summative: MicroxMagic                    *
*  Purpose: A visual novel with interactive games where  *
*  the player battle against AI.                         *
*  Due Date: June 1, 2010                                *
*********************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
public class StartMenu extends MouseAdapter implements MouseMotionListener 
{
   Drawing draw = new Drawing();
	JFrame frame = new JFrame("Visual Novel");
	int x,y;
	ImageIcon play = new ImageIcon("play.png");
	ImageIcon instruction = new ImageIcon("instruction.png");
	ImageIcon playGlow = new ImageIcon("playGlow.png");
	ImageIcon instructionGlow = new ImageIcon("instructionGlow.png");
	ImageIcon title = new ImageIcon("title.png");
	
   public StartMenu()
   {  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
      frame.setSize(1025,725);  // set the size of the window to whatever width and height you like
      frame.add(draw); // put an object we can draw on in the centre of the window
		draw.addMouseMotionListener(this);
		draw.addMouseListener(this);
      frame.setVisible(true); //show the window
   }
 	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		draw.repaint();
	}	
	
	public void mouseClicked(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		if (x >= 430 && x <= 620 && y >= 500 && y <= 580)
		{
			frame.setVisible(false);
			new NewSkills();
			frame = null;
		}
		else if (x >= 330 && x <= 720 && y >= 590 && y <= 640)
			new Instruction();
	}
	
	class Drawing extends JComponent
   {
      public void paint(Graphics g)
      {
			System.gc();
			g.drawImage(new ImageIcon("background.jpg").getImage(),0,0,1025,725,this);
			g.drawImage(title.getImage(),286,36,454,454,this);
			if (x >= 430 && x <= 620 && y >= 500 && y <= 580)
				g.drawImage(playGlow.getImage(),357,438,295,163,this);
			else
				g.drawImage(play.getImage(),369,450,271,148,this);
			if (x >= 330 && x <= 720 && y >= 590 && y <= 640)
				g.drawImage(instructionGlow.getImage(),245,538,525,163,this);
			else
				g.drawImage(instruction.getImage(),257,550,502,148,this);
      }
   }
   
   public static void main(String[] args)
   {
      new StartMenu();
   }
} 