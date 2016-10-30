import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

//Shows the instruction
public class Instruction
{
   Drawing draw = new Drawing();
   ImageIcon background = new ImageIcon("background.jpg");
   public Instruction()
   {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
      frame.setSize(1025,725);  // set the size of the window to whatever width and height you like
      frame.add(draw); // put an object we can draw on in the centre of the window
      frame.setVisible(true); //show the window
		frame.setResizable(false);
   }
   
   class Drawing extends JComponent
   {
      public void paint(Graphics g)
      {
			g.drawImage(background.getImage(),0,0,1025,725,this);
		   g.setColor(Color.white);
		   g.drawString("What is a Visual Novel?",10,20);
		   g.drawString("__________________",10,20);
		   g.drawString("A visual novel is an interactive fiction game featuring mostly static graphics, usually with anime-style art. As the name might suggest, they resemble mixed- ",10,45);
		   g.drawString("media novels or tableau vivant stage plays. It is a form of story that incorporates pictures, backgrounds, and drawn characters throughout the whole storytelling process.",10,60);
		   g.drawString("With ours, a user-interactive game is interjected during the storytelling. The player will first select a character/protagonist and a number of skills. Following that, there will be the short",10,80);
		   g.drawString("story of conflict told. Then there is the battle where the player fights, with their selected character and skills, against the programmed character, the sophisticated opponent/antagonist.",10,95);
		   
		   //How the skill selection works.
		   g.drawString("Skills Selection",10,125);
		   g.drawString("____________",10,125);
		   g.drawString("When the player develops their character's skill settings, there are 5 potential skills that the player can choose at the maximum, out of the 20 skills that are displayed on the left region",10,150);
		   g.drawString("of the screen. Every skill comes with an explanation of what it does and its effect to a certain magnitude depending on how many times the skill points is invested on, all displayed to",10,165);
		   g.drawString("the right. Any of those 20 skills can be invested up to a maximum of 4 times. There are 4 categories of skills which are fire, ice, earth, and thunder in that respective order going from",10,180);
		   g.drawString("top to bottom on the left region of the screen. Each category has 5 different skills which are all unlocked at the start, and they each take up an entire row on the section of the screen.",10,195);
		   g.drawString("If the player decides to choose 5 skills first, the remaining 15 skills will become locked, disabling the player from selecting any more of those skills. You can use a maximum total of",10,210);
		   g.drawString("20 skill points for investing the 5 skills. The 7 stat skills along the right center allows the player to upgrade their character’s stats, indicated on the bottom right corner. There is no ",10,225);
		   g.drawString("limit as to how many times they can be invested. The player has 50 skill points, allowing the skills to be invested, to a total of 50 times. Once all skill points have been used, as it will",10,240);
		   g.drawString("be indicated to the right, the start button activates, restricting the player to make any further skill selections, and allows the player to proceed, next with the story, followed by the battle.",10,255);
		   g.drawString("All skill points must be used for the player to continue on with the story, followed by the battle.",10,270);
		   //How the story works.
		   g.drawString("Story",10,300);
		   g.drawString("____",10,300);
		   g.drawString("There will be the story, appearing on screen. The player may click any region of the screen to have the dialogue of the story continue.",10,325);
		   //How the battle works.
		   g.drawString("Battle Operations",10,355);
		   g.drawString("______________",10,355);
		   g.drawString("In the battle arena, the player's character will be positioned on the bottom right region of the screen, while the computer's character on the top left region, during engagement on the",10,380);
		   g.drawString("arena. Each character will have their status information displayed in their same region. There will be three bars indicating the stats of health points, mana points, and action situation,",10,395);
		   g.drawString("in this order, going from top to bottom. Right below all three bars there will be four indications of attack, defense, evasion, and spell resistence scores, in this respective order from",10,410);
		   g.drawString("left to right. On the bottom left corner, there will be all selected skills displayed for the player to activate against their opponent. When the player's health points reach zero before the",10,425);
		   g.drawString("opponent's does, the player loses with a clear indication on the computer screen. The player must think both wisely and strategically through the battle, when they use their selected",10,440);
		   g.drawString("skills and character settings. After all, their resources can only do so much against a built in villain. Only then will they be able to ensure victory, and conquer the game.",10,455);
		   
			//Tips
			g.drawString("Tips",10,485);
		   g.drawString("___",10,485);
		   g.drawString("-Just don't suck at video games.",10,500);
		   g.drawString("-Watch out for the red bar; once the red bar is full, you should make a move right away so that you wouldn't waste any precious time.                                                      ",10,515);
		   g.drawString("-Choose good skills; don't choose a random combination of skills because you can't defeat the AI if you don't have any \"Ideas\" in your skill build.                                        ",10,530);
		   g.drawString("-Don't friggin spend only 1 point in a skill; a skill is pretty much useless if it is not at its maximum strength. ",10,545);
		   g.drawString("-Plan out the battle ahead; if you see your opponent having an easy time against you, use another strategy with your skills to try to change the flow of the game",10,560);
		   g.drawString("-Play safely or riskily; a true man never play safely because they are stupid. But playing safely is just way too passive. So it is better to go for a risky strategy than safe.                      ",10,575);
			//The request for the user to enjoy our game and recognize that we deserve a perfect score mark.
		   g.drawString("THAT'S IT FOR NOW, SO HAVE FUN AND ENJOY PLAYING THE GAME THAT WE MADE!",260,665);
		   g.drawString("BY: FRANK T. WANG & JUSTIN ARITA\\ILLUSTRATED BY: FRANK T. WANG & REBECCA SETO",246,685);
      }
   }
   
   public static void main(String[] args)
   {
      new Instruction();
   }
}