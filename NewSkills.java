   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.Graphics;
   public class NewSkills extends MouseMotionAdapter implements ActionListener
   {
      Drawing draw = new Drawing();
      int x,y;
      JButton start = new JButton ("Start");
      //ImageIcon bigCircle = new ImageIcon("bigCircle.gif");
      ImageIcon background = new ImageIcon("background.jpg");
      ImageIcon expBox = new ImageIcon("expBox.png");
      JFrame frame = new JFrame();
   
   //Stats
      ImageIcon attack = new ImageIcon("attack.gif");
      JButton attackButton = new JButton(attack);
      ImageIcon defense = new ImageIcon("defense.gif");
      JButton defenseButton = new JButton(defense);
      ImageIcon speed = new ImageIcon("speed.gif");
      JButton speedButton = new JButton(speed);
      ImageIcon evasion = new ImageIcon("evasion.gif");
      JButton evasionButton = new JButton(evasion);
      ImageIcon health = new ImageIcon("health.gif");
      JButton healthButton = new JButton(health);
      ImageIcon mana = new ImageIcon("mana.gif");
      JButton manaButton = new JButton(mana);
      ImageIcon magicDefense = new ImageIcon("magicDefense.gif");
      JButton mdButton = new JButton(magicDefense);
   
   //Fire
      ImageIcon fireBolt = new ImageIcon("fireBolt.gif");
      JButton fireBoltButton = new JButton(fireBolt);
      ImageIcon berserk = new ImageIcon("berserkerCry.gif");
      JButton berserkButton = new JButton(berserk);
      ImageIcon blood = new ImageIcon("bloodLust.gif");
      JButton bloodButton = new JButton(blood);
      ImageIcon flame = new ImageIcon("mindFlame.gif");
      JButton flameButton = new JButton(flame);
      ImageIcon blast = new ImageIcon("Chaos Blast.gif");
      JButton blastButton = new JButton(blast);
   
   //Ice
      ImageIcon graveChill = new ImageIcon("graveChill.gif");
      JButton chillButton = new JButton(graveChill);
      ImageIcon darkRitual = new ImageIcon("darkRitual.gif");
      JButton darkButton = new JButton(darkRitual);
      ImageIcon essenceFreeze = new ImageIcon("essenceFreeze.jpg");
      JButton freezeButton = new JButton(essenceFreeze);
      ImageIcon magicCancel = new ImageIcon("magicCancel.gif");
      JButton cancelButton = new JButton(magicCancel);
      ImageIcon frostNova = new ImageIcon("frostNova.gif");
      JButton novaButton = new JButton(frostNova);
   
   //Earth
      ImageIcon overGrowth = new ImageIcon("overGrowth.gif");
      JButton growthButton = new JButton(overGrowth);
      ImageIcon armour  = new ImageIcon("livingArmor.gif");
      JButton armourButton = new JButton(armour);
      ImageIcon implode = new ImageIcon("implode.gif");
      JButton implodeButton = new JButton(implode);
      ImageIcon cure = new ImageIcon("Cure.gif");
      JButton cureButton = new JButton(cure);
      ImageIcon holy_grace = new ImageIcon("Holy Grace.gif");
      JButton graceButton = new JButton(holy_grace);
   
   //Thunder
      ImageIcon thunderPunch = new ImageIcon("thunderPunch.gif");
      JButton punchButton = new JButton(thunderPunch);
      ImageIcon overload = new ImageIcon("overload.gif");
      JButton overloadButton = new JButton(overload);
      ImageIcon plasmafield = new ImageIcon("plasmaField.jpg");
      JButton fieldButton = new JButton(plasmafield);
      ImageIcon fusion = new ImageIcon("fusion.gif");
      JButton fusionButton = new JButton(fusion);
      ImageIcon infiniteVoltage = new ImageIcon("infinitVoltage.gif");
      JButton voltageButton = new JButton(infiniteVoltage);
   
   //Array that keeps track of the skill investments
      static int [] fire = new int[5];
      static int [] ice = new int[5];
      static int [] earth = new int[5];
      static int [] thunder = new int[5];
      static int [] stats = new int[7];
      int skillPoints = 50;
   
       public NewSkills()
      {
      //Fire
         frame.add(fireBoltButton);
         fireBoltButton.addActionListener(this);
         frame.add(flameButton);
         flameButton.addActionListener(this);
         frame.add(blastButton);
         blastButton.addActionListener(this);
         frame.add(bloodButton);
         bloodButton.addActionListener(this);
         frame.add(berserkButton);
         berserkButton.addActionListener(this);
         frame.setBackground(Color.black);
         Insets insets = frame.getInsets();
         fireBoltButton.setBounds(25 + insets.left, 60 + insets.top,70, 70);
         bloodButton.setBounds(145 + insets.left, 60 + insets.top,70, 70);
         flameButton.setBounds(265 + insets.left, 60 + insets.top,70, 70);
         berserkButton.setBounds(385 + insets.left, 60 + insets.top,70, 70);
         blastButton.setBounds(505 + insets.left, 60 + insets.top,70, 70);
      
      //Ice
         frame.add(chillButton);
         chillButton.addActionListener(this);
         frame.add(darkButton);
         darkButton.addActionListener(this);
         frame.add(freezeButton);
         freezeButton.addActionListener(this);
         frame.add(cancelButton);
         cancelButton.addActionListener(this);
         frame.add(novaButton);
         novaButton.addActionListener(this);
         chillButton.setBounds(25 + insets.left, 220 + insets.top,70, 70);
         darkButton.setBounds(145 + insets.left, 220 + insets.top,70, 70);
         freezeButton.setBounds(265 + insets.left, 220 + insets.top,70, 70);
         cancelButton.setBounds(385 + insets.left, 220 + insets.top,70, 70);
         novaButton.setBounds(505 + insets.left, 220 + insets.top,70, 70);
      
      //Earth
         frame.add(growthButton);
         growthButton.addActionListener(this);
         frame.add(armourButton);
         armourButton.addActionListener(this);
         frame.add(implodeButton);
         implodeButton.addActionListener(this);
         frame.add(cureButton);
         cureButton.addActionListener(this);
         frame.add(graceButton);
         graceButton.addActionListener(this);
         growthButton.setBounds(25 + insets.left, 380 + insets.top,70, 70);
         armourButton.setBounds(145 + insets.left, 380 + insets.top,70, 70);
         implodeButton.setBounds(265 + insets.left, 380 + insets.top,70, 70);
         cureButton.setBounds(385 + insets.left, 380 + insets.top,70, 70);
         graceButton.setBounds(505 + insets.left, 380 + insets.top,70, 70);
      
      //Thunder
         frame.add(punchButton);
         punchButton.addActionListener(this);
         frame.add(overloadButton);
         overloadButton.addActionListener(this);
         frame.add(fieldButton);
         fieldButton.addActionListener(this);
         frame.add(fusionButton);
         fusionButton.addActionListener(this);
         frame.add(voltageButton);
         voltageButton.addActionListener(this);
         punchButton.setBounds(25 + insets.left, 540 + insets.top,70, 70);
         overloadButton.setBounds(145 + insets.left, 540 + insets.top,70, 70);
         fieldButton.setBounds(265 + insets.left, 540 + insets.top,70, 70);
         fusionButton.setBounds(385 + insets.left, 540 + insets.top,70, 70);
         voltageButton.setBounds(505 + insets.left, 540 + insets.top,70, 70);
      
      //Stats
         frame.add(attackButton);
         attackButton.addActionListener(this);
         attackButton.setBounds(610,5,70,70);
         frame.add(defenseButton);
         defenseButton.addActionListener(this);
         defenseButton.setBounds(610,105,70,70);
         frame.add(speedButton);
         speedButton.addActionListener(this);
         speedButton.setBounds(610,205,70,70);
         frame.add(evasionButton);
         evasionButton.addActionListener(this);
         evasionButton.setBounds(610,305,70,70);
         frame.add(healthButton);
         healthButton.addActionListener(this);
         healthButton.setBounds(610,405,70,70);
         frame.add(manaButton);
         manaButton.addActionListener(this);
         manaButton.setBounds(610,505,70,70);
         frame.add(mdButton);
         mdButton.addActionListener(this);
         mdButton.setBounds(610,605,70,70);
      
      
      //Declarations Mandatory
         frame.add(start);
         start.addActionListener(this);
         start.setEnabled(false);
         start.setBounds(790,650,100,20);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
         frame.setSize(1025 + insets.left + insets.right,725 + insets.top + insets.bottom);
         frame.add(draw); // put an object we can draw on in the centre of the window
         draw.addMouseMotionListener(this);
         frame.setVisible(true); //show the window
         frame.setResizable(false);
      }
   
	//A player can only choose up to 5 skills, so this method will check if they have reached that limit
       public boolean maxedOut()
      {
         int spells=0;
         for (int i = 0; i < 5; i++)
            if (fire[i] != 0)
               spells ++;
         for (int i = 0; i < 5; i++)
            if (ice[i] != 0)
               spells ++;
         for (int i = 0; i < 5; i++)
            if (earth[i] != 0)
               spells ++;
         for (int i = 0; i < 5; i++)
            if (thunder[i] != 0)
               spells ++;
         if (spells + 1 > 5)
            return true;
         else
            return false;
      }
   	
      public void actionPerformed(ActionEvent e)
      {
         if (e.getSource() == start)
			{
				frame.setVisible(false);
         	new Dialogue();
			}   	
			
      //Fire
         if (skillPoints != 0)
         {
            if (e.getSource() == fireBoltButton && fire[0] < 4)
            {
               fire[0]++;
               skillPoints--;
            }
            else if (e.getSource() == bloodButton && fire[1] < 4)
            {
               fire[1]++;
               skillPoints--;
            }
            else if (e.getSource() == flameButton && fire[2] < 4)
            {
               fire[2]++;
               skillPoints--;
            }
            else if (e.getSource() == berserkButton && fire[3] < 4)
            {
               fire[3]++;
               skillPoints--;
            }
            else if (e.getSource() == blastButton && fire[4] < 4)
            {
               fire[4]++;
               skillPoints--;
            }
         
         // Ice
            if (e.getSource() == chillButton && ice[0] < 4) 
            {
               ice[0]++; 
               skillPoints--;
            }
            else if (e.getSource() == darkButton && ice[1] < 4) 
            {
               ice[1]++; 
               skillPoints--;
            }
            else if (e.getSource() == freezeButton && ice[2] < 4) 
            {
               ice[2]++; 
               skillPoints--;
            }
            else if (e.getSource() == cancelButton && ice[3] < 4) 
            {
               ice[3]++; 
               skillPoints--;
            }
            else if (e.getSource() == novaButton && ice[4] < 4) 
            {
               ice[4]++; 
               skillPoints--;
            }
         
         // Earth
            if (e.getSource() == growthButton && earth[0] < 4)
            {
               earth[0]++;
               skillPoints--;
            }
            else if (e.getSource() == armourButton && earth[1] < 4)
            {
               earth[1]++;
               skillPoints--;
            }
            else if (e.getSource() == implodeButton && earth[2] < 4)
            {
               earth[2]++;
               skillPoints--;
            }
            else if (e.getSource() == cureButton && earth[3] < 4)
            {
               earth[3]++;
               skillPoints--;
            }
            else if (e.getSource() == graceButton && earth[4] < 4)
            {
               earth[4]++;
               skillPoints--;
            }
         
         // Thunder
            if (e.getSource() == punchButton && thunder[0] < 4)
            {
               thunder[0]++;
               skillPoints--;
            }
            else if (e.getSource() == overloadButton && thunder[1] < 4)
            {
               thunder[1]++;
               skillPoints--;
            }
            else if (e.getSource() == fieldButton && thunder[2] < 4)
            {
               thunder[2]++;
               skillPoints--;
            }
            else if (e.getSource() == fusionButton && thunder[3] < 4)
            {
               thunder[3]++;
               skillPoints--;
            }
            else if (e.getSource() == voltageButton && thunder[4] < 4)
            {
               thunder[4]++;
               skillPoints--;
            }
         }
      	
      //Stats
         if (e.getSource() == attackButton && skillPoints != 0)
         {
            stats[0]++;
            skillPoints--;
         }
         if (e.getSource() == defenseButton && skillPoints != 0)
         {
            stats[1]++;
            skillPoints--;
         }
         if (e.getSource() == speedButton && skillPoints != 0)
         {
            stats[2]++;
            skillPoints--;
         }
         if (e.getSource() == evasionButton && skillPoints != 0)
         {
            stats[3]++;
            skillPoints--;
         }
         if (e.getSource() == healthButton && skillPoints != 0)
         {
            stats[4]++;
            skillPoints--;
         }
         if (e.getSource() == manaButton && skillPoints != 0)
         {
            stats[5]++;
            skillPoints--;
         }
         if (e.getSource() == mdButton && skillPoints != 0)
         {
            stats[6]++;
            skillPoints--;
         }
      
		//If the player already have 5 skills, then all the buttons other than the 5 are restricted
         if (maxedOut())
         {	
            if (fire[0] == 0)
               fireBoltButton.setEnabled(false);
            if (fire[1] == 0)
               bloodButton.setEnabled(false);
            if (fire[2] == 0)
               flameButton.setEnabled(false);
            if (fire[3] == 0)
               berserkButton.setEnabled(false);
            if (fire[4] == 0)
               blastButton.setEnabled(false);
            if (ice[0] == 0)
               chillButton.setEnabled(false);
            if (ice[1] == 0)
               darkButton.setEnabled(false);
            if (ice[2] == 0)
               freezeButton.setEnabled(false);
            if (ice[3] == 0)
               cancelButton.setEnabled(false);
            if (ice[4] == 0)
               novaButton.setEnabled(false);
            if (earth[0] == 0)
               growthButton.setEnabled(false);
            if (earth[1] == 0)
               armourButton.setEnabled(false);
            if (earth[2] == 0)
               implodeButton.setEnabled(false);
            if (earth[3] == 0)
               cureButton.setEnabled(false);
            if (earth[4] == 0)
               graceButton.setEnabled(false);
            if (thunder[0] == 0)
               punchButton.setEnabled(false);
            if (thunder[1] == 0)
               overloadButton.setEnabled(false);
            if (thunder[2] == 0)
               fieldButton.setEnabled(false);
            if (thunder[3] == 0)
               fusionButton.setEnabled(false);
            if (thunder[4] == 0)
               voltageButton.setEnabled(false);
         }
      
         if (skillPoints == 0)
            start.setEnabled(true);
      
      //Repaint
         draw.repaint();
      }
   
       public void mouseMoved (MouseEvent e)
      {
         x = e.getX();
         y = e.getY();
         draw.repaint();
      }
		//Static methods used to allow other classes to access the information obtained from player choosing skills
       public static int [] skillFire ()
      {
         return fire;
      }
       public static int [] skillIce ()
      {
         return ice;
      }
       public static int [] skillEarth ()
      {
         return earth;
      }
       public static int [] skillThunder ()
      {
         return thunder;
      }
       public static int [] stats()
      {
         return stats;
      }
       public class Drawing extends JComponent
      {
          public void paint(Graphics g)
         {	
         
            g.drawImage(background.getImage(),0,0,1025,725,this);
            //g.drawImage(bigCircle.getImage(),300,100,1052,1052,this);
         
         //Font
            g.setColor(Color.black);
            g.drawImage(expBox.getImage(),700,0,317,358,this);
         //Skill Explanations
         //Fire
            if (x >= 5 && x <= 115 && y >= 40 && y <= 150)
            {
               g.drawImage(fireBolt.getImage(),827,30,70,70,this);
               g.drawString("Fire Bolt",840,112);
               g.drawString("Does a quick and painful damage to your",745,137);
               g.drawString("opponent.",745,149);
               g.drawString("Level 1 - 150 Magical Damage/75 Mana",745,174);
               g.drawString("Level 2 - 200 Magical Damage/75 Mana",745,189);
               g.drawString("Level 3 - 250 Magical Damage/75 Mana",745,204);
               g.drawString("Level 4 - 300 Magical Damage/75 Mana",745,219);
               g.drawString("Current Level: Level " + fire[0],745,244);
               if (fire[0] == 0)
                  g.drawString("Current Effect: " + "None",745,259);
               else if (fire[0] == 1)
                  g.drawString("Current Effect: " + "150 Magical Damage",745,259);
               else if (fire[0] == 2)
                  g.drawString("Current Effect: " + "200 Magical Damage",745,259);
               else if (fire[0] == 3)
                  g.drawString("Current Effect: " + "250 Magical Damage",745,259);
               else if (fire[0] == 4)
                  g.drawString("Current Effect: " + "300 Magical Damage",745,259);
            }
            if (x >= 125 && x <= 235 && y >= 40 && y <= 150)
            {
               g.drawImage(blood.getImage(),827,30,70,70,this);
               g.drawString("Blood Lust",835,112);
               g.drawString("You strike your opponent with an insatiable",740,137);
               g.drawString("thirst for blood; you do more damage the" ,740,149);
               g.drawString("weaker your opponent is.",740,161);
               g.drawString("Level 1 - Does damage equal to 1.5% of your",740,186);
               g.drawString(" opponent's lost life/80 Mana",740,198);
               g.drawString("Level 2 - Does damage equal to 3% of your",740,213);
               g.drawString(" opponent's lost life/110 Mana",740,225);
               g.drawString("Level 3 - Does damage equal to 4.5% of your",740,240);
               g.drawString(" opponent's lost life/140 Mana",740,252);
               g.drawString("Level 4 - Does damage equal to 6% of your",740,267);
               g.drawString(" opponent's lost life/170 Mana",740,279);
               g.drawString("Current Level: " + fire[1],740,304);
               if (fire[1] == 0)
                  g.drawString("Current Effect: " + "None",740,319);
               else if (fire[1] == 1)
               {
                  g.drawString("Current Effect: " + "Does damage equal to 1.5% of",740,319);
                  g.drawString(" your opponent's lost life.",740,331);
               }
               else if (fire[1] == 2)
               {
                  g.drawString("Current Effect: " + "Does damage equal to 3% of",740,319);
                  g.drawString(" your opponent's lost life.",740,331);
               }
               else if (fire[1] == 3)
               {
                  g.drawString("Current Effect: " + "Does damage equal to 4.5% of",740,319);
                  g.drawString(" your opponent's lost life.",740,331);
               }
               else if (fire[1] == 4)
               {
                  g.drawString("Current Effect: " + "Does damage equal to 6% of",740,319);
                  g.drawString(" your opponent's lost life.",740,331);
               }
            }
            if (x >= 245 && x <= 355 && y >= 40 && y <= 150)
            {
               g.drawImage(flame.getImage(),827,30,70,70,this);
               g.drawString("Mind Flame",831,112);
               g.drawString("Psionicly shocks your opponent, burning",745,137);
               g.drawString("his mind and life source",745,152);
               g.drawString("Level 1 - 75 Mana Burn/30 Mana",745,177);
               g.drawString("Level 2 - 100 Mana Burn/60 Mana",745,192);
               g.drawString("Level 3 - 125 Mana Burn/90 Mana",745,207);
               g.drawString("Level 4 - 150 Mana Burn/120 Mana",745,222);
               g.drawString("*Excess mana burn is dealt to the target's",745,237);
               g.drawString("health.",745,249);
               g.drawString("Current Level: " + fire[2],745,274);
               if (fire[2] == 0)
                  g.drawString("Current Effect: " + "None",745,289);
               else if (fire[2] == 1)
                  g.drawString("Current Effect: " + "75 Mana Burn",745,289);
               else if (fire[2] == 2)
                  g.drawString("Current Effect: " + "100 Mana Burn",745,289);
               else if (fire[2] == 3)
                  g.drawString("Current Effect: " + "125 Mana Burn",745,289);
               else if (fire[2] == 4)
                  g.drawString("Current Effect: " + "150 Mana Burn",745,289);
            }
            if (x >= 365 && x <= 475 && y >= 40 && y <= 150)
            {
               g.drawImage(berserk.getImage(),827,30,70,70,this);
               g.drawString("Berserker Cry",824,112);
               g.drawString("You are engulfed by the spirit of Situ, giving you",725,137);
               g.drawString("an increased boost in attack power and speed.",725,149);
               g.drawString("Level 1 - +15 Damage/+7 Speed/150 Mana",725,174);
               g.drawString("Level 2 - +30 Damage/+14 Speed/150 Mana",725,189);
               g.drawString("Level 3 - +45 Damage/+21 Speed/150 Mana",725,204);
               g.drawString("Level 4 - +60 Damage/+28 Speed/150 Mana",725,219);
               g.drawString("Current Level: " + fire[3],725,244);
               if (fire[3] == 0)
                  g.drawString("Current Effect: " + "None",725,259);
               else if (fire[3] == 1)
                  g.drawString("Current Effect: " + "+15 Damage/+7 Speed",725,259);
               else if (fire[3] == 2)
                  g.drawString("Current Effect: " + "+30 Damage/+14 Speed",725,259);
               else if (fire[3] == 3)
                  g.drawString("Current Effect: " + "+45 Damage/+21 Speed",725,259);
               else if (fire[3] == 4)
                  g.drawString("Current Effect: " + "+60 Damage/+28 Speed",725,259);
            }
            if (x >= 485 && x <= 595 && y >= 40 && y <= 150)
            {
               g.drawImage(blast.getImage(),827,30,70,70,this);
               g.drawString("Chaos Blast",830,112);
               g.drawString("You release a blast of destructive energy that",731,137);
               g.drawString("damages and paralyzes your enemy",731,149);
               g.drawString("Level 1 - 150 Damage/1 second/110 Mana",731,174);
               g.drawString("Level 2 - 200 Damage/2 seconds/150 Mana",731,189);
               g.drawString("Level 3 - 250 Damage/3 seconds/190 Mana",731,204);
               g.drawString("Level 4 - 300 Damage/4 seconds/230 Mana",731,219);
               g.drawString("Current Level: " + fire[4],731,244);
               if (fire[4] == 0)
                  g.drawString("Current Effect: " + "None",731,259);
               else if (fire[4] == 1)
                  g.drawString("Current Effect: " + "150 Damage/1 second",731,259);
               else if (fire[4] == 2)
                  g.drawString("Current Effect: " + "200 Damage/2 seconds",731,259);
               else if (fire[4] == 3)
                  g.drawString("Current Effect: " + "250 Damage/3 seconds",731,259);
               else if (fire[4] == 4)
                  g.drawString("Current Effect: " + "300 Damage/4 seconds",731,259);
               g.drawString("*This ability is un-spammable",731,274);
            }
         //Ice
            if (x >= 5 && x <= 115 && y >= 200 && y <= 310)
            {
               g.drawImage(graveChill.getImage(),827,30,70,70,this);
               g.drawString("Shadow Dance",823,112);
               g.drawString("You perform your dance of Kyobi, weakening",735,137);
               g.drawString("your opponent's physical performance.",735,149);
               g.drawString("Level 1 - 5% Slow/4% Miss/100 Mana",735,174);
               g.drawString("Level 2 - 10% Slow/8% Miss/100 Mana",735,189);
               g.drawString("Level 3 - 15% Slow/12% Miss/100 Mana",735,204);
               g.drawString("Level 4 - 20% Slow/16% Miss/100 Mana",735,219);
               g.drawString("Current Level: " + ice[0],735,244);
               if (ice[0] == 0)
                  g.drawString("Current Effect: " + "None",735,259);
               else if (ice[0] == 1)
                  g.drawString("Current Effect: " + "5% Slow/4% Miss",735,259);
               else if (ice[0] == 2)
                  g.drawString("Current Effect: " + "10% Slow/8% Miss",735,259);
               else if (ice[0] == 3)
                  g.drawString("Current Effect: " + "15% Slow/12% Miss",735,259);
               else if (ice[0] == 4)
                  g.drawString("Current Effect: " + "20% Slow/16% Miss",735,259);
            }
            if (x >= 125 && x <= 235 && y >= 200 && y <= 310)
            {
               g.drawImage(darkRitual.getImage(),827,30,70,70,this);
               g.drawString("Dark Ritual",832,112);
               g.drawString("You sacrifice your own life source to",740,137);
               g.drawString("replenish your mana.",740,149);
               g.drawString("Level 1 - +100 Mana/-100 life/50 Mana",740,174);
               g.drawString("Level 2 - +150 Mana/-100 life/50 Mana",740,189);
               g.drawString("Level 3 - +200 Mana/-100 life/50 Mana",740,204);
               g.drawString("Level 4 - +250 Mana/-100 life/50 Mana",740,219);
               g.drawString("Current Level: " + ice[1],740,244);
               if (ice[1] == 0)
                  g.drawString("Current Effect: " + "None",740,259);
               else if (ice[1] == 1)
                  g.drawString("Current Effect: " + "+100 Mana",740,259);
               else if (ice[1] == 2)
                  g.drawString("Current Effect: " + "+150 Mana",740,259);
               else if (ice[1] == 3)
                  g.drawString("Current Effect: " + "+200 Mana",740,259);
               else if (ice[1] == 4)
                  g.drawString("Current Effect: " + "+250 Mana",740,259);
            }
            if (x >= 245 && x <= 355 && y >= 200 && y <= 310)
            {
               g.drawImage(essenceFreeze.getImage(),827,30,70,70,this);
               g.drawString("Essence Freeze",819,112);
               g.drawString("The power of ice mage freezes the source of life",724,137);
               g.drawString("and energy of a target until he meets his demise.",724,149);
               g.drawString("Regeneration can be less than 0.",724,161);
               g.drawString("Level 1 - -16 Regeneration/100 Mana",724,186);
               g.drawString("Level 2 - -32 Regeneration/100 Mana",724,201);
               g.drawString("Level 3 - -48 Regeneration/100 Mana",724,216);
               g.drawString("Level 4 - -64 Regeneration/100 Mana",724,231);
               g.drawString("Current Level: " + ice[2],724,256);
               if (ice[2] == 0)
                  g.drawString("Current Effect: " + "None",724,271);
               else if (ice[2] == 1)
                  g.drawString("Current Effect: " + "-16 Regeneration",724,271);
               else if (ice[2] == 2)
                  g.drawString("Current Effect: " + "-32 Regeneration",724,271);
               else if (ice[2] == 3)
                  g.drawString("Current Effect: " + "-48 Regeneration",724,271);
               else if (ice[2] == 4)
                  g.drawString("Current Effect: " + "-64 Regeneration",724,271);
            }
            if (x >= 365 && x <= 475 && y >= 200 && y <= 310)
            {
               g.drawImage(magicCancel.getImage(),827,30,70,70,this);
               g.drawString("Cryptic Command",813,112);
               g.drawString("You use your own arcane magic to interrupt",731,137);
               g.drawString("your opponent's ability to cast spells.",731,149);
               g.drawString("Can be used any time.",731,161);
               g.drawString("Level 1 - Interrupt before 40/75 Mana",731,186);
               g.drawString("Level 2 - Interrupt before 60/75 Mana",731,201);
               g.drawString("Level 3 - Interrupt before 80/75 Mana",731,216);
               g.drawString("Level 4 - Interrupt before 100/75 Mana",731,231);
               g.drawString("*Interruption drains 100 mana.",731,246);
               g.drawString("Current Level: " + ice[3],731,271);
               if (ice[3] == 0)
                  g.drawString("Current Effect: " + "None",731,286);
               else if (ice[3] == 1)
                  g.drawString("Current Effect: " + "Interrupt before 40",731,286);
               else if (ice[3] == 2)
                  g.drawString("Current Effect: " + "Interrupt before 60",731,286);
               else if (ice[3] == 3)
                  g.drawString("Current Effect: " + "Interrupt before 80",731,286);
               else if (ice[3] == 4)
                  g.drawString("Current Effect: " + "Interrupt before 100",731,286);
               g.drawString("*Each additional level reduces its cool down",731,301);
            }
            if (x >= 485 && x <= 595 && y >= 200 && y <= 310)
            {
               g.drawImage(frostNova.getImage(),827,30,70,70,this);
               g.drawString("Frost Nova",833,112);
               g.drawString("You blast your opponent with a wave of",748,137);
               g.drawString("damaging frosts that slow them.",748,149);
               g.drawString("Level 1 - 200 Damage/110 Mana",748,174);
               g.drawString("Level 2 - 300 Damage/140 Mana",748,189);
               g.drawString("Level 3 - 400 Damage/170 Mana",748,204);
               g.drawString("Level 4 - 500 Damage/200 Mana",748,219);
               g.drawString("*The slow does not stack.",748,234);
               g.drawString("Current Level: " + ice[4],748,259);
               if (ice[4] == 0)
                  g.drawString("Current Effect: " + "None",748,274);
               else if (ice[4] == 1)
                  g.drawString("Current Effect: " + "175 Damage",748,274);
               else if (ice[4] == 2)
                  g.drawString("Current Effect: " + "250 Damage",748,274);
               else if (ice[4] == 3)
                  g.drawString("Current Effect: " + "325 Damage",748,274);
               else if (ice[4] == 4)
                  g.drawString("Current Effect: " + "400 Damage",748,274);
            }
         //Earth
            if (x >= 5 && x <= 115 && y >= 360 && y <= 470)
            {
               g.drawImage(overGrowth.getImage(),827,30,70,70,this);
               g.drawString("Tangle",844,112);
               g.drawString("You call forth the power of nature and",742,137);
               g.drawString("strangle your opponent with vines,",742,149);
               g.drawString("restricting their movement and doing",742,161);
               g.drawString("minor damage for a short period of time.",742,173);
               g.drawString("Level 1 - 20% Slow/30 Damage/50 Mana",742,198);
               g.drawString("Level 2 - 25% Slow/40 Damage/50 Mana",742,213);
               g.drawString("Level 3 - 30% Slow/50 Damage/50 Mana",742,228);
               g.drawString("Level 4 - 35% Slow/60 Damage/50 Mana",742,243);
               g.drawString("*Slow does not stack.",742,258);
               g.drawString("Current Level: " + earth[0],742,283);
               if (earth[0] == 0)
                  g.drawString("Current Effect: " + "None",742,298);
               else if (earth[0] == 1)
                  g.drawString("Current Effect: " + "20% Slow/30 Damage",742,298);
               else if (earth[0] == 2)
                  g.drawString("Current Effect: " + "25% Slow/40 Damage",742,298);
               else if (earth[0] == 3)
                  g.drawString("Current Effect: " + "30% Slow/50 Damage",742,298);
               else if (earth[0] == 4)
                  g.drawString("Current Effect: " + "35% Slow/60 Damage",742,298);
            }
            if (x >= 125 && x <= 235 && y >= 360 && y <= 470)
            {
               g.drawImage(armour.getImage(),827,30,70,70,this);
               g.drawString("Living Armour",825,112);
               g.drawString("You magically conjure a defensive armour made",731,137);
               g.drawString("out of nature's creation that protects you from",731,149);
               g.drawString("both physical and magical attacks.",731,161);
               g.drawString("Level 1 - +12 DEF/+12 MDF/75 Mana",731,186);
               g.drawString("Level 2 - +24 DEF/+24 MDF/75 Mana",731,201);
               g.drawString("Level 3 - +36 DEF/+36 MDF/75 Mana",731,216);
               g.drawString("Level 4 - +48 DEF/+48 MDF/75 Mana",731,231);
               g.drawString("*You can only have one shield up.",731,246);
               g.drawString("Current Level: " + earth[1],731,271);
               if (earth[1] == 0)
                  g.drawString("Current Effect: " + "None",731,286);
               else if (earth[1] == 1)
                  g.drawString("Current Effect: " + "+12 DEF/+12 MDF",731,286);
               else if (earth[1] == 2)
                  g.drawString("Current Effect: " + "+24 DEF/+24 MDF",731,286);
               else if (earth[1] == 3)
                  g.drawString("Current Effect: " + "+36 DEF/+36 MDF",731,286);
               else if (earth[1] == 4)
                  g.drawString("Current Effect: " + "+48 DEF/+48 MDF",731,286);
            }
            if (x >= 245 && x <= 355 && y >= 360 && y <= 470)
            {
               g.drawImage(implode.getImage(),827,30,70,70,this);
               g.drawString("Implode",842,112);
               g.drawString("You can no longer endure the damage you",742,137);
               g.drawString("have taken, and you let out a furious wave",742,149);
               g.drawString("of energy that shocks your opponent.",742,161);
               g.drawString("Level 1 - 5% of your lost life",742,186);
               g.drawString("Level 2 - 10% of your lost life",742,201);
               g.drawString("Level 3 - 15% of your lost life",742,216);
               g.drawString("Level 4 - 20% of your lost life",742,231);
               g.drawString("Current Level: " + earth[2],742,256);
               if (earth[2] == 0)
                  g.drawString("Current Effect: " + "None",742,271);
               else if (earth[2] == 1)
                  g.drawString("Current Effect: " + "5% of your lost life",742,271);
               else if (earth[2] == 2)
                  g.drawString("Current Effect: " + "10% of your lost life",742,271);
               else if (earth[2] == 3)
                  g.drawString("Current Effect: " + "15% of your lost life",742,271);
               else if (earth[2] == 4)
                  g.drawString("Current Effect: " + "20% of your lost life",742,271);
               g.drawString("*Mana cost is higher the lower your health is",742,286);
            }
            if (x >= 365 && x <= 475 && y >= 360 && y <= 470)
            {
               g.drawImage(cure.getImage(),827,30,70,70,this);
               g.drawString("Cure",848,112);
               g.drawString("You heal yourself using your mastery of life's",731,137);
               g.drawString("essence.",731,149);
               g.drawString("Level 1 - Heal 100 life/125 Mana",731,174);
               g.drawString("Level 2 - Heal 200 life/150 Mana",731,189);
               g.drawString("Level 3 - Heal 300 life/175 Mana",731,204);
               g.drawString("Level 4 - Heal 400 life/200 Mana",731,219);
               g.drawString("Current Level: " + earth[3],731,244);
               if (earth[3] == 0)
                  g.drawString("Current Effect: " + "None",731,259);
               else if (earth[3] == 1)
                  g.drawString("Current Effect: " + "Heal 100 life",731,259);
               else if (earth[3] == 2)
                  g.drawString("Current Effect: " + "Heal 200 life",731,259);
               else if (earth[3] == 3)
                  g.drawString("Current Effect: " + "Heal 300 life",731,259);
               else if (earth[3] == 4)
                  g.drawString("Current Effect: " + "Heal 400 life",731,259);
            }
            if (x >= 485 && x <= 595 && y >= 360 && y <= 470)
            {
               g.drawImage(holy_grace.getImage(),827,30,70,70,this);
               g.drawString("Angelic Blessing",816,112);
               g.drawString("You purify yourself of all evil, enhancing your",730,137);
               g.drawString("regeneration rate and attack power.",730,149);
               g.drawString("Level 1 - +18 Regeneration/+12 Damage/30 Mana",730,174);
               g.drawString("Level 2 - +36 Regeneration/+24 Damage/60 Mana",730,189);
               g.drawString("Level 3 - +54 Regeneration/+36 Damage/90 Mana",730,204);
               g.drawString("Level 4 - +72 Regeneration/+48 Damage/120 Mana",730,219);
               g.drawString("Current Level: " + earth[4],730,244);
               if (earth[4] == 0)
                  g.drawString("Current Effect: " + "None",730,259);
               else if (earth[4] == 1)
                  g.drawString("Current Effect: " + "+18 Regeneration/+12 Damage",730,259);
               else if (earth[4] == 2)
                  g.drawString("Current Effect: " + "+36 Regeneration/+24 Damage",730,259);
               else if (earth[4] == 3)
                  g.drawString("Current Effect: " + "+54 Regeneration/+36 Damage",730,259);
               else if (earth[4] == 4)
                  g.drawString("Current Effect: " + "+72 Regeneration/+48 Damage",730,259);
            }
         //Thunder
            if (x >= 5 && x <= 115 && y >= 520 && y <= 630)
            {
               g.drawImage(thunderPunch.getImage(),827,30,70,70,this);
               g.drawString("Bolted Punch",826,112);
               g.drawString("You strike your enemy with a charged fist,",740,137);
               g.drawString("with a chance of paralizing them.",740,149);
               g.drawString("Level 1 - +25 Damage/30% Paralysis/40 Mana ",740,174);
               g.drawString("Level 2 - +30 Damage/35% Paralysis/40 Mana ",740,189);
               g.drawString("Level 3 - +35 Damage/40% Paralysis/40 Mana ",740,204);
               g.drawString("Level 4 - +40 Damage/45% Paralysis/40 Mana ",740,219);
               g.drawString("Current Level: " + thunder[0],740,244);
               if (thunder[0] == 0)
                  g.drawString("Current Effect: " + "None",740,259);
               else if (thunder[0] == 1)
                  g.drawString("Current Effect: " + "+25 Damage/30% Paralysis",740,259);
               else if (thunder[0] == 2)
                  g.drawString("Current Effect: " + "+30 Damage/35% Paralysis",740,259);
               else if (thunder[0] == 3)
                  g.drawString("Current Effect: " + "+35 Damage/40% Paralysis",740,259);
               else if (thunder[0] == 4)
                  g.drawString("Current Effect: " + "+40 Damage/45% Paralysis",728,259);
            }
            if (x >= 125 && x <= 235 && y >= 520 && y <= 630)
            {
               g.drawImage(overload.getImage(),827,30,70,70,this);
               g.drawString("Nervous Frenzy 1",815,112);
               g.drawString("The electrical conductivity of your spinal",745,137);
               g.drawString("cord explodes, causing you to move as",745,149);
               g.drawString("fast as lightning.",745,161);
               g.drawString("Level 1 - +30% Speed/100 Mana",745,186);
               g.drawString("Level 2 - +45% Speed/100 Mana",745,201);
               g.drawString("Level 3 - +60% Speed/100 Mana",745,216);
               g.drawString("Level 4 - +75% Speed/100 Mana",745,231);
               g.drawString("Current Level: " + thunder[1],745,256);
               if (thunder[1] == 0)
                  g.drawString("Current Effect: " + "None",745,271);
               else if (thunder[1] == 1)
                  g.drawString("Current Effect: " + "+30% Speed",745,271);
               else if (thunder[1] == 2)
                  g.drawString("Current Effect: " + "+45% Speed",745,271);
               else if (thunder[1] == 3)
                  g.drawString("Current Effect: " + "+60% Speed",745,271);
               else if (thunder[1] == 4)
                  g.drawString("Current Effect: " + "+75% Speed",745,271);
            }
            if (x >= 245 && x <= 355 && y >= 520 && y <= 630)
            {
               g.drawImage(plasmafield.getImage(),827,30,70,70,this);
               g.drawString("Nervous Frenzy 2",815,112);
               g.drawString("The electrical conductivity of your cerebrum",731,137);
               g.drawString("explodes, letting you target your opponent's",731,149);
               g.drawString("weak spots at frightening accuracy.",731,161);
               g.drawString("Level 1 - 23% 1.5 x normal damage/70 Mana",731,186);
               g.drawString("Level 2 - 23% 2 x normal damage/70 Mana",731,201);
               g.drawString("Level 3 - 23% 2.5 x normal damage/70 Mana",731,216);
               g.drawString("Level 4 - 23% 3 x normal damage/70 Mana",731,231);
               g.drawString("Current Level: " + thunder[2],731,256);
               if (thunder[2] == 0)
                  g.drawString("Current Effect: " + "None",731,271);
               else if (thunder[2] == 1)
                  g.drawString("Current Effect: " + "35% 1.5 x normal damage",731,271);
               else if (thunder[2] == 2)
                  g.drawString("Current Effect: " + "35% 2 x normal damage",731,271);
               else if (thunder[2] == 3)
                  g.drawString("Current Effect: " + "35% 2.5 x normal damage",731,271);
               else if (thunder[2] == 4)
                  g.drawString("Current Effect: " + "35% 3 x normal damage",731,271);
            }
            if (x >= 365 && x <= 475 && y >= 520 && y <= 630)
            {
               g.drawImage(fusion.getImage(),827,30,70,70,this);
               g.drawString("Electrical Field 1",820,112);
               g.drawString("Your surrounding becomes charged, which",731,137);
               g.drawString("weakens your opponent's attack power while",731,149);
               g.drawString("enhancing yours.",731,161);
               g.drawString("Level 1 - Drain 10 Damage/100 Mana",731,186);
               g.drawString("Level 2 - Drain 20 Damage/100 Mana ",731,201);
               g.drawString("Level 3 - Drain 30 Damage/100 Mana ",731,216);
               g.drawString("Level 4 - Drain 40 Damage/100 Mana ",731,231);
               g.drawString("Current Level: " + thunder[3],731,256);
               if (thunder[3] == 0)
                  g.drawString("Current Effect: " + "None",731,271);
               else if (thunder[3] == 1)
                  g.drawString("Current Effect: " + "Drain 10 Damage",731,271);
               else if (thunder[3] == 2)
                  g.drawString("Current Effect: " + "Drain 20 Damage",731,271);
               else if (thunder[3] == 3)
                  g.drawString("Current Effect: " + "Drain 30 Damage",731,271);
               else if (thunder[3] == 4)
                  g.drawString("Current Effect: " + "Drain 40 Damage",731,271);
            }
            if (x >= 485 && x <= 595 && y >= 520 && y <= 630)
            {
               g.drawImage(infiniteVoltage.getImage(),827,30,70,70,this);
               g.drawString("Electrical Field 2",820,112);
               g.drawString("The surrounding becomes charged, which has",731,137);
               g.drawString("a higher chance of paralyzing your opponent",731,149);
               g.drawString("upon each attack.",731,161);
               g.drawString("Level 1 - +30% Paralysis/100 Mana",731,186);
               g.drawString("Level 2 - +35% Paralysis/100 Mana",731,201);
               g.drawString("Level 3 - +40% Paralysis/100 Mana",731,216);
               g.drawString("Level 4 - +45% Paralysis/100 Mana",731,231);
               g.drawString("Current Level: " + thunder[4],731,256);
               if (thunder[4] == 0)
                  g.drawString("Current Effect: " + "None",731,271);
               else if (thunder[4] == 1)
                  g.drawString("Current Effect: " + "+30% Paralysis",731,271);
               else if (thunder[4] == 2)
                  g.drawString("Current Effect: " + "+35% Paralysis",731,271);
               else if (thunder[4] == 3)
                  g.drawString("Current Effect: " + "+40% Paralysis",731,271);
               else if (thunder[4] == 4)
                  g.drawString("Current Effect: " + "+45% Paralysis",731,271);
            }
         
         //Stats
            if (x >= 600 && x <= 690 && y >= 0 && y <= 85)
            {
               g.drawImage(attack.getImage(),827,30,70,70,this);
               g.drawString("Attack",845,112);
               g.drawString("Increase damage by 9",810,137);
            }
            if (x >= 600 && x <= 690 && y >= 95 && y <= 185)
            {
               g.drawImage(defense.getImage(),827,30,70,70,this);
               g.drawString("Defense",840,112);
               g.drawString("Increase defense by 5",805,137); 
            }
            if (x >= 600 && x <= 690 && y >= 195 && y <= 285)
            {
               g.drawImage(speed.getImage(),827,30,70,70,this);
               g.drawString("Speed",845,112);
               g.drawString("Increase speed by 2",810,137); 
            }
            if (x >= 600 && x <= 690 && y >= 295 && y <= 385)
            {
               g.drawImage(evasion.getImage(),827,30,70,70,this);
               g.drawString("Evasion",842,112);
               g.drawString("Increase evasion by 1.5%",805,137); 
            }
            if (x >= 600 && x <= 690 && y >= 395 && y <= 485)
            {
               g.drawImage(health.getImage(),827,30,70,70,this);
               g.drawString("Health",845,112);
               g.drawString("Increase health by 106",808,137);
               g.drawString("Slightly increases health regeneration",762,152);
            } 
            if (x >= 600 && x <= 690 && y >= 495 && y <= 585)
            {
               g.drawImage(mana.getImage(),827,30,70,70,this);
               g.drawString("Mana",848,112);
               g.drawString("Increase mana by 37",808,137);
               g.drawString("Slightly increases mana regeneration",762,152);
            }
            if (x >= 600 && x <= 690 && y >= 595 && y <= 685)
            {
               g.drawImage(magicDefense.getImage(),827,30,70,70,this);
               g.drawString("Spell Resistance",818,112);
               g.drawString("Increase spell resistance by 5",780,137); 
            }
         
         //Skill Points Indicator
            g.setFont(new Font("Arial",Font.BOLD,24));
            g.drawString("Skill Points: ", 780, 400);
            g.setColor(Color.blue);
            g.drawString(""+skillPoints,940, 400);
         
         //Stats Indicator
            g.setColor(Color.black);
            g.setFont(new Font("Arial",Font.BOLD,16));
            g.drawString("Attack: ",725,450);
            g.drawString("Defense: ",725,500);
            g.drawString("Speed: ",725,550);
            g.drawString("Evasion: ",725,600);
            g.drawString("Health: ",855,450);
            g.drawString("Mana: ",855,500);
            g.drawString("Spell Res.: ",855,550);
            g.setColor(Color.blue);
            g.drawString(""+(80+ stats[0]*7),800,450);
            g.drawString(""+(10+ stats[1]*5),800,500);
            g.drawString(""+(20+ stats[2]),800,550);
            g.drawString(""+(stats[3]*0.75)+"%",800,600);
            g.drawString(""+(4256+ stats[4]*106),940,450);
            g.drawString(""+(2145+ stats[5]*37),940,500);
            g.drawString(""+(10+ stats[6]*5),940,550);
         }
      }
   
      public static void main(String[] args)
      {
         NewSkills skills = new NewSkills();
      }
   }
