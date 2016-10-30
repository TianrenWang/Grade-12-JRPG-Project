import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

//All the stuffs needed to play sound
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

//This class displays the visual novel characters, backgound, and dialogue
public class Dialogue extends MouseAdapter implements MouseMotionListener
{
   Drawing draw = new Drawing();
	JFrame frame = new JFrame("Dialogue");
	Dialogue dialogueScreen;
   int x,y,xClicked,yClicked;
	ImageIcon dialogueBox = new ImageIcon("dialogueBox2.png");
	ImageIcon background;
	TextFile dialogue = new TextFile();
	String [] lines = new String[6];
	String [] previousLines = new String[6];
	Battle battle;
	int battleNumber;
	Click mouseClick = new Click();
	boolean battleFinished = false;
	
	//Sound playing
	private static final int	EXTERNAL_BUFFER_SIZE = 128000;
	Music backgroundMusic;
	SoundEffect effect;

	//Character Pictures
	ImageIcon [] character = new ImageIcon[5];
	int quentynX = -500;
	int quentynY = 100;
	int latiaX = 1100;
	int latiaY = 100;
	int moveX = 0;
	
	//The activity of different types of visual effects
	Animation animation = new Animation();
	boolean glowAnim = false;
	boolean dimAnim = false;
	boolean fadeOutAnim = false;
	boolean fadeInAnim = false;
	boolean dark = false;
	boolean bright = false;
	boolean slide = false;
	boolean battleStart = false;
	boolean shake = false;
	int mover = -1;
	int shakeDistance = 0;
	boolean effectActivated = false;
	boolean quentynSlide = false;
	boolean latiaSlide = false;
	boolean dracoSlide = false;
	boolean raphaelSlide = false;
	boolean diannaSlide = false;
	boolean end = false;
	
	//Battle Start Continuation
	ImageIcon battleStart1 = new ImageIcon("battleStart.png");
	ImageIcon battleStart2 = new ImageIcon("battleStartCon.png");
	
   public Dialogue()
   {
		//Character Pictures
		character[0] = new ImageIcon("quentynNormal.png");
		character[1] = new ImageIcon("latiaNormal.png");;
		dialogue.open("Visual Novel.txt","r");
		//Reads the first 6 lines of the textfile
		for (int i = 0; i < 6; i++)
		{
			lines[i] = dialogue.read();
			if (lines[i].equals("fadeIn"))
			{
				fadeInAnim = true;
				animation = new Animation();
				animation.start();
				lines[i] = "";
			}
			else if (lines[i].equals("fadeOut"))
			{
				fadeOutAnim = true;
				animation = new Animation();
				animation.start();
				lines[i] = "";
			}
			else if (lines[i].equals("music=nothing"))
			{
				backgroundMusic = null;
				lines[i] = "";
			}
			else if (lines[i].length() > 5 && lines[i].substring(0,6).equals("music="))
			{
				backgroundMusic = new Music(lines[i].substring(6,lines[i].length()));
				backgroundMusic.start();
				lines[i] = "";
			}
			else if (lines[i].length() > 11 && lines[i].substring(0,11).equals("background="))
			{
				background = new ImageIcon(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
				lines[i] = "";
			}
		}
		
		//Declarations Mandatory
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program when you close it
      frame.setSize(1025,725);
      frame.add(draw); // put an object we can draw on in the centre of the window
      draw.addMouseListener(this);
		draw.addMouseMotionListener(this);
      frame.setVisible(true); //show the window
      frame.setResizable(false);
   }
   
	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		draw.repaint();
	}
	
	public void mouseDragged(MouseEvent e)
	{
	}
	
	//This method reads the dialogue and make appropriate animations, or change facial expressions or background
	public void readDialogue()
	{
		do
		{
			previousLines[0] = lines[0];
			previousLines[1] = lines[1];
			previousLines[2] = lines[2];
			previousLines[3] = lines[3];
			previousLines[4] = lines[4];
			previousLines[5] = lines[5];
			effectActivated = false;
			if (animation.time == 0)
			{
				for (int i = 0; i < 6; i++)
				{
					lines[i] = dialogue.read();
					//Fade In Effect
					if (lines[i].equals("fadeIn"))
					{
						fadeInAnim = true;
						animation = new Animation();
						animation.start();
						lines[i] = "";
					}
					//Fade Out Effect
					else if (lines[i].equals("fadeOut"))
					{
						fadeOutAnim = true;
						animation = new Animation();
						animation.start();
						lines[i] = "";
					}
					//Glow Effect
					else if (lines[i].equals("glow"))
					{
						glowAnim = true;
						animation = new Animation();
						animation.start();
						lines[i] = "";
					}
					//Dim Effect
					else if (lines[i].equals("dim"))
					{
						dimAnim = true;
						animation = new Animation();
						animation.start();
						lines[i] = "";
					}
					//Turns off music
					else if (lines[i].equals("music=nothing"))
					{
						backgroundMusic.line.close();
						backgroundMusic.interrupt();
						backgroundMusic = null;
						lines[i] = "";
					}
					//Shakes the screen
					else if (lines[i].equals("shake"))
					{
						shake = true;
						animation = new Animation();
						animation.start();
						lines[i] = "";
					}
					//Shows the Ending
					else if (lines[i].equals("The End"))
					{
						end = true;
						lines[i] = "";
					}
					//Changes music to something
					else if (lines[i].length() > 6 && lines[i].substring(0,6).equals("music="))
					{
						if (backgroundMusic != null)
						{
							backgroundMusic.line.close();
							backgroundMusic.interrupt();
							backgroundMusic = null;
							backgroundMusic = new Music(lines[i].substring(6,lines[i].length()));
							backgroundMusic.start();
						}
						else 
						{
							backgroundMusic = new Music(lines[i].substring(6,lines[i].length()));
							backgroundMusic.start();
						}
						lines[i] = "";
					}
					//Activates a sound effect
					else if (lines[i].length() > 7 && lines[i].substring(0,7).equals("effect="))
					{
						if (effect != null)
						{
							effect.line.close();
							effect = null;
							effect = new SoundEffect(lines[i].substring(7,lines[i].length()));
							effect.play();
							effectActivated = true;
						}
						else 
						{
							effect = new SoundEffect(lines[i].substring(7,lines[i].length()));
							effect.play();
							effectActivated = true;
						}
						lines[i] = "";
					}
					//Slides the character
					else if (lines[i].length() > 12 && lines[i].substring(0,5).equals("slide"))
					{
						//Slide Quentyn
						if (lines[i].substring(5,12).equals("quentyn"))
						{
							moveX = Integer.parseInt(lines[i].substring(12,lines[i].length())) ;
							mover = 0;
							slide = true;
							quentynSlide = true;
							animation = new Animation();
							animation.start();
						}	
						//Slide Latia
						else if (lines[i].substring(5,10).equals("latia"))
						{
							moveX = Integer.parseInt(lines[i].substring(10,lines[i].length())) ;
							mover = 1;
							slide = true;
							latiaSlide = true;
							animation = new Animation();
							animation.start();
						}		
					}
					//Changes Quentyn's Facial Expression
					else if (lines[i].length() > 8 && lines[i].substring(0,8).equals("quentyn="))
					{
						character[0] = new ImageIcon(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
						lines[i] = "";
					}
					//Changes Latia's Facial Expression
					else if (lines[i].length() > 6 && lines[i].substring(0,6).equals("latia="))
					{
						character[1] = new ImageIcon(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
						lines[i] = "";
					}
					//Sets Quentyn's X coordinate location
					else if (lines[i].length() > 8 && lines[i].substring(0,9).equals("quentynX="))
					{
						quentynX = Integer.parseInt(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
						lines[i] = "";
					}
					//Sets Latia's X coordinate location
					else if (lines[i].length() > 6 && lines[i].substring(0,7).equals("latiaX="))
					{
						latiaX = Integer.parseInt(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
						lines[i] = "";
					}
					//Sets the background
					else if (lines[i].length() > 11 && lines[i].substring(0,11).equals("background="))
					{
						background = null;
						background = new ImageIcon(lines[i].substring(lines[i].indexOf("=")+1,lines[i].length()));
						lines[i] = "";
					}
					//Initiates battle
					else if (lines[i].length() == 7 && lines[i].substring(0,6).equals("battle"))
					{
						if (backgroundMusic != null)
						{
							backgroundMusic.line.close();
							backgroundMusic.interrupt();
							backgroundMusic = null;
						}
						battleNumber = Integer.parseInt(lines[i].substring(6,7));
						battleStart = true;
						lines[i] = "";
					}
				}
				//If any of these effects are active, the dialogue wouldn't show the technical part of the dialogue like "quentyn=whatever.png"
				if (slide || fadeOutAnim || glowAnim || dimAnim || shake)
				{
					lines[0] = previousLines[0];
					lines[1] = previousLines[1];
					lines[2] = previousLines[2];
					lines[3] = previousLines[3];
					lines[4] = previousLines[4];
					lines[5] = previousLines[5];
				}
			}
		}while ((lines[0].equals("") && lines[1].equals("") && lines[2].equals("") && lines[3].equals("") && lines[4].equals("") && lines[5].equals("")) && !slide && !fadeOutAnim && !fadeInAnim && !dimAnim && !glowAnim && !battleStart && !effectActivated && !end);
		draw.repaint();
	}
	
   public void mouseClicked (MouseEvent e)
   {
		//Mouse click must be monitored because if it is not, then there will be problem with loading music
		if (mouseClick.cooled && !shake && !end)
		{
			mouseClick = new Click();
			mouseClick.start();
			mouseClick.cooled = false;
			//The battle initiation can only happen if the battle initiation box is shown
			if (battleStart)
			{
				xClicked = e.getX();
				yClicked = e.getY();
			}
			//If battle is not initiated, then the player will continue with the dialogue
			if (!battleStart)
				readDialogue();
			//This starts the battle after it is initiated
			if (battleStart)
			{
				if (xClicked >= 424 && xClicked <= 601 && yClicked >= 380 && yClicked <= 415)
				{
					battleStart = false;
					frame.setVisible(false);
	            battle = new Battle(battleNumber,this);
					xClicked = 0;
					yClicked = 0;
				}
			}
		}
   }

   class Drawing extends JComponent
   {	
      public void paint(Graphics g)
      {	
			//Screen Shaking Effeect
			if (shake)
			{
				shakeDistance = (int)(Math.random()*100-50);
				if (animation.time >= 600)
				{
					shake = false;
					animation.interrupt();
					animation = new Animation();
					shakeDistance = 0;
				}
			}
			//MOnitor mouse click speed
			if (mouseClick.time > 100)
			{
				mouseClick.interrupt();
				mouseClick = new Click();
				mouseClick.cooled = true;
			}
			//If a fight is finished, then the dialogue continues
			if (battleFinished)
			{
				battle.frame.setVisible(false);
				battle.frame=null;
				frame.setVisible(true);
				fadeInAnim = true;
				animation = new Animation();
				animation.start();
				battleFinished = false;
			}
			//draws background
			g.drawImage(background.getImage(),0 + shakeDistance,0 + shakeDistance,1025,769,this);
			//sliding effeect
			if (slide)
			{
				if (animation.time < 300)
				{
					if (mover == 0)
						g.drawImage(character[0].getImage(),quentynX+(int)(moveX/300.0*animation.time), 50, 296,670,this);
					if (mover == 1)
						g.drawImage(character[1].getImage(),latiaX+(int)(moveX/300.0*animation.time), 200, 382,808,this);
				}
				else if (animation.time >= 300)
				{
					slide = false;
					animation.interrupt();
					animation = new Animation();
					if (mover == 0)
						quentynX += moveX;
					else if (mover == 1)
						latiaX += moveX;
					mover = -1;
					moveX = 0;
				}
			}
			//Draws the characters
			for (int i = 0; i < 5; i++)
				if (!dark && !bright)
				{
					if (mover != 0)
						g.drawImage(character[0].getImage(),quentynX + shakeDistance,50+ shakeDistance,296,670,this);
					if (mover != 1)
						g.drawImage(character[1].getImage(),latiaX + shakeDistance,200 + shakeDistance,382,808,this);
				}
			//Glowing effect
			if (glowAnim)
			{
				if (animation.time < 100)
					g.drawImage(new ImageIcon("glow10%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 150)
					g.drawImage(new ImageIcon("glow20%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 200)
					g.drawImage(new ImageIcon("glow30%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 250)
					g.drawImage(new ImageIcon("glow40%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 300)
					g.drawImage(new ImageIcon("glow50%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 350)
					g.drawImage(new ImageIcon("glow60%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 400)
					g.drawImage(new ImageIcon("glow70%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 450)
					g.drawImage(new ImageIcon("glow80%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 500)
					g.drawImage(new ImageIcon("glow90%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 550)
					g.drawImage(new ImageIcon("glow100%.png").getImage(),0,0,1046,737,this);
				else if (animation.time >= 550)
				{
					animation.interrupt();
					animation = new Animation();
					glowAnim = false;
					bright = true;
					background=null;
					background = new ImageIcon("glow100%.png");
					g.drawImage(background.getImage(),0,0,1025,725,this);
					quentynX = -500;
					latiaX = 1100;
					System.gc();
				}
			}
			//Dimming effect
			else if (dimAnim)
			{
				if (animation.time < 50)
					g.drawImage(new ImageIcon("glow100%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 100)
					g.drawImage(new ImageIcon("glow90%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 150)
					g.drawImage(new ImageIcon("glow80%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 200)
					g.drawImage(new ImageIcon("glow70%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 250)
					g.drawImage(new ImageIcon("glow60%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 300)
					g.drawImage(new ImageIcon("glow50%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 350)
					g.drawImage(new ImageIcon("glow40%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 400)
					g.drawImage(new ImageIcon("glow30%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 450)
					g.drawImage(new ImageIcon("glow20%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 500)
					g.drawImage(new ImageIcon("glow10%.png").getImage(),0,0,1046,737,this);
				else if (animation.time >= 550)
				{
					dimAnim = false;
					animation.interrupt();
					animation = new Animation();
					bright = false;
					readDialogue();
					System.gc();
					repaint();
				}
			}
			//Shows the dialogue box and the text
			if (!fadeInAnim && !dark)
			{
				g.drawImage(dialogueBox.getImage(),0,450,1044,276,this);
				g.setColor(Color.white);
				g.setFont(new Font("Arial",Font.ITALIC,19));
				if (lines[0].indexOf(':') >= 0)
				{
					g.drawString(""+lines[0].substring(0,lines[0].length() -1),20,500);
					g.drawString(""+lines[1],20,585);
					g.drawString(""+lines[2],20,625);
					g.drawString(""+lines[3],20,665);
				}
				else
				{
					g.drawString(""+lines[0],20,585);
					g.drawString(""+lines[1],20,625);
					g.drawString(""+lines[2],20,665);
				}
			}
			//Fade Out Effect
			if (fadeOutAnim)
			{
				if (animation.time < 100)
					g.drawImage(new ImageIcon("10%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 150)
					g.drawImage(new ImageIcon("20%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 200)
					g.drawImage(new ImageIcon("30%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 250)
					g.drawImage(new ImageIcon("40%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 300)
					g.drawImage(new ImageIcon("50%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 350)
					g.drawImage(new ImageIcon("60%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 400)
					g.drawImage(new ImageIcon("70%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 450)
					g.drawImage(new ImageIcon("80%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 500)
					g.drawImage(new ImageIcon("90%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 550)
					g.drawImage(new ImageIcon("100%.png").getImage(),0,0,1046,737,this);
				else if (animation.time >= 550)
				{
					animation.interrupt();
					animation = new Animation();
					fadeOutAnim = false;
					dark = true;
					background=null;
					background = new ImageIcon("100%.png");
					g.drawImage(background.getImage(),0,0,1025,725,this);
					quentynX = -500;
					latiaX = 1100;
					System.gc();
				}
			}
			//Fade In Effect
			else if (fadeInAnim)
			{
				if (animation.time < 50)
					g.drawImage(new ImageIcon("100%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 100)
					g.drawImage(new ImageIcon("90%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 150)
					g.drawImage(new ImageIcon("80%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 200)
					g.drawImage(new ImageIcon("70%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 250)
					g.drawImage(new ImageIcon("60%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 300)
					g.drawImage(new ImageIcon("50%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 350)
					g.drawImage(new ImageIcon("40%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 400)
					g.drawImage(new ImageIcon("30%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 450)
					g.drawImage(new ImageIcon("20%.png").getImage(),0,0,1046,737,this);
				else if (animation.time < 500)
					g.drawImage(new ImageIcon("10%.png").getImage(),0,0,1046,737,this);
				else if (animation.time >= 550)
				{
					fadeInAnim = false;
					animation.interrupt();
					animation = new Animation();
					dark = false;
					readDialogue();
					System.gc();
					repaint();
				}
			}
			//When the battle is about to start, it shows a box start box for the player to click on
			if (battleStart)
			{
				if (x >= 424 && x <= 601 && y >= 380 && y <= 415)
					g.drawImage(battleStart2.getImage(),373,292,279,142,this);
				else
					g.drawImage(battleStart1.getImage(),373,292,279,142,this);
			}
			//Shows "The End"
			if (end)
				g.drawImage(new ImageIcon("end.png").getImage(),188,293,649,139,this);
		}
   }
	//Animation controls the time
 	class Animation extends Thread
   {
		int time;
		public Animation()
		{
			time = 0;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					time ++;
					draw.repaint();
					sleep(1);
            }
         } catch (InterruptedException e)
            {};
      }
   } 
	//Used for controlling mouse clicking speed
	class Click extends Thread
   {
		int time;
		boolean cooled;
		public Click()
		{
			time = 0;
			cooled = true;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					time ++;
					draw.repaint();
					sleep(1);
            }
         } catch (InterruptedException e)
            {};
      }
   }
	
	//Sound Effects
	class SoundEffect
   {
		AudioInputStream	audioInputStream;
		File soundFile;
		SourceDataLine	line;
		public SoundEffect(String name)
		{
			audioInputStream = null;
			soundFile = new File(name);
			line = null;
		}
		public void play()
      {
			try
			{
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			AudioFormat	audioFormat = audioInputStream.getFormat();
			//SourceDataLine	line = null;
			DataLine.Info	info = new DataLine.Info(SourceDataLine.class,
													 audioFormat);
			try
			{
				line = (SourceDataLine) AudioSystem.getLine(info);
				line.open(audioFormat);
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
			line.start();
			int	nBytesRead = 0;
			byte[]	abData = new byte[EXTERNAL_BUFFER_SIZE];
			while (nBytesRead != -1)
			{
				try
				{
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				if (nBytesRead >= 0)
				{
					int	nBytesWritten = line.write(abData, 0, nBytesRead);
				}
			}
			line.drain();
			line.close();
      }
   }
	
	//Background Music
	class Music extends Thread
   {
		AudioInputStream	audioInputStream;
		File soundFile;
		SourceDataLine	line;
		public Music(String name)
		{
			audioInputStream = null;
			soundFile = new File(name);
			line = null;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					try
					{
						audioInputStream = AudioSystem.getAudioInputStream(soundFile);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.exit(1);
					}
					AudioFormat	audioFormat = audioInputStream.getFormat();
					//SourceDataLine	line = null;
					DataLine.Info	info = new DataLine.Info(SourceDataLine.class,
															 audioFormat);
					try
					{
						line = (SourceDataLine) AudioSystem.getLine(info);
						line.open(audioFormat);
					}
					catch (LineUnavailableException e)
					{
						e.printStackTrace();
						System.exit(1);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.exit(1);
					}
					line.start();
					int	nBytesRead = 0;
					byte[]	abData = new byte[EXTERNAL_BUFFER_SIZE];
					while (nBytesRead != -1)
					{
						try
						{
							nBytesRead = audioInputStream.read(abData, 0, abData.length);
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						if (nBytesRead >= 0)
						{
							int	nBytesWritten = line.write(abData, 0, nBytesRead);
						}
					}
					line.drain();
					line.close();
               sleep(3000);
            }
         } catch (InterruptedException e)
            {};
      }
   }
	
   public static void main(String[] args)
   {
      new Dialogue();
   }
}

