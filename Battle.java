import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.applet.*;
import java.net.*;

//All the stuffs needed to play sound
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

//The Battle
public class Battle extends MouseMotionAdapter implements ActionListener,MouseListener
{
   Drawing draw = new Drawing();
	JFrame frame = new JFrame("Battle");
	int [] movesLevel = new int[20];
	JButton [] moves = new JButton[20];
	Character me = new Character(true);
	Character boss = new Character(false);
	int x,y;
	Battle battle;
	Dialogue another;
	
	//Sound playing
	private static final int	EXTERNAL_BUFFER_SIZE = 128000;
	Music battleMusic = new Music("21 Ace in the Hole.wav");

	//The evasion variable names really should be switched, but it might cause too much problems if I do it at this stage
	//I will switch their names when I get the chance.
	int evasion;//How often the boss evades
	int hisEvasion;//How often the player evades
	int bossNumber;
	int auraCounter=0;
	double damageIntakeP;
	double damageIntakeS;
	double myIntakeP;
	double myIntakeS;
	int myDamage;
	int hisDamage;
	
	//Used for some fancy battle images
	ImageIcon commandBox = new ImageIcon("commandBox.gif");
	ImageIcon hisCommandBox = new ImageIcon("hisCommandBox.gif");
	ImageIcon refight = new ImageIcon("refight.png");
	ImageIcon refightyes = new ImageIcon("refightyes.png");
	ImageIcon refightno = new ImageIcon("refightno.png");
	ImageIcon attack = new ImageIcon("attack.gif");
	ImageIcon barFrame = new ImageIcon("barFrame.png");
	ImageIcon healthBar = new ImageIcon("healthBar.gif");
	ImageIcon manaBar = new ImageIcon("manaBar.gif");
	ImageIcon actionBar = new ImageIcon("actionBar.gif");
	ImageIcon victory = new ImageIcon("victory.png");
	ImageIcon victoryCon = new ImageIcon("victoryCon.png");
	
	//Icons
	ImageIcon vampireAura = new ImageIcon("vampiricAura.gif");
	ImageIcon rampagePic = new ImageIcon("rampage.gif");
	ImageIcon hatredPic = new ImageIcon("hatred.gif");
	ImageIcon knuckle = new ImageIcon("brassKuckle.jpg");
	ImageIcon poison = new ImageIcon("poison.gif");
	ImageIcon silent = new ImageIcon("silence.gif");
	ImageIcon silenced = new ImageIcon("silenced.gif");
	ImageIcon quasOrb = new ImageIcon ("quasIcon.gif");
	ImageIcon wexOrb = new ImageIcon ("wexIcon.gif");
	ImageIcon exortOrb = new ImageIcon ("exort.jpg");
	ImageIcon shock = new ImageIcon ("shock.gif");
	ImageIcon broke = new ImageIcon ("broken.gif");
	
	//Sprite Images
	ImageIcon bat1;
	ImageIcon bat2;
	ImageIcon bat3;
	ImageIcon explosion1;
	ImageIcon explosion2;
	ImageIcon explosion3;
	ImageIcon explosion4;
	ImageIcon explosion5;
	ImageIcon explosion6;
	ImageIcon explosion7;
	ImageIcon explosion8;
	ImageIcon explosion9;
	ImageIcon explosion10;
	ImageIcon explosion11;
	ImageIcon wind1;
	ImageIcon wind2;
	ImageIcon wind3;
	ImageIcon wind4;
	ImageIcon wind5;
	ImageIcon wind6;
	ImageIcon wind7;
	ImageIcon wind8;
	ImageIcon wind9;
	ImageIcon wind10;
	ImageIcon wave1;
	ImageIcon wave2;
	ImageIcon wave3;
	ImageIcon wave4;
	ImageIcon wave5;
	ImageIcon wave6;
	ImageIcon wave7;
	ImageIcon wave8;
	ImageIcon sand1;
	ImageIcon sand2;
	ImageIcon sand3;
	ImageIcon sand4;
	ImageIcon fire1;
	ImageIcon fire2;
	ImageIcon fire3;
	ImageIcon fire4;
	ImageIcon fire5;
	ImageIcon fire6;
	ImageIcon burn1;
	ImageIcon burn2;
	ImageIcon burn3;
	ImageIcon burn4;
	ImageIcon burn5;
	ImageIcon burn6;
	ImageIcon burn7;
	ImageIcon burn8;
	ImageIcon feedback1;
	ImageIcon feedback2;
	ImageIcon feedback3;
	ImageIcon feedback4;
	ImageIcon feedback5;
	ImageIcon feedback6;
	ImageIcon feedback7;
	ImageIcon feedback8;
	ImageIcon curse1;
	ImageIcon curse2;
	ImageIcon curse3;
	ImageIcon curse4;
	ImageIcon frost1;
	ImageIcon frost2;
	ImageIcon frost3;
	ImageIcon frost4;
	ImageIcon frost5;
	ImageIcon freeze1;
	ImageIcon freeze2;
	ImageIcon freeze3;
	ImageIcon freeze4;
	ImageIcon pump1;
	ImageIcon pump2;
	ImageIcon pump3;
	ImageIcon pump4;
	ImageIcon noh;
	ImageIcon soulSteal1;
	ImageIcon soulSteal2;
	ImageIcon soulSteal3;
	ImageIcon magicCancel1;
	ImageIcon magicCancel2;
	ImageIcon magicCancel3;
	ImageIcon magicCancel4;
	ImageIcon orbQuas = new ImageIcon("quas.gif");
	ImageIcon orbWex = new ImageIcon("wex.gif");
	ImageIcon orbExort = new ImageIcon("exort.gif");
	ImageIcon breaker1;
	ImageIcon breaker2;
	ImageIcon breaker3;
	ImageIcon breaker4;
	ImageIcon breaker5;
	ImageIcon regen1 = new ImageIcon("regen1.gif");
	ImageIcon regen2 = new ImageIcon("regen2.gif");
	ImageIcon regen3 = new ImageIcon("regen3.gif");
	ImageIcon regen4 = new ImageIcon("regen4.gif");
	ImageIcon scythe1;
	ImageIcon scythe2;
	ImageIcon scythe3;
	ImageIcon scythe4;
	ImageIcon scythe5;
	ImageIcon scythe6;
	ImageIcon scythe7;
	ImageIcon scythe8;
	ImageIcon scythe9;
	ImageIcon scythe10;
	ImageIcon ritual1;
	ImageIcon ritual2;
	ImageIcon ritual3;
	ImageIcon ritual4;
	ImageIcon ritual5;
	ImageIcon ritual6;
	ImageIcon ritual7;
	ImageIcon ritual8;
	ImageIcon blessing1;
	ImageIcon blessing2;
	ImageIcon blessing3;
	ImageIcon blessing4;
	ImageIcon blessing5;
	ImageIcon blessing6;
	ImageIcon blessing7;
	ImageIcon blessing8;
	ImageIcon blessing9;
	ImageIcon blessing10;
	ImageIcon nervous1;
	ImageIcon nervous2;
	ImageIcon nervous3;
	ImageIcon nervous4;
	ImageIcon spark1;
	ImageIcon spark2;
	ImageIcon spark3;
	ImageIcon spark4;
	ImageIcon aura1;
	ImageIcon aura2;
	ImageIcon aura3;
	ImageIcon aura4;
	ImageIcon vine1;
	ImageIcon vine2;
	ImageIcon vine3;
	ImageIcon vine4;
	ImageIcon vine5;
	ImageIcon vine6;
	ImageIcon vine7;
	ImageIcon vine8;
	ImageIcon shocked1;
	ImageIcon shocked2;
	ImageIcon shocked3;
	ImageIcon shocked4;
	ImageIcon shieldPic;
	ImageIcon skull = new ImageIcon("skull.png");
	ImageIcon clock = new ImageIcon("clock.png");
	ImageIcon matchStart = new ImageIcon("matchStart.png");
	ImageIcon poisonAttack = new ImageIcon("poisonAttack.gif");
	ImageIcon quentynFigure = new ImageIcon("QuentynFigure.png");
	ImageIcon bossFigure = new ImageIcon("");
	
	//Icons for buff and moves
	ImageIcon fireBolt = new ImageIcon("fireBolt.gif");
	ImageIcon bloodLust = new ImageIcon("bloodLust.gif");
	ImageIcon mindFlame = new ImageIcon("mindFlame.gif");
	ImageIcon berserkerCry = new ImageIcon("berserkerCry.gif");
	ImageIcon chaosBlast = new ImageIcon("Chaos Blast.gif");
	ImageIcon graveChill = new ImageIcon("graveChill.gif");
	ImageIcon darkRitual = new ImageIcon("darkRitual.gif");
	ImageIcon essenceFreeze = new ImageIcon("essenceFreeze.jpg");
	ImageIcon magicCancel = new ImageIcon("magicCancel.gif");
	ImageIcon frostNova = new ImageIcon("frostNova.gif");
	ImageIcon overGrowth = new ImageIcon("overGrowth.gif");
	ImageIcon armor = new ImageIcon("livingArmor.gif");
	ImageIcon implode = new ImageIcon("implode.gif");
	ImageIcon cure = new ImageIcon("cure.gif");
	ImageIcon HolyGrace = new ImageIcon("Holy Grace.gif");
	ImageIcon thunderPunch = new ImageIcon("thunderPunch.gif");
	ImageIcon overload = new ImageIcon("overload.gif");
	ImageIcon plasmaField = new ImageIcon("plasmaField.jpg");
	ImageIcon fusion = new ImageIcon("fusion.gif");
	ImageIcon infinitVoltage = new ImageIcon("infinitVoltage.gif");
	
	//Mandatory Button
	JButton attackButton = new JButton(attack);
	
	//Percentage Increment/Reduction Effects
	int incrN1;
	int incrRM;
	int powerRM;
	
	//Paralysis
	boolean stunBoltage = false;
	boolean stunBlast = false;
	boolean stunBrass = false;
	boolean stunShock = false;
	
	//Threads
	
	//Cool Down of Action
	CoolDown myCD = new CoolDown(me.tempSP);
	CoolDown hisCD;
	
	//Regeneration of HP and MA
	Regen hisRegenHP;
	Regen hisRegenMA;
	Regen myRegenHP = new Regen(me.health, me.tempHP, me.tempRHP);
	Regen myRegenMA = new Regen(me.mana, me.tempMA, me.tempRMA);
	
	//Duration of Spells for the player
	Duration mindFlameCD = new Duration();
	Duration berserk = new Duration();
	Duration blast = new Duration();
	Duration blastCD = new Duration();
	Duration dance = new Duration();
	Duration freeze = new Duration();
	Duration shield = new Duration();
	Duration ritualCD = new Duration();
	Duration nova = new Duration();
	Duration tangle = new Duration();
	Duration tangleCD = new Duration();
	Duration cureCD = new Duration();
	Duration bless = new Duration();
	Duration boltage = new Duration();
	Duration frenzy1 = new Duration();
	Duration frenzy2 = new Duration();
	Duration field1 = new Duration();
	Duration field2 = new Duration();
	Duration counterCD = new Duration();
	Duration [] list = new Duration[20];
	boolean stunMe = false;
	
	//Duration of Spells for the AI
	Duration aura = new Duration();
	Duration legion = new Duration();
	Duration rampage = new Duration();
	Duration rampageCD = new Duration();
	Duration hatred = new Duration();
	Duration hatredCD = new Duration();
	Duration rushCD = new Duration();
	Duration brass = new Duration();
	Duration poisson = new Duration();
	Duration poissonCD = new Duration();
	Duration silence = new Duration();
	Duration silenceCD = new Duration();
	Duration timeLapseCD = new Duration();
	Duration quas = new Duration();
	Duration wex = new Duration();
	Duration exort = new Duration();
	Duration shocked = new Duration();
	Duration broken = new Duration();
	Duration [] listOp = new Duration[20];
	boolean stunOp = false;
	
	//Boss Calculation/Execution Thread
	Boss bossAI = new Boss();
	
	//Animation Threads
	Animation animation = new Animation();
	Animation boltAnimation = new Animation();
	Animation burnAnimation = new Animation();
	Animation berserkerAnimation = new Animation();
	Animation blastAnimation = new Animation();
	Animation danceAnimation = new Animation();
	Animation ritualAnimation = new Animation();
	Animation freezeAnimation = new Animation();
	Animation commandAnimation = new Animation();
	Animation frostAnimation = new Animation();
	Animation tangleAnimation = new Animation();
	Animation armourAnimation = new Animation();
	Animation implodeAnimation = new Animation();
	Animation frenzyAnimation = new Animation();
	Animation cureAnimation = new Animation();
	Animation blessingAnimation = new Animation();
	Animation stealAnimation = new Animation();
	Animation auraAnimation = new Animation();
	Animation cancelAnimation = new Animation();
	Animation legionAttack = new Animation();
	Animation rampageAnimation = new Animation();
	Animation hatredAnimation = new Animation();
	Animation poisonAnimation = new Animation();
	Animation lapseAnimation = new Animation();
	Animation bossAttackAnimation = new Animation();
	Animation attackAnimation = new Animation();
	Animation rushAnimation = new Animation();
	Animation orbAnimation = new Animation();
	Animation shockAnimation = new Animation();
	Animation regenAnimation = new Animation();
	Animation ripAnimation = new Animation();
	Animation breakerAnimation = new Animation();
	Animation challenger = new Animation();
	
	//These are used for animations
	boolean damageDealt = false;
	boolean meAttack = false;
	boolean bossAttack = false;
	boolean challenged = false;
	
	//Used for boss #3
	int [] lifeKeeper = new int [10000];
	int [] manaKeeper = new int [10000];
	
   public Battle(int number, Dialogue something)
   {
		another = something;
		boss.tempEV = 0;
		//Making the skill buttons that the player chose from the NewSkills menu
		Insets insets = frame.getInsets();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 5; j++)
				if (i == 0)
					movesLevel[i*5 +j] = NewSkills.skillFire()[j];
				else if (i == 1)
					movesLevel[i*5 +j] = NewSkills.skillIce()[j];
				else if (i == 2)
					movesLevel[i*5 +j] = NewSkills.skillEarth()[j];
				else if (i == 3)
					movesLevel[i*5 +j] = NewSkills.skillThunder()[j];
		//Checks which skills were chosen.
		for (int i = 0, j = 0, k = 0; i < 20; i++)
		{
			if (movesLevel [i] != 0)
			{
				if (i == 0)
				{
					moves[i] = new JButton(fireBolt);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("Hurls a fireball at your opponent");
					j++;
					fire1 = new ImageIcon("fire1.gif");
					fire2 = new ImageIcon("fire2.gif");
					fire3 = new ImageIcon("fire3.gif");
					fire4 = new ImageIcon("fire4.gif");
					fire5 = new ImageIcon("fire5.gif");
					fire6 = new ImageIcon("fire6.gif");
				}
				else if (i == 1)
				{
					moves[i] = new JButton(bloodLust);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("A physical attack that gets stronger when your opponent's health is low");
					j++;
				}
				else if (i == 2)
				{
					moves[i] = new JButton(mindFlame);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("Burns your opponent's magical energy");
					j++;
					burn1 = new ImageIcon("burn1.gif");
					burn2 = new ImageIcon("burn2.gif");
					burn3 = new ImageIcon("burn3.gif");
					burn4 = new ImageIcon("burn4.gif");
					burn5 = new ImageIcon("burn5.gif");
					burn6 = new ImageIcon("burn6.gif");
					burn7 = new ImageIcon("burn7.gif");
					burn8 = new ImageIcon("burn8.gif");
					mindFlameCD.start();
				}
				else if (i == 3)
				{
					moves[i] = new JButton(berserkerCry);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					berserk.start();
					moves[i].setToolTipText("Increases your attack and speed");
					j++;
					pump1 = new ImageIcon("pump1.gif");
					pump2 = new ImageIcon("pump2.gif");
					pump3 = new ImageIcon("pump3.gif");
					pump4 = new ImageIcon("pump4.gif");
					noh = new ImageIcon("noh1.png");
				}
				else if (i == 4)
				{
					moves[i] = new JButton(chaosBlast);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					blast.start();
					blastCD.start();
					moves[i].setToolTipText("Paralyzes your enemy and does some damage");
					j++;
					explosion1 = new ImageIcon("explosion1.gif");
					explosion2 = new ImageIcon("explosion2.gif");
					explosion3 = new ImageIcon("explosion3.gif");
					explosion4 = new ImageIcon("explosion4.gif");
					explosion5 = new ImageIcon("explosion5.gif");
					explosion6 = new ImageIcon("explosion6.gif");
					explosion7 = new ImageIcon("explosion7.gif");
					explosion8 = new ImageIcon("explosion8.gif");
					explosion9 = new ImageIcon("explosion9.gif");
					explosion10 = new ImageIcon("explosion10.gif");
					explosion11 = new ImageIcon("explosion11.gif");
				}
				else if (i == 5)
				{
					moves[i] = new JButton(graveChill);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					dance.start();
					moves[i].setToolTipText("Slows down your opponent and causes them to miss occasionlly");
					j++;
					curse1 = new ImageIcon("curse1.gif");
					curse2 = new ImageIcon("curse2.gif");
					curse3 = new ImageIcon("curse3.gif");
					curse4 = new ImageIcon("curse4.gif");
				}
				else if (i == 6)
				{
					moves[i] = new JButton(darkRitual);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("Sacrifices some of your life in return for magical energy");
					j++;
					ritual1 = new ImageIcon("ritual1.gif");
					ritual2 = new ImageIcon("ritual2.gif");
					ritual3 = new ImageIcon("ritual3.gif");
					ritual4 = new ImageIcon("ritual4.gif");
					ritual5 = new ImageIcon("ritual5.gif");
					ritual6 = new ImageIcon("ritual6.gif");
					ritual7 = new ImageIcon("ritual7.gif");
					ritual8 = new ImageIcon("ritual8.gif");
					ritualCD.start();
				}
				else if (i == 7)
				{
					moves[i] = new JButton(essenceFreeze);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					freeze.start();
					moves[i].setToolTipText("Slowly freezes your enemy to death");
					j++;
					wind1 = new ImageIcon("wind1.gif");
					wind2 = new ImageIcon("wind2.gif");
					wind3 = new ImageIcon("wind3.gif");
					wind4 = new ImageIcon("wind4.gif");
					wind5 = new ImageIcon("wind5.gif");
					wind6 = new ImageIcon("wind6.gif");
					wind7 = new ImageIcon("wind7.gif");
					wind8 = new ImageIcon("wind8.gif");
					wind9 = new ImageIcon("wind9.gif");
					wind10 = new ImageIcon("wind10.gif");
				}
				else if (i == 8)
				{
					moves[i] = new JButton(magicCancel);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					counterCD.start();
					moves[i].setToolTipText("Interrupts your opponent in channeling a spell");
					j++;
					feedback1 = new ImageIcon("feedback1.gif");
					feedback2 = new ImageIcon("feedback2.gif");
					feedback3 = new ImageIcon("feedback3.gif");
					feedback4 = new ImageIcon("feedback4.gif");
					feedback5 = new ImageIcon("feedback5.gif");
					feedback6 = new ImageIcon("feedback6.gif");
					feedback7 = new ImageIcon("feedback7.gif");
					feedback8 = new ImageIcon("feedback8.gif");
				}
				else if (i == 9)
				{
					moves[i] = new JButton(frostNova);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					nova.start();
					moves[i].setToolTipText("Blasts your enemy with a barrage of frosts that slows down his movement");
					j++;
					frost1 = new ImageIcon("frost1.gif");
					frost2 = new ImageIcon("frost2.gif");
					frost3 = new ImageIcon("frost3.gif");
					frost4 = new ImageIcon("frost4.gif");
					frost5 = new ImageIcon("frost5.gif");
					freeze1 = new ImageIcon("freeze1.gif");
					freeze2 = new ImageIcon("freeze2.gif");
					freeze3 = new ImageIcon("freeze3.gif");
					freeze4 = new ImageIcon("freeze4.gif");
				}
				else if (i == 10)
				{
					moves[i] = new JButton(overGrowth);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					tangle.start();
					moves[i].setToolTipText("Tangle your enemy with damaging tree vines");
					j++;
					vine1 = new ImageIcon("vine1.gif");
					vine2 = new ImageIcon("vine2.gif");
					vine3 = new ImageIcon("vine3.gif");
					vine4 = new ImageIcon("vine4.gif");
					vine5 = new ImageIcon("vine5.gif");
					vine6 = new ImageIcon("vine6.gif");
					vine7 = new ImageIcon("vine7.gif");
					vine8 = new ImageIcon("vine8.gif");
					tangleCD.start();
				}
				else if (i == 11)
				{
					moves[i] = new JButton(armor);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					shield.start();
					moves[i].setToolTipText("Greatly increases your magical and physical defense");
					j++;
					shieldPic = new ImageIcon("shield.png");
				}
				else if (i == 12)
				{
					moves[i] = new JButton(implode);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("Does more damage and cost more mana the less health you have");
					j++;
					wave1 = new ImageIcon("wave1.gif");
					wave2 = new ImageIcon("wave2.gif");
					wave3 = new ImageIcon("wave3.gif");
					wave4 = new ImageIcon("wave4.gif");
					wave5 = new ImageIcon("wave5.gif");
					wave6 = new ImageIcon("wave6.gif");
					wave7 = new ImageIcon("wave7.gif");
					wave8 = new ImageIcon("wave8.gif");
				}
				else if (i == 13)
				{
					moves[i] = new JButton(cure);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					moves[i].setToolTipText("Heals your life");
					cureCD.start();
					j++;
				}
				else if (i == 14)
				{
					moves[i] = new JButton(HolyGrace);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					bless.start();
					moves[i].setToolTipText("Increases your health regeneration and attack");
					j++;
					blessing1 = new ImageIcon("blessing1.gif");
					blessing2 = new ImageIcon("blessing2.gif");
					blessing3 = new ImageIcon("blessing3.gif");
					blessing4 = new ImageIcon("blessing4.gif");
					blessing5 = new ImageIcon("blessing5.gif");
					blessing6 = new ImageIcon("blessing6.gif");
					blessing7 = new ImageIcon("blessing7.gif");
					blessing8 = new ImageIcon("blessing8.gif");
					blessing9 = new ImageIcon("blessing9.gif");
					blessing10 = new ImageIcon("blessing10.gif");
				}
				else if (i == 15)
				{
					moves[i] = new JButton(thunderPunch);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					boltage.start();
					moves[i].setToolTipText("Attack your opponent with a likelyhood of paralyzing him");
					j++;
				}
				else if (i == 16)
				{
					moves[i] = new JButton(overload);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					frenzy1.start();
					moves[i].setToolTipText("Increases your speed");
					j++;
					nervous1 = new ImageIcon("frenzy1.gif");
					nervous2 = new ImageIcon("frenzy2.gif");
					nervous3 = new ImageIcon("frenzy3.gif");
					nervous4 = new ImageIcon("frenzy4.gif");
					spark1 = new ImageIcon("spark1.gif");
					spark2 = new ImageIcon("spark2.gif");
					spark3 = new ImageIcon("spark3.gif");
					spark4 = new ImageIcon("spark4.gif");
				}
				else if (i == 17)
				{
					moves[i] = new JButton(plasmaField);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					frenzy2.start();
					moves[i].setToolTipText("Lets you do critical hit");
					j++;
					nervous1 = new ImageIcon("frenzy1.gif");
					nervous2 = new ImageIcon("frenzy2.gif");
					nervous3 = new ImageIcon("frenzy3.gif");
					nervous4 = new ImageIcon("frenzy4.gif");
					spark1 = new ImageIcon("spark1.gif");
					spark2 = new ImageIcon("spark2.gif");
					spark3 = new ImageIcon("spark3.gif");
					spark4 = new ImageIcon("spark4.gif");
				}
				else if (i == 18)
				{
					moves[i] = new JButton(fusion);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					field1.start();
					moves[i].setToolTipText("Steals some attack from your opponent");
					j++;
					nervous1 = new ImageIcon("frenzy1.gif");
					nervous2 = new ImageIcon("frenzy2.gif");
					nervous3 = new ImageIcon("frenzy3.gif");
					nervous4 = new ImageIcon("frenzy4.gif");
					spark1 = new ImageIcon("spark1.gif");
					spark2 = new ImageIcon("spark2.gif");
					spark3 = new ImageIcon("spark3.gif");
					spark4 = new ImageIcon("spark4.gif");
				}
				else if (i == 19)
				{
					moves[i] = new JButton(infinitVoltage);
					frame.add(moves[i]);
					moves[i].addActionListener(this);
					moves[i].setBounds(j*100+357 + insets.left, 510+k*100 + insets.top,70, 70);
					field2.start();
					boltage.start();
					moves[i].setToolTipText("Gives you the possbility to paralyze your opponent with each physical attack");
					j++;
					nervous1 = new ImageIcon("frenzy1.gif");
					nervous2 = new ImageIcon("frenzy2.gif");
					nervous3 = new ImageIcon("frenzy3.gif");
					nervous4 = new ImageIcon("frenzy4.gif");
					spark1 = new ImageIcon("spark1.gif");
					spark2 = new ImageIcon("spark2.gif");
					spark3 = new ImageIcon("spark3.gif");
					spark4 = new ImageIcon("spark4.gif");
				}
				if (j == 3)
				{
					k++;
					j = 0;
				}
			}
		}
		
		myIntakeP = 1-Math.sqrt(me.tempDE)/20;
		myIntakeS = 1-Math.sqrt(me.tempDE)/20;
		
		//Declaring the stats of the Boss. Different bossNumber represents the different variations of bosses
		//refer to the boss/AI thread for the abilities of each boss and its corresponding bossNumber
		bossNumber = number;
		if (bossNumber == 1)
		{
			boss.attack = 115;
			boss.defense = 14;
			boss.speed = 30;
			boss.evasion = 18;
			boss.health = 4280;
			boss.mana = 2145;
			boss.magicalDefense = 20;
			boss.regHP = 17;
			boss.regMA = 11;
			boss.tempAT = 115;
			boss.tempDE = 14;
			boss.tempSP = 30;
			boss.tempEV = 18;
			boss.tempHP = 4280;
			boss.tempMA = 2145;
			boss.tempMD = 20;
			boss.tempRHP = 17;
			boss.tempRMA = 11;
			aura = new Duration();
			aura.start();
			legion.start();
			bossFigure = new ImageIcon("DracoFigure.png");
			bat1 = new ImageIcon("batSprite1.gif");
			bat2 = new ImageIcon("batSprite2.gif");
			bat3 = new ImageIcon("batSprite3.gif");
			soulSteal1 = new ImageIcon("soulSteal1.gif");
			soulSteal2 = new ImageIcon("soulSteal2.gif");
			soulSteal3 = new ImageIcon("soulSteal3.gif");
			aura1 = new ImageIcon("aura1.gif");
			aura2 = new ImageIcon("aura2.gif");
			aura3 = new ImageIcon("aura3.gif");
			aura4 = new ImageIcon("aura4.gif");
			magicCancel1 = new ImageIcon("cancel1.gif");
			magicCancel2 = new ImageIcon("cancel2.gif");
			magicCancel3 = new ImageIcon("cancel3.gif");
			magicCancel4 = new ImageIcon("cancel4.gif");
		}
		else if (bossNumber == 2)
		{
			boss.attack = 145;
			boss.defense = 67;
			boss.speed = 30;
			boss.evasion = 0;
			boss.health = 6439;
			boss.mana = 1850;
			boss.magicalDefense = 54;
			boss.regHP = 21;
			boss.regMA = 13;
			boss.tempAT = 145;
			boss.tempDE = 84;
			boss.tempSP = 30;
			boss.tempEV = 0;
			boss.tempHP = 6439;
			boss.tempMA = 1850;
			boss.tempMD = 54;
			boss.tempRHP = 17;
			boss.tempRMA = 13;
			rampage.start();
			rampageCD.start();
			rushCD.start();
			hatred.start();
			hatredCD.start();
			bossFigure = new ImageIcon("");
			sand1 = new ImageIcon("sand1.gif");
			sand2 = new ImageIcon("sand2.gif");
			sand3 = new ImageIcon("sand3.gif");
			sand4 = new ImageIcon("sand4.gif");
		}
		else if (bossNumber == 3)
		{
			boss.attack = 124;
			boss.defense = 3;
			boss.speed = 50;
			boss.evasion = 28;
			boss.health = 3963;
			boss.mana = 1887;
			boss.magicalDefense = 3;
			boss.regHP = 13;
			boss.regMA = 9;
			boss.tempAT = 124;
			boss.tempDE = 3;
			boss.tempSP = 50;
			boss.tempEV = 28;
			boss.tempHP = 3963;
			boss.tempMA = 1887;
			boss.tempMD = 3;
			boss.tempRHP = 13;
			boss.tempRMA = 9;
			brass.start();
			poisson.start();
			poissonCD.start();
			silence.start();
			silenceCD.start();
			timeLapseCD.start();
			bossFigure = new ImageIcon("ChronosFigure.png");
		}
		else if (bossNumber == 4)
		{
			boss.attack = 90;
			boss.defense = 3;
			boss.speed = 30;
			boss.evasion = 7;
			boss.health = 3945;
			boss.mana = 3327;
			boss.magicalDefense = 70;
			boss.regHP = 13;
			boss.regMA = 21;
			boss.tempAT = 90;
			boss.tempDE = 3;
			boss.tempSP = 30;
			boss.tempEV = 7;
			boss.tempHP = 3945;
			boss.tempMA = 3327;
			boss.tempMD = 70;
			boss.tempRHP = 13;
			boss.tempRMA = 21;
			quas.start();
			wex.start();
			exort.start();
			shocked.start();
			broken.start();
			orbAnimation.start();
			bossFigure = new ImageIcon("MageFigure.png");
			scythe1 = new ImageIcon("scythe1.gif");
			scythe2 = new ImageIcon("scythe2.gif");
			scythe3 = new ImageIcon("scythe3.gif");
			scythe4 = new ImageIcon("scythe4.gif");
			scythe5 = new ImageIcon("scythe5.gif");
			scythe6 = new ImageIcon("scythe6.gif");
			scythe7 = new ImageIcon("scythe7.gif");
			scythe8 = new ImageIcon("scythe8.gif");
			scythe9 = new ImageIcon("scythe9.gif");
			scythe10 = new ImageIcon("scythe10.gif");
			breaker1 = new ImageIcon("breaker.gif");
			breaker2 = new ImageIcon("breaker2.gif");
			breaker3 = new ImageIcon("breaker3.gif");
			breaker4 = new ImageIcon("breaker4.gif");
			breaker5 = new ImageIcon("breaker5.gif");
			shocked1 = new ImageIcon("shocked1.gif");
			shocked2 = new ImageIcon("shocked2.gif");
			shocked3 = new ImageIcon("shocked3.gif");
			shocked4 = new ImageIcon("shocked4.gif");
		}
		//calculates the amount of damage the AI would take from magical and physical attack
		damageIntakeP = 1-Math.sqrt(boss.tempDE)/20;
		damageIntakeS = 1-Math.sqrt(boss.tempMD)/20;
		//Makes the thread that keeps track of the AI's health and mana, and its regenerations
		hisRegenHP = new Regen(boss.health, boss.tempHP, boss.tempRHP);
		hisRegenMA = new Regen(boss.mana, boss.tempMA, boss.tempRMA);
		//Makes the thread that keeps track of the AI's speed and the cool down
		hisCD = new CoolDown(boss.tempSP);
		
		//All the basic declarations
		frame.add(attackButton);
		attackButton.addActionListener(this);
		attackButton.setBounds(557 + insets.left, 610 + insets.top,70, 70);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
      frame.setSize(1025,725);  // set the size of the window to whatever width and height you like
      frame.add(draw); // put an object we can draw on in the centre of the window
      frame.setVisible(true); //show the window
		frame.setResizable(false);
		draw.addMouseMotionListener(this);
		draw.addMouseListener(this);
		
		//Threads declarations;
		myCD.start();
		hisCD.start();
		hisRegenHP.start();
		hisRegenMA.start();
		myRegenHP.start();
		myRegenMA.start();
		bossAI.start();
		battleMusic.start();
		challenged = true;
		challenger.start();
   }

	//Decide whether a player would evade an attack or not. 1 is not evaded, and 0 is evaded
	public int evade(int evasion)
	{
		if (Math.random()*100 > evasion)
			return 1;
		else
			return 0;
	}
	//Methodsd that inserts the buff to the display
	public void insertBuff (Duration buff)
	{
		for (int i = 0; i < 20; i++)
			if (list[i] == buff)
				for (int j = i; j < 20; j ++)
					if (j != 19)
						list[j] = list[j+1];
					else
						list[j] = null;
		boolean found = false;
		for (int i = 0; i < 20 && !found; i++)
			if (list[i] == null)
			{
				list[i] = buff;
				found = true;
			}
			else if (list[i].duration > buff.duration)
			{
				for (int j = 19; j > i; j--)
					list[j] = list[j - 1];
				list[i] = buff;
				found = true;
			}
	}
	public void insertBuffOp (Duration buff)
	{
		for (int i = 0; i < 20; i++)
			if (listOp[i] == buff)
				for (int j = i; j < 20; j ++)
					if (j != 19)
						listOp[j] = listOp[j+1];
					else
						listOp[j] = null;
		boolean found = false;
		for (int i = 0; i < 20 && !found; i++)
			if (listOp[i] == null)
			{
				listOp[i] = buff;
				found = true;
			}
			else if (listOp[i].duration > buff.duration)
			{
				for (int j = 19; j > i; j--)
					listOp[j] = listOp[j - 1];
				listOp[i] = buff;
				found = true;
			}
	}
	
	//Receives x and y coordinate, only used at the end of a battle
	public void mouseMoved(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		draw.repaint();
	}
	
	public void mouseClicked(MouseEvent e)
	{
		//After the game ends, the player can choose to fight again or stop the fight
		if (myRegenHP.temp == 0)
		{
			//Fights again
			if (x >= 411 && x <= 478 && y >= 388 && y <= 422)
			{
				battleMusic.line.close();
				battleMusic.interrupt();
				battleMusic = null;
				battle = new Battle(bossNumber,another);
				frame.setVisible(false);
			}
			//Stops fighting, returns to the story
			else if (x >= 549 && x <= 615 && y >= 388 && y <= 422)
			{
				battleMusic.line.close();
				battleMusic.interrupt();
				battleMusic = null;
				another.battleFinished = true;
				//frame.setVisible(false);
				another.frame.setVisible(true);
				another.draw.repaint();
				battle = null;
			}
		}
		//Returns to the story after winning a battle
		if (hisRegenHP.temp == 0)
		{
			if (x >= 424 && x <= 601 && y >= 380 && y <= 415)
			{
				battleMusic.line.close();
				battleMusic.interrupt();
				battleMusic = null;
				another.battleFinished = true;
				//frame.setVisible(false);
				another.frame.setVisible(true);
				another.draw.repaint();
				battle = null;
			}
		}
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public void mousePressed(MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	//The ability of each skill
	public void actionPerformed (ActionEvent e)
	{
		//calculate evadability
		evasion =evade(boss.tempEV);
		damageIntakeP = 1-Math.sqrt(boss.tempDE)/20;
		damageIntakeS = 1-Math.sqrt(boss.tempMD)/20;
		//////////////////////Action-Independent Spells
		//Cryptic Command-If the AI is going to cast a spell, the player can use this to return the AI's action bar to 0 and burn 100 mana
		if (moves[8] == e.getSource() && myRegenMA.temp >= 75 && hisCD.coolDown <= 20 + movesLevel[8]*20 && myRegenHP.temp > 0)
		{
			if (bossAI.action > 1)
			{
				if (hisRegenMA.temp <= 100)
					hisRegenMA.temp = 0;
				else
					hisRegenMA.temp -= 100;
				hisCD.coolDown = 0;
			}
			myRegenMA.temp -= 75;
			counterCD.duration = 11-movesLevel[8];
			moves[8].setEnabled(false);
			commandAnimation = new Animation();
			commandAnimation.start();
		}
		//Mind Flame-burns AI's mana
		else if (moves[2] == e.getSource() && myRegenMA.temp >= 30*movesLevel[2])
		{
			if (hisRegenMA.temp < 50+25*movesLevel[2])
			{
				if (hisRegenHP.temp < (int)((50+25*movesLevel[2]-hisRegenMA.temp)*damageIntakeS))
				{
					hisRegenHP.temp = 0;
					hisRegenMA.temp = 0;
				}
				else
				{
					hisRegenHP.temp -= (int)((50+25*movesLevel[2]-hisRegenMA.temp)*damageIntakeS);
					hisRegenMA.temp = 0;
				}
			}
			else
				hisRegenMA.temp -= 50+25*movesLevel[2];
			mindFlameCD.duration = 8;
			moves[2].setEnabled(false);
			myRegenMA.temp -= 30*movesLevel[2];
			myDamage = 50+25*movesLevel[2];
			burnAnimation = new Animation();
			burnAnimation.start();
		}
		//Chaos Blast-Does straight damage and paralyzes the AI
		else if (moves[4] == e.getSource() && myRegenMA.temp > 70+40*movesLevel[4])
		{
			if (hisRegenHP.temp < (int)((movesLevel[4]*50+100)*damageIntakeP)*evasion)
				hisRegenHP.temp = 0;
			else
				hisRegenHP.temp -= (int)((movesLevel[4]*50+100)*damageIntakeP)*evasion;
			blast.active = true;
			blast.duration = movesLevel[4];
			myDamage = (int)((movesLevel[4]*50+100)*damageIntakeP)*evasion;
			blastAnimation = new Animation();
			blastAnimation.start();
			myRegenMA.temp -=70+40*movesLevel[4];
			blastCD.duration = 30;
			insertBuffOp(blast);
			moves[4].setEnabled(false);
		}
		//Dark Ritual-refer to animation section
		else if (moves[6] == e.getSource() && myRegenMA.temp >= 50 && myRegenHP.temp > 100)
		{
			damageDealt = false;
			ritualCD.duration = 8;
			moves[6].setEnabled(false);
			myRegenMA.temp -= 50;
			ritualAnimation = new Animation();
			ritualAnimation.start();
		}
		//Tangle-slows down the enemy and causes them to slowly lose health for a period of time
		else if (moves[10] == e.getSource() && myRegenMA.temp >= 50)
		{
			if(tangle.duration == 0)
			{
				hisRegenHP.rate -= 20+10*movesLevel[10];
				tangle.duration = 3;
				tangle.active = true;
			}
			else
				tangle.duration = 3;
			tangleCD.duration = 10;
			moves[10].setEnabled(false);
			myRegenMA.temp -= 50;
			insertBuffOp(tangle);
			tangleAnimation = new Animation();
			tangleAnimation.start();
		}
		//Heal-regain some health back
		else if (moves[13] == e.getSource() && myRegenMA.temp >= 100+25*movesLevel[13])
		{
			if (myRegenHP.max < myRegenHP.temp+100*movesLevel[13])
				myRegenHP.temp = myRegenHP.max;
			else
				myRegenHP.temp += 100*movesLevel[13];
			myRegenMA.temp -= 100+25*movesLevel[13];
			cureAnimation = new Animation();
			cureAnimation.start();
			cureCD.duration = 8;
			moves[13].setEnabled(false);
		}
		
		/////////////////////////Action-Dependent Moves/////////////////////////
		//A normal attack
		if (attackButton == e.getSource() && myCD.coolDown == 100 && myRegenHP.temp > 0)
		{
			//For Critial Damage
			if (frenzy2.active && Math.random()*100 <= 23)
			{
				if (hisRegenHP.temp < (int)((me.tempAT*(movesLevel[17]*0.5+1.0))*damageIntakeP))
					hisRegenHP.temp =0;
				else
					hisRegenHP.temp -= (int)((me.tempAT*(movesLevel[17]*0.5+1.0))*damageIntakeP);
				myDamage = (int)((me.tempAT*(movesLevel[17]*0.5+1.0))*damageIntakeP);
				meAttack = true;
				attackAnimation.start();
			}
			//For Normal Damage
			else
			{
				if (hisRegenHP.temp < (int)(me.tempAT*damageIntakeP)*evasion)
					hisRegenHP.temp =0;
				else
					hisRegenHP.temp -= (int)(me.tempAT*damageIntakeP)*evasion;
				myDamage = (int)(me.tempAT*damageIntakeP)*evasion;
				meAttack = true;
				attackAnimation.start();
			}
			//For Electric Field 2-randomly paralyzes the AI at each regular attack
			if (movesLevel[19]*5+25 >= Math.random()*100 && evasion == 1 && field2.duration > 0)
			{
				boltage.active = true;
				boltage.duration = 1;
				insertBuffOp(boltage);
			}
			myCD.coolDown = 0;
		}
		
		//Special Abilities
		for (int i = 0; i < 20; i++)
			if (movesLevel[i] != 0 && moves[i] == e.getSource() && myCD.coolDown == 100 && myRegenHP.temp > 0)
			{
				//Fire Bolt-refer to animation section
				if (i == 0 && myRegenMA.temp >= 75)
				{
					boltAnimation = new Animation();
					boltAnimation.start();
					myRegenMA.temp -= 75;
					myCD.coolDown =0;
				}
				
				//Blood Lust-does more damage based on how much health the AI lost
				else if (i == 1 && myRegenMA.temp >= 50+movesLevel[i]*30)
				{
					if (hisRegenHP.temp <(int)(((boss.health - hisRegenHP.temp)*(0.015*movesLevel[i])+me.tempAT)*damageIntakeP)*evasion)
						hisRegenHP.temp = 0;
					else
						hisRegenHP.temp -= (int)(((boss.health - hisRegenHP.temp)*(0.015*movesLevel[i])+me.tempAT)*damageIntakeP)*evasion;
					if (movesLevel[19]*5+5 >= Math.random()*100 && evasion == 1 && field2.duration > 0)
					{
						boltage.active = true;
						boltage.duration = 1;
						insertBuffOp(boltage);
						boltage.duration = 1;
					}
					myDamage = (int)(((boss.health - hisRegenHP.temp)*(0.015*movesLevel[i])+me.tempAT)*damageIntakeP)*evasion;
					myRegenMA.temp -= 50+movesLevel[i]*30;
					myCD.coolDown =0;
					meAttack = true;
					attackAnimation.start();
				}
				
				//Berserker Cry-Increase attack and speed
				else if (i == 3 && myRegenMA.temp >= 150)
				{
					if(berserk.duration == 0)
					{
						me.tempAT += 15*movesLevel[i];
						berserk.duration = 15;
						berserk.active = true;
					}
					else
						berserk.duration = 15;
					myRegenMA.temp -= 150;
					insertBuff(berserk);
					myCD.coolDown =0;
					berserkerAnimation = new Animation();
					berserkerAnimation.start();
				}
				
				//Shadow Dance-reduces AI's speed and increase its chance of missing in each attack
				else if (i == 5 && myRegenMA.temp >= 100)
				{
					if(dance.duration == 0)
					{
						me.tempEV += 4*movesLevel[i];
						dance.duration = 15;
						insertBuffOp(dance);
						dance.active = true;
					}
					else
						dance.duration = 15;
					myRegenMA.temp -= 100;
					myCD.coolDown =0;
					danceAnimation = new Animation();
					danceAnimation.start();
				}
				
				//Essence Freeze-causes the AI to slowly lose life for a long period of time, if the AI's health reaches below 200
				//while this is in effect, the AI dies immediately
				else if (i == 7 && myRegenMA.temp >= 100)
				{
					if(freeze.duration == 0)
					{
						hisRegenHP.rate -= 16*movesLevel[i];
						freeze.duration = 30;
						freeze.active = true;
					}
					else
						freeze.duration = 30;
					myRegenMA.temp -= 100;
					insertBuffOp(freeze);
					myCD.coolDown =0;
					freezeAnimation = new Animation();
					freezeAnimation.start();
				}
				
				//Frost Nova-refer to animation section
				else if (i == 9 && myRegenMA.temp >= 80 + 30*movesLevel[i])
				{
					myRegenMA.temp -= 80 + 30*movesLevel[9];
					myCD.coolDown =0;
					frostAnimation = new Animation();
					frostAnimation.start();
					damageDealt = false;
				}
				
				//Living Armour-increases magical and physical defense
				else if (i == 11 && myRegenMA.temp >= 75)
				{
					if(shield.duration == 0)
					{
						me.tempDE += 12*movesLevel[i];
						me.tempMD += 12*movesLevel[i];
						shield.duration = 30;
						shield.active = true;
					}
					else
						shield.duration = 30;
					myRegenMA.temp -= 75;
					insertBuff(shield);
					myCD.coolDown =0;
					armourAnimation = new Animation();
					armourAnimation.start();
				}
				
				//Implode-refer to animation section
				else if (i == 12 && myRegenMA.temp >= (int)(600-600.0*myRegenHP.temp/myRegenHP.max))
				{
					myRegenMA.temp -= (int)(600-600.0*myRegenHP.temp/myRegenHP.max);
					myCD.coolDown =0;
					implodeAnimation = new Animation();
					implodeAnimation.start();
				}
				
				//Angelic Blessing-increases attack and health regeneration rate for a short period of time
				else if (i == 14 && myRegenMA.temp >= 30*movesLevel[i])
				{
					if(bless.duration == 0)
					{
						me.tempAT += 12*movesLevel[i];
						myRegenHP.rate += 18*movesLevel[i];
						bless.duration = 12;
						bless.active = true;
					}
					else
						bless.duration = 12;
					myRegenMA.temp -= 30*movesLevel[i];
					insertBuff(bless);
					myCD.coolDown =0;
					blessingAnimation = new Animation();
					blessingAnimation.start();
				}
				
				//Bolted Punch-a slightly stronger version of normal attack that randomly paralyzes the AI
				else if (i == 15 && myRegenMA.temp >= 40)
				{
					if (hisRegenHP.temp <(int)((20+5*movesLevel[i]+me.tempAT)*damageIntakeP)*evasion)
						hisRegenHP.temp = 0;
					else
						hisRegenHP.temp -= (int)((20+5*movesLevel[i]+me.tempAT)*damageIntakeP)*evasion;
					if (Math.random()*100 <= movesLevel[i]*5+25 && evasion == 1)
					{
						boltage.active = true;
							boltage.duration = 1;
						insertBuffOp(boltage);
					}
					myDamage = (int)((20+5*movesLevel[i]+me.tempAT)*damageIntakeP)*evasion;
					myRegenMA.temp -= 40;
					myCD.coolDown =0;
					meAttack = true;
					attackAnimation.start();
				}
				
				//Nervous Frenzy 1-increase speed for a short period of time
				else if (i == 16 && myRegenMA.temp >= 100)
				{
					frenzy1.active = true;
					frenzy1.duration = 15;
					myRegenMA.temp -= 100;
					insertBuff(frenzy1);
					myCD.coolDown =0;
					frenzyAnimation = new Animation();
					frenzyAnimation.start();
				}
				
				//Nervous Frenzy 2-give the player chances of doing more damage on regular attacks
				else if (i == 17 && myRegenMA.temp >= 70)
				{
					if(frenzy2.duration == 0)
					{
						frenzy2.duration = 20;
						frenzy2.active = true;
					}
					else
						frenzy2.duration = 20;
					myRegenMA.temp -= 70;
					insertBuff(frenzy2);
					myCD.coolDown =0;
					frenzyAnimation = new Animation();
					frenzyAnimation.start();
				}
				
				//Electric Field 1-steals attack power from the AI for a short period of time
				else if (i == 18 && myRegenMA.temp >= 100)
				{
					if(field1.duration == 0)
					{
						me.tempAT += 10*movesLevel[i];
						boss.tempAT -= 10*movesLevel[i];
						field1.duration = 20;
						field1.active = true;
					}
					else
						field1.duration = 20;
					myRegenMA.temp -= 100;
					insertBuff(field1);
					insertBuffOp(field1);
					myCD.coolDown =0;
					frenzyAnimation = new Animation();
					frenzyAnimation.start();

				}
				
				//Electric Field 2-for a period of time, each regular attack has a chance of paralyzing the AI
				else if (i == 19 && myRegenMA.temp >= 100)
				{
					if(field2.duration == 0)
					{
						field2.duration = 20;
						field2.active = true;
					}
					else
						field2.duration = 20;
					myRegenMA.temp -= 70;
					insertBuff(field2);
					myCD.coolDown =0;
					frenzyAnimation = new Animation();
					frenzyAnimation.start();
				}
			}
				
		draw.repaint();			
	}
   
	
	//////////////////////////////////////DRAWING CLASS/////////////////////////////////////////
   class Drawing extends JComponent
   {
      public void paint(Graphics g)
      {
			//If Essence Freeze is active while the opponent's life is below 200, the opponent dies immediately
			if (freeze.duration > 0 && hisRegenHP.temp <= 200)
				hisRegenHP.temp = 0;
			//Drawing the picture of the player and boss
			if (bossNumber == 1)
				g.drawImage(bossFigure.getImage(),300,225,84,170,this);
			else if (bossNumber == 3)
				g.drawImage(bossFigure.getImage(),300,225,61,192,this);
			else if (bossNumber == 4)
				g.drawImage(bossFigure.getImage(),300,225,85,151,this);
			g.drawImage(quentynFigure.getImage(),650,400,68,183,this);
			//The orb effects of the 3rd boss
			if (quas.active)
				g.drawImage(orbQuas.getImage(),375+(int)(50*Math.cos(orbAnimation.time/1000.0)),275+(int)(50*Math.sin(orbAnimation.time/1000.0)),81,82,this);
			if (wex.active)
				g.drawImage(orbWex.getImage(),375+(int)(50*Math.cos(orbAnimation.time/700.0)),275+(int)(50*Math.sin(orbAnimation.time/700.0)),37,46,this);
			if (exort.active)
				g.drawImage(orbExort.getImage(),375+(int)(50*Math.cos(orbAnimation.time/400.0)),275+(int)(50*Math.sin(orbAnimation.time/400.0)),81,82,this);
			
			//Game Ends if the player reaches 0 life
			if (myRegenHP.temp == 0)
			{
				myRegenHP.interrupt();
				hisRegenHP.interrupt();
				bossAI.interrupt();
				if (x >= 411 && x <= 478 && y >= 388 && y <= 422)
					g.drawImage(refightyes.getImage(),373,292,279,142,this);
				else if (x >= 549 && x <= 615 && y >= 388 && y <= 422)
					g.drawImage(refightno.getImage(),373,292,279,142,this);
				else
					g.drawImage(refight.getImage(),373,292,279,142,this);
			}
			//Game ends if the opponent reaches 0 life
			else if (hisRegenHP.temp == 0)
			{
				myRegenHP.interrupt();
				hisRegenHP.interrupt();
				bossAI.interrupt();
				quas.interrupt();
				if (lapseAnimation.time > 0)
				{
					lapseAnimation.interrupt();
					lapseAnimation = new Animation();
				}
				if (x >= 424 && x <= 601 && y >= 380 && y <= 415)
					g.drawImage(victoryCon.getImage(),373,292,279,142,this);
				else
					g.drawImage(victory.getImage(),373,292,279,142,this);
			}
			
			//Duration-The effect of certain spells wears off
			g.setFont(new Font("Arial",Font.BOLD,16));
			g.setColor(Color.black);
			
			//Berserk
			if (berserk.duration == 0 && berserk.active)
			{
				me.tempAT -= 15*movesLevel[3];
				berserk.active = false;
			}
			
			//Mind Flame
			if (mindFlameCD.duration == 0 && moves[2] != null && !silence.active && silence.duration == 0)
			{
				moves[2].setEnabled(true);
			}
			
			//Chaos Blast
			if (blast.duration == 0 && blast.active)
			{
				stunBlast = false;
				blast.active = false;
			}
			
			if (blastCD.duration == 0 && moves[4] != null && !silence.active && silence.duration == 0)
			{
				moves[4].setEnabled(true);
			}
			
			//Shadow Dance
			if (dance.duration == 0 && dance.active)
			{
				me.tempEV -= 4*movesLevel[5];
				dance.active = false;
			}
			
			//Dark Ritual
			if (ritualCD.duration == 0 && moves[6] != null && !silence.active && silence.duration == 0)
			{
				moves[6].setEnabled(true);
			}
			
			//Essence Freeze
			if (freeze.duration == 0 && freeze.active)
			{
				hisRegenHP.rate += 16*movesLevel[7];
				freeze.active = false;
			}
			
			//Cryptic Command
			if (counterCD.duration == 0 && moves[8] != null)
			{
				moves[8].setEnabled(true);
			}
			
			//Frost Nova
			if (nova.duration == 0 && nova.active)
			{
				nova.active = false;
			}
			
			//Tangle
			if (tangle.duration == 0 && tangle.active)
			{
				hisRegenHP.rate += 20+10*movesLevel[10];
				tangle.active = false;
			}
			
			if (tangleCD.duration == 0 && moves[10] != null && !silence.active && silence.duration == 0)
			{
				moves[10].setEnabled(true);
			}
			
			//Living Armour
			if (shield.duration == 0 && shield.active)
			{
				me.tempDE -= 12*movesLevel[11];
				me.tempMD -= 12*movesLevel[11];
				shield.active = false;
			}
			
			//Heal
			if (cureCD.duration == 0 && moves[13] != null && !silence.active && silence.duration == 0)
			{
				moves[13].setEnabled(true);
			}
			
			//Angelic Blessing
			if (bless.duration == 0 && bless.active)
			{
				me.tempAT -= 12*movesLevel[14];
				myRegenHP.rate -= 18*movesLevel[14];
				bless.active = false;
			}
			
			//Bolted Punch
			if (boltage.duration == 0 && boltage.active)
			{
				stunBoltage = false;
				boltage.active = false;
			}
			
			//Nervous Frenzy 1
			if (frenzy1.duration == 0 && frenzy1.active)
			{
				frenzy1.active = false;
			}
			
			//Nervous Frenzy 2
			if (frenzy2.duration == 0 && frenzy2.active)
			{
				frenzy2.active = false;
			}
			
			//Electric Field 1
			if (field1.duration == 0 && field1.active)
			{
				me.tempAT -= 10*movesLevel[18];
				boss.tempAT += 10*movesLevel[18];
				field1.active = false;
			}
			
			//Electric Field 2
			if (field2.duration == 0 && field2.active)
			{
				field2.active = false;
			}
			//Vampiric Aura
			if (aura.duration == 0 && aura.active)
			{
				boss.tempAT -= 100*auraCounter;
				auraCounter =0;
				aura.active = false;
			}
			
			//Rampage
			if (rampage.duration == 0 && rampage.active)
			{
				boss.tempAT -= powerRM;
				rampage.active = false;
			}
			
			//Hatred
			if (hatred.duration == 0 && hatred.active)
			{
				hatred.active = false;
			}
			
			//Brass Knuckle
			if (brass.duration == 0 && brass.active)
			{
				stunBrass = false;
				brass.active = false;
			}
			
			//Poisson
			if (poisson.duration == 0 && poisson.active)
			{
				myRegenHP.rate += 120;
				poisson.active = false;
			}
			
			//Silence
			if (silence.duration == 0 && silence.active)
			{
				for (int i = 0; i < 20; i ++)
					if (moves[i] != null)
						moves[i].setEnabled(true);
				if (blastCD.duration != 0)
					moves[4].setEnabled(false);
				else if (mindFlameCD.duration != 0)
					moves[2].setEnabled(false);
				else if (ritualCD.duration != 0)
					moves[6].setEnabled(false);
				else if (counterCD.duration != 0)
					moves[8].setEnabled(false);
				else if (tangleCD.duration != 0)
					moves[10].setEnabled(false);
				else if (cureCD.duration != 0)
					moves[13].setEnabled(false);
				silence.active = false;
			}
			
			//Quas
			if (quas.duration == 0 && quas.active)
			{
				hisRegenHP.rate -= 25;
				if (hisRegenHP.temp + 500 > hisRegenHP.max)
					hisRegenHP.temp = hisRegenHP.max;
				else
					hisRegenHP.temp += 500;
				quas.active = false;
			}
			
			//Wex
			if (wex.duration == 0 && wex.active)
			{
				hisRegenMA.rate -= 25;
				if (hisRegenMA.temp + 500 > hisRegenMA.max)
					hisRegenMA.temp = hisRegenMA.max;
				else
					hisRegenMA.temp += 500;
				wex.active = false;
			}
			
			//Exort
			if (exort.duration == 0 && exort.active)
			{
				shocked.active = true;
				shocked.duration = 3;
				insertBuff(shocked);
				shockAnimation.start();
				exort.active = false;
			}
			
			//Shock
			if (shocked.duration == 0 && shocked.active)
			{
				stunShock = false;
				shocked.active = false;
			}
			
			//Spirit Break
			if (broken.duration == 0 && broken.active)
			{
				myRegenHP.rate += 10;
				me.tempAT += 20;
				broken.active = false;
			}
			
			//Displaying Buff Effects of the Player
			for (int i = 0; i < 20; i++)
				if (list[i] != null && !list[i].active)
					for (int j = i; j < 20; j ++)
						if (j != 19)
							list[j] = list[j+1];
						else
							list[j] = null;
			for (int i = 0; i < 20; i++)
				if (list[i] == berserk)
					g.drawImage(berserkerCry.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == shield)
					g.drawImage(armor.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == bless)
					g.drawImage(HolyGrace.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == frenzy1)
					g.drawImage(overload.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == frenzy2)
					g.drawImage(plasmaField.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == field1)
					g.drawImage(fusion.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == field2)
					g.drawImage(infinitVoltage.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == brass)
					g.drawImage(knuckle.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == poisson)
					g.drawImage(poison.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == shocked)
					g.drawImage(shock.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == hatred)
					g.drawImage(hatredPic.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == silence)
					g.drawImage(silent.getImage(),945,430-i*50, 43,43,this);
				else if (list[i] == broken)
					g.drawImage(broke.getImage(),945,430-i*50, 43,43,this);
			
			//Keep track of the speed of the player
			stunBrass = false;
			stunShock = false;
			int stack = 0;
			for (int i = 0; i  < 20; i++)
				if (list[i] == berserk)
					stack += 7*movesLevel[3];
				else if (list[i] == frenzy1)
					stack += (int)(me.speed*(0.15+0.15*movesLevel[16]));
				else if (list[i] == brass)
					stunBrass = true;
				else if (list[i] == poisson)
					stack -= me.speed/2;
				else if (list[i] == shocked)
					stunShock = true;
			if (stunBrass)
				myCD.speed = 1;
			else if (stunShock)
				myCD.speed = 1;
			else if (me.speed + stack <= 0)
				myCD.speed = 1;
			else
				myCD.speed = me.speed + stack;
			
			//Displaying Buff Effects of the Opponent
			for (int i = 0; i < 20; i++)
				if (listOp[i] != null && !listOp[i].active)
					for (int j = i; j < 20; j ++)
						if (j != 19)
							listOp[j] = listOp[j+1];
						else
							listOp[j] = null;
			for (int i = 0; i < 20; i++)
				if (listOp[i] == dance)
					g.drawImage(graveChill.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == freeze)
					g.drawImage(essenceFreeze.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == nova)
					g.drawImage(frostNova.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == tangle)
					g.drawImage(overGrowth.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == boltage)
					g.drawImage(thunderPunch.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == field1)
					g.drawImage(fusion.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == aura)
					g.drawImage(vampireAura.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == rampage)
					g.drawImage(rampagePic.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == blast)
					g.drawImage(chaosBlast.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == quas)
					g.drawImage(quasOrb.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == wex)
					g.drawImage(wexOrb.getImage(),20,215+i*50, 43,43,this);
				else if (listOp[i] == exort)
					g.drawImage(exortOrb.getImage(),20,215+i*50, 43,43,this);
			//Keep track of the speed of the AI
			stunBoltage = false;
			stunBlast = false;
			int stackOp = 0;
			for (int i = 0; i  < 20; i++)
				if (listOp[i] == dance)
					stackOp -= (int)(boss.speed*(0.05*movesLevel[5]));
				else if (listOp[i] == nova)
					stackOp -= (int)(boss.speed*0.2);
				else if (listOp[i] == tangle)
					stackOp -= (int)(boss.speed*(0.15+0.05*movesLevel[10]));
				else if (listOp[i] == boltage)
					stunBoltage = true;
				else if (listOp[i] == aura)
					stackOp += auraCounter*7;
				else if (listOp[i] == rampage)
					stackOp += incrRM;
				else if (listOp[i] == blast)
					stunBlast = true;
				else if (listOp[i] == exort)
					stackOp += 20;
			if (stunBoltage)
				hisCD.speed = 1;
			else if (stunBlast)
				hisCD.speed = 1;
			else if (boss.speed + stackOp <= 0)
				hisCD.speed = 1;
			else
				hisCD.speed = boss.speed + stackOp;
			
			/*
			g.drawString(""+myCD.speed,200,200);
			g.drawString(""+hisCD.speed,200,220);
			g.drawString(""+stunBrass + brass.active + brass.duration,200,240);
			g.drawString(""+stunBoltage + boltage.active + boltage.duration,200,260);*/
			
			//Recalculates the damage intake of player and opponent based on their defenses
			damageIntakeP = 1-Math.sqrt(boss.tempDE)/20;
			damageIntakeS = 1-Math.sqrt(boss.tempMD)/20;
			myIntakeP = 1-Math.sqrt(me.tempDE)/20;
			myIntakeS = 1-Math.sqrt(me.tempMD)/20;
			
			//Opponent's Health, Mana, and Action Bar
			g.drawImage(hisCommandBox.getImage(),0,0,379,204,this);
			g.drawImage(barFrame.getImage(),20,32,328,28,this);
			g.drawImage(barFrame.getImage(),20,72,328,28,this);
			g.drawImage(barFrame.getImage(),20,112,328,28,this);
			g.drawImage(healthBar.getImage(),22,34,(int)(324*(hisRegenHP.temp*1.0/hisRegenHP.max)),24, this);
			g.drawImage(manaBar.getImage(),22,74,(int)(324*(hisRegenMA.temp*1.0/hisRegenMA.max)),24, this);
			g.drawImage(actionBar.getImage(),22,114,(int)(324*(hisCD.coolDown*1.0/100)),24, this);
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,14));
			g.drawString(""+hisRegenHP.temp+"/"+hisRegenHP.max,17,64);
			g.drawString(""+hisRegenMA.temp+"/"+hisRegenMA.max,17,104);
			g.drawString(""+hisCD.coolDown+"/"+100,17,144);
			
			//Opponent's Stats
			g.setColor(Color.black);
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.drawString("ATK: ",20, 177);
			g.drawString("DEF: ",115,177);
			g.drawString("EVA: ",210,177);
			g.drawString("SRE: ",305,177);
			g.setColor(Color.blue);
			if (boss.tempAT > 300)
			{
				g.setColor(Color.red);
				g.drawString(""+(boss.tempAT),50+((int)(Math.random()*10)-5),177+((int)(Math.random()*10)-5));
			}
			else
			{
				g.setColor(Color.blue);
				g.drawString(""+(boss.tempAT),50,177);
			}
			g.setColor(Color.blue);
			g.drawString(""+(int)(100-damageIntakeP*100)+"%",145,177);
			g.drawString(""+(boss.tempEV)+"%",240,177);
			g.drawString(""+(int)(100-damageIntakeS*100)+"%",335,177);
			
			//Stats Indicator: Me
			g.setColor(Color.black);
			g.drawImage(commandBox.getImage(),340,491,679,204,this);
			g.setFont(new Font("Arial",Font.BOLD,12));
			g.drawString("ATK: ",670,670);
			g.drawString("DEF: ",765,670);
			g.drawString("EVA: ",860,670);
			g.drawString("SRE: ",955,670);
			g.setColor(Color.blue);
			g.drawString(""+(me.tempAT),700,670);
			g.drawString(""+(int)(100-myIntakeP*100)+"%",795,670);
			g.drawString(""+(me.tempEV)+"%",890,670);
			g.drawString(""+(int)(100-myIntakeS*100)+"%",985,670);
			if (silence.active)
				g.drawImage(silenced.getImage(),700,400,54,52,this);
			
			//My Health, Mana, and Action Bars
			g.drawImage(barFrame.getImage(),670,525,328,28,this);
			g.drawImage(barFrame.getImage(),670,565,328,28,this);
			g.drawImage(barFrame.getImage(),670,605,328,28,this);
			g.drawImage(healthBar.getImage(),672,527,(int)(324*(myRegenHP.temp*1.0/myRegenHP.max)),24, this);
			g.drawImage(manaBar.getImage(),672,567,(int)(324*(myRegenMA.temp*1.0/myRegenMA.max)),24, this);
			g.drawImage(actionBar.getImage(),672,607,(int)(324*(myCD.coolDown*1.0/100)),24, this);
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,14));
			g.drawString(""+myRegenHP.temp+"/"+myRegenHP.max,935,557);
			g.drawString(""+myRegenMA.temp+"/"+myRegenMA.max,935,597);
			g.drawString(""+myCD.coolDown+"/"+100,935,637);
			
			
			////////////////////ANIMATIONS///////////////////////////
			//The animations are shown by showing different pictures at different time of the animation
			//Note, some abilities are calculated WITHIN the animation, that is when the user clicks on the button
			//the ability does not immediately take effect, but rather it will take effect in a specific time frame of the animation
			//Such kind of animations has the ability explanation within the animation code
			//For all other ability explanations, refer back to the section of button clicking
			//Regular Attack Animation for Me
			g.setFont(new Font("Arial",Font.BOLD,24));
			g.setColor(Color.black);
			if (meAttack)
			{
				if (attackAnimation.time < 300);
				{
					g.setColor(Color.red);
					g.drawLine(300,251,(int)(300+1/3.0*attackAnimation.time),251+(int)(1/3.0*attackAnimation.time));
					g.drawLine(300,252,(int)(300+1/3.0*attackAnimation.time),252+(int)(1/3.0*attackAnimation.time));
					g.drawLine(300,253,(int)(300+1/3.0*attackAnimation.time),253+(int)(1/3.0*attackAnimation.time));
					g.drawLine(300,254,(int)(300+1/3.0*attackAnimation.time),254+(int)(1/3.0*attackAnimation.time));
				}
				if (myDamage == 0)
				{
					g.setColor(Color.red);
					g.drawString("MISS!",350,200);
					g.setColor(Color.black);
				}
				else
					g.drawString(""+myDamage,350,200);
			}
			if (attackAnimation.time > 300)
			{
				attackAnimation.interrupt();
				attackAnimation = new Animation();
				meAttack = false;
			}
			
			//Regular Attack Animation for AI
			if (bossAttack)
			{
				if (bossAttackAnimation.time < 300);
				{
					g.setColor(Color.red);
					g.drawLine(650,451,(int)(650+1/3.0*bossAttackAnimation.time),451+(int)(1/3.0*bossAttackAnimation.time));
					g.drawLine(650,452,(int)(650+1/3.0*bossAttackAnimation.time),452+(int)(1/3.0*bossAttackAnimation.time));
					g.drawLine(650,453,(int)(650+1/3.0*bossAttackAnimation.time),453+(int)(1/3.0*bossAttackAnimation.time));
					g.drawLine(650,454,(int)(650+1/3.0*bossAttackAnimation.time),454+(int)(1/3.0*bossAttackAnimation.time));
				}
				if (hisDamage == 0)
				{
					g.setColor(Color.red);
					g.drawString("MISS!",700,450);
					g.setColor(Color.black);
				}
				else
					g.drawString(""+hisDamage,700,450);
			}
			if (bossAttackAnimation.time > 300)
			{
				bossAttackAnimation.interrupt();
				bossAttackAnimation = new Animation();
				bossAttack = false;
			}
			
			//Fire Bolt Animation
			if (boltAnimation.time > 0)
			{
				if (boltAnimation.time < 200)
					if (Math.random()*100 >50)
						g.drawImage(fire1.getImage(),600 - (int)(boltAnimation.time*(300.0/200)),400-(int)(boltAnimation.time*(200.0/200)),160,160,this);
					else
						g.drawImage(fire2.getImage(),600 - (int)(boltAnimation.time*(300.0/200)),400-(int)(boltAnimation.time*(200.0/200)),160,160,this);
				//Does straight damage with the cost of some mana
				else if (boltAnimation.time >= 200 && !boltAnimation.damageDealt)
				{
					if (hisRegenHP.temp < (int)((100 + 50*movesLevel[0])*damageIntakeS)*evasion)
						hisRegenHP.temp = 0;
					else
						hisRegenHP.temp -= (int)((100 + 50*movesLevel[0])*damageIntakeS)*evasion;
					boltAnimation.damageDealt = true;
					myDamage = (int)((100 + 50*movesLevel[0])*damageIntakeS)*evasion;
				}
				else if (boltAnimation.time < 220)
					g.drawImage(fire3.getImage(),257,150,160,160,this);
				else if (boltAnimation.time < 290)
					g.drawImage(fire4.getImage(),257,150,160,160,this);
				else if (boltAnimation.time < 360)
					g.drawImage(fire5.getImage(),257,150,160,160,this);
				else if (boltAnimation.time < 430)
					g.drawImage(fire6.getImage(),257,150,160,160,this);
				else if (boltAnimation.time >= 430)
				{
					boltAnimation.interrupt();
					boltAnimation = new Animation();
				}
				if (boltAnimation.time > 220 && boltAnimation.time < 500)
					if (myDamage == 0)
					{
						g.setColor(Color.red);
						g.drawString("MISS!",350,200);
						g.setColor(Color.black);
					}
					else
						g.drawString(""+myDamage,350,200);
			}
			
			//Mind Flame
			if (burnAnimation.time > 0)
			{
				if (burnAnimation.time < 35)
					g.drawImage(burn1.getImage(),325,275,73,37,this);				
				else if (burnAnimation.time < 70)
					g.drawImage(burn2.getImage(),326,269,70,49,this);
				else if (burnAnimation.time < 105)
					g.drawImage(burn3.getImage(),327,266,68,54,this);
				else if (burnAnimation.time < 140)
					g.drawImage(burn4.getImage(),323,265,78,58,this);
				else if (burnAnimation.time < 175)
					g.drawImage(burn5.getImage(),312,256,100,74,this);
				else if (burnAnimation.time < 210)
					g.drawImage(burn6.getImage(),309,250,105,87,this);
				else if (burnAnimation.time < 245)
					g.drawImage(burn7.getImage(),309,249,105,79,this);
				else if (burnAnimation.time < 280)
					g.drawImage(burn8.getImage(),312,261,100,66,this);
				else if (burnAnimation.time >= 280)
				{
					burnAnimation.interrupt();
					burnAnimation = new Animation();
				} 
				if (myDamage == 0)
				{
					g.setColor(Color.red);
					g.drawString("MISS!",350,200);
					g.setColor(Color.black);
				}
				else
					g.drawString(""+myDamage,350,200);
			}
			
			//Berserker Call Animation
			if (berserkerAnimation.time > 0)
			{
				if (berserkerAnimation.time < 35)
					g.drawImage(pump1.getImage(),430,250,473,291,this);				
				else if (berserkerAnimation.time < 70)
					g.drawImage(pump2.getImage(),430,250,473,291,this);
				else if (berserkerAnimation.time < 105)
					g.drawImage(pump3.getImage(),430,250,473,291,this);
				else if (berserkerAnimation.time < 140)
					g.drawImage(pump4.getImage(),430,250,473,291,this);
				else if (berserkerAnimation.time >= 140)
				{
					berserkerAnimation.interrupt();
					berserkerAnimation = new Animation();
				}
				g.drawImage(noh.getImage(),(int)((546-(229/140.0*berserkerAnimation.time))/2)+390,400-(int)(319/140.0*berserkerAnimation.time),(int)(229/140.0*berserkerAnimation.time),(int)(319/140.0*berserkerAnimation.time),this);
			}
			
			//Chaos Blast Animation
			if (blastAnimation.time > 0)
			{
				if (blastAnimation.time < 35)
					g.drawImage(explosion1.getImage(),100,-100,480,480,this);				
				else if (blastAnimation.time < 70)
					g.drawImage(explosion2.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 105)
					g.drawImage(explosion3.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 140)
					g.drawImage(explosion4.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 175)
					g.drawImage(explosion5.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 210)
					g.drawImage(explosion6.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 245)
					g.drawImage(explosion7.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 280)
					g.drawImage(explosion8.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 315)
					g.drawImage(explosion9.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 350)
					g.drawImage(explosion10.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time < 385)
					g.drawImage(explosion11.getImage(),100,-100,480,480,this);
				else if (blastAnimation.time >= 385)
				{
					blastAnimation.interrupt();
					blastAnimation = new Animation();
				}
				if (myDamage == 0)
				{
					g.setColor(Color.red);
					g.drawString("MISS!",350,200);
					g.setColor(Color.black);
				}
				else
					g.drawString(""+myDamage,350,200);
			}
			
			//Shadow Dance
			if (danceAnimation.time > 0)
			{
				if (danceAnimation.time/40%4 == 0)
					g.drawImage(curse1.getImage(),100,100,243,246,this);				
				else if (danceAnimation.time/40%4 == 1)
					g.drawImage(curse2.getImage(),100,100,243,246,this);
				else if (danceAnimation.time/40%4 == 2)
					g.drawImage(curse3.getImage(),100,100,243,246,this);
				else if (danceAnimation.time/40%4 == 3)
					g.drawImage(curse4.getImage(),100,100,243,246,this);
				if (danceAnimation.time >= 1500)
				{
					danceAnimation.interrupt();
					danceAnimation = new Animation();;
				} 
			}
			
			//Cryptic Command
			if (commandAnimation.time > 0)
			{
				if (commandAnimation.time < 35)
					g.drawImage(feedback1.getImage(),350,200,73,37,this);				
				else if (commandAnimation.time < 70)
					g.drawImage(feedback2.getImage(),351,194,70,49,this);
				else if (commandAnimation.time < 105)
					g.drawImage(feedback3.getImage(),352,191,68,54,this);
				else if (commandAnimation.time < 140)
					g.drawImage(feedback4.getImage(),348,190,78,58,this);
				else if (commandAnimation.time < 175)
					g.drawImage(feedback5.getImage(),337,181,100,74,this);
				else if (commandAnimation.time < 210)
					g.drawImage(feedback6.getImage(),334,175,105,87,this);
				else if (commandAnimation.time < 245)
					g.drawImage(feedback7.getImage(),334,174,105,79,this);
				else if (commandAnimation.time < 280)
					g.drawImage(feedback8.getImage(),337,186,100,66,this);
				else if (commandAnimation.time >= 280)
				{
					commandAnimation.interrupt();
					commandAnimation = new Animation();
				} 
			}
			
			//Essence Freeze Animation
			if (freezeAnimation.time > 0)
			{
				if (freezeAnimation.time < 35)
					g.drawImage(wind1.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 70)
					g.drawImage(wind2.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 105)
					g.drawImage(wind3.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 140)
					g.drawImage(wind4.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 175)
					g.drawImage(wind5.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 210)
					g.drawImage(wind6.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 245)
					g.drawImage(wind7.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 280)
					g.drawImage(wind8.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 315)
					g.drawImage(wind3.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 350)
					g.drawImage(wind4.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 385)
					g.drawImage(wind5.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 420)
					g.drawImage(wind6.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 455)
					g.drawImage(wind7.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 490)
					g.drawImage(wind8.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 525)
					g.drawImage(wind9.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time < 560)
					g.drawImage(wind10.getImage(),150 + (int)(75.0/freezeAnimation.time),0,480,480,this);
				else if (freezeAnimation.time >= 560)
				{
					freezeAnimation.interrupt();
					freezeAnimation = new Animation();
				}
			}
			
			//Dark Ritual Animation
			if (ritualAnimation.time > 0)
			{
				if (ritualAnimation.time < 35)
					g.drawImage(ritual1.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 70)
					g.drawImage(ritual2.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 105)
					g.drawImage(ritual3.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 140)
					g.drawImage(ritual4.getImage(),650,400,81,82,this);
				//Gain back some mana at the cost of a little bit of mana and health
				else if (!ritualAnimation.damageDealt)
				{
					if(myRegenMA.temp + movesLevel[6]*50+50 > myRegenMA.max)
					{
						myRegenMA.temp = myRegenMA.max;
					}
					else
						myRegenMA.temp += movesLevel[6]*50+50;
					myRegenHP.temp -= 100;
					ritualAnimation.damageDealt= true;
					myDamage = movesLevel[6]*50+50;
				}
				else if (ritualAnimation.time < 175)
					g.drawImage(ritual5.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 210)
					g.drawImage(ritual6.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 245)
					g.drawImage(ritual7.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time < 280)
					g.drawImage(ritual8.getImage(),650,400,81,82,this);
				else if (ritualAnimation.time > 580)
				{
					ritualAnimation.interrupt();
					ritualAnimation = new Animation();
				}
				if (ritualAnimation.time > 140)
				{
					g.setColor(Color.blue);
					g.drawString(""+myDamage,600,350);
					g.setColor(Color.black);
				}
			}
			
			//Frost Nova Animation
			if (frostAnimation.time > 0)
			{
				if (frostAnimation.time < 300)
				{
					g.drawImage(frost1.getImage(),-15+(int)(360.0/300*(frostAnimation.time)),-170+(int)(480.0/300*(frostAnimation.time)),69,69,this);
					g.drawImage(frost2.getImage(),100+(int)(210.0/300*frostAnimation.time),-350+(int)(600.0/300*frostAnimation.time),69,69,this);
					g.drawImage(frost3.getImage(),325,-250+(int)(200.0/100*frostAnimation.time),69,69,this);
					g.drawImage(frost4.getImage(),520-(int)(210.0/300*frostAnimation.time),-350+(int)(600.0/300*frostAnimation.time),69,69,this);
					g.drawImage(frost5.getImage(),705-(int)(360.0/300*frostAnimation.time),-170+(int)(480.0/300*frostAnimation.time),69,69,this);
				}
				//Damage straight damage with the effect of slowing down the opponent's action bar
				else if (!frostAnimation.damageDealt)
				{
					if (hisRegenHP.temp < (int)((100+100*movesLevel[9])*damageIntakeS)*evasion)
						hisRegenHP.temp = 0;
					else
						hisRegenHP.temp -= (int)((100+100*movesLevel[9])*damageIntakeS)*evasion;
					nova.active = true;
					nova.duration = 4;
					myDamage = (int)((100+100*movesLevel[9])*damageIntakeS)*evasion;
					insertBuffOp(nova);
					frostAnimation.damageDealt = true;
				}
				else if (frostAnimation.time <= 370)
					g.drawImage(freeze1.getImage(),325,275,81,82,this);
				else if (frostAnimation.time <= 440)
					g.drawImage(freeze2.getImage(),325,275,81,82,this);
				else if (frostAnimation.time <= 510)
					g.drawImage(freeze3.getImage(),325,275,81,82,this);
				else if (frostAnimation.time <= 580)
					g.drawImage(freeze4.getImage(),325,275,81,82,this);
				else if (frostAnimation.time > 580)
				{
					frostAnimation.interrupt();
					frostAnimation = new Animation();
				}
				if (frostAnimation.time >= 370)
					if (myDamage == 0)
					{
						g.setColor(Color.red);
						g.drawString("MISS!",350,200);
						g.setColor(Color.black);
					}
					else
						g.drawString(""+myDamage,350,200);
			}

			//Tangle Animation
			if (tangleAnimation.time > 0)
			{
				if (tangleAnimation.time < 70)
					g.drawImage(vine1.getImage(),300,325,161,50,this);				
				else if (tangleAnimation.time < 140)
					g.drawImage(vine2.getImage(),306,316,149,59,this);
				else if (tangleAnimation.time < 210)
					g.drawImage(vine3.getImage(),282,286,198,89,this);
				else if (tangleAnimation.time < 280)
					g.drawImage(vine4.getImage(),293,242,176,133,this);
				else if (tangleAnimation.time < 350)
					g.drawImage(vine5.getImage(),288,217,186,158,this);
				else if (tangleAnimation.time < 420)
					g.drawImage(vine6.getImage(),282,156,198,219,this);
				else if (tangleAnimation.time < 490)
					g.drawImage(vine7.getImage(),280,142,201,233,this);
				else if (tangleAnimation.time < 560)
					g.drawImage(vine8.getImage(),278,145,205,230,this);
				else if (tangleAnimation.time >= 560)
				{
					tangleAnimation.interrupt();
					tangleAnimation = new Animation();
				}
			}
			
			//Living Armor Animation
			if (armourAnimation.time > 0)
			{
				if (armourAnimation.time < 2000)
					g.drawImage(shieldPic.getImage(),650,320,100,130,this);
				else if (armourAnimation.time >= 2000)
				{
					armourAnimation.interrupt();
					armourAnimation = new Animation();
				}
			}

			//Implode Animation
			if (implodeAnimation.time > 0)
			{
				if (implodeAnimation.time < 490)
					if (Math.random()*100 >50)
						g.drawImage(wave2.getImage(),500 - (int)((implodeAnimation.time-70.0)/7*5),250-(int)((implodeAnimation.time-70.0)/7*5),480,480,this);
					else
						g.drawImage(wave4.getImage(),500 - (int)((implodeAnimation.time-70.0)/7*5),250-(int)((implodeAnimation.time-70.0)/7*5),480,480,this);
				else if (implodeAnimation.time < 560)
					g.drawImage(wave5.getImage(),500-300,250-300,480,480,this);
				else if (implodeAnimation.time < 630)
					g.drawImage(wave6.getImage(),500-300,250-300,480,480,this);
				else if (implodeAnimation.time < 700)
					g.drawImage(wave7.getImage(),500-300,250-300,480,480,this);
				else if (implodeAnimation.time < 770)
					g.drawImage(wave8.getImage(),500-300,250-300,480,480,this);
				//Does straight damage the lower the player's health is, but the mana cost gets higher when damage gets higher
				else if (!implodeAnimation.damageDealt)
				{
					if (hisRegenHP.temp < (int)((myRegenHP.max-myRegenHP.temp)*(0.03*movesLevel[12]+0.2)*damageIntakeP)*evasion)
						hisRegenHP.temp = 0;
					else
						hisRegenHP.temp -= (int)((myRegenHP.max-myRegenHP.temp)*(0.03*movesLevel[12]+0.2)*damageIntakeP*evasion);
					myDamage = (int)((myRegenHP.max-myRegenHP.temp)*(0.03*movesLevel[12]+0.2)*damageIntakeP)*evasion;
					implodeAnimation.damageDealt = true;
				}
				else if (implodeAnimation.time >= 770 && implodeAnimation.time < 1100)
					if (myDamage == 0)
					{
						g.setColor(Color.red);
						g.drawString("MISS!",350,200);
						g.setColor(Color.black);
					}
					else
						g.drawString(""+myDamage,350,200);
				else if (implodeAnimation.time >= 1100)
				{
					implodeAnimation.interrupt();
					implodeAnimation = new Animation();
				}
			}
			
			//Cure Animation
			if (cureAnimation.time > 0)
			{	
				if (cureAnimation.time/40%4 == 0)
					g.drawImage(regen1.getImage(),630,400,81,82,this);				
				else if (cureAnimation.time/40%4 == 1)
					g.drawImage(regen2.getImage(),630,400,81,82,this);
				else if (cureAnimation.time/40%4 == 2)
					g.drawImage(regen3.getImage(),630,400,81,82,this);
				else if (cureAnimation.time/40%4 == 3)
					g.drawImage(regen4.getImage(),630,400,81,82,this);
				if (cureAnimation.time >= 1500)
				{
					cureAnimation.interrupt();
					cureAnimation = new Animation();
				}
			}
			
			//Angelic Blessing Animation
			if (blessingAnimation.time > 0)
			{	
				if (blessingAnimation.time < 70)
					g.drawImage(blessing1.getImage(),624,-1012,100,1463,this);				
				else if (blessingAnimation.time < 140)
					g.drawImage(blessing2.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 210)
					g.drawImage(blessing3.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 280)
					g.drawImage(blessing4.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 350)
					g.drawImage(blessing5.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 420)
					g.drawImage(blessing6.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 490)
					g.drawImage(blessing7.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 560)
					g.drawImage(blessing8.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 630)
					g.drawImage(blessing9.getImage(),624,-1012,100,1463,this);	
				else if (blessingAnimation.time < 700)
					g.drawImage(blessing10.getImage(),624,-1012,100,1463,this);
				if (blessingAnimation.time >= 1500)
				{
					blessingAnimation.interrupt();
					blessingAnimation = new Animation();
				}
				if (blessingAnimation.time >= 700)
					g.drawImage(blessing10.getImage(),624,-1012,100,1463,this); 
			}
			
			//Nervous Frenzy Animation
			if (frenzyAnimation.time > 0)
			{
				if (frenzyAnimation.time < 35)
					g.drawImage(nervous1.getImage(),430,250,473,291,this);				
				else if (frenzyAnimation.time < 70)
					g.drawImage(nervous2.getImage(),430,250,473,291,this);
				else if (frenzyAnimation.time < 105)
					g.drawImage(nervous3.getImage(),430,250,473,291,this);
				else if (frenzyAnimation.time < 140)
					g.drawImage(nervous4.getImage(),430,250,473,291,this);
				else if (frenzyAnimation.time >= 500)
				{
					frenzyAnimation.interrupt();
					frenzyAnimation = new Animation();
				}
				if (frenzyAnimation.time > 100)
				{
					if (frenzyAnimation.time < 200)
						g.drawImage(spark1.getImage(),583,350,163,141,this);
					else if (frenzyAnimation.time < 300)
						g.drawImage(spark2.getImage(),583,350,172,220,this);
					else if (frenzyAnimation.time < 400)
						g.drawImage(spark3.getImage(),583,350,161,151,this);
					else if (frenzyAnimation.time < 500)
						g.drawImage(spark4.getImage(),583,350,120,168,this);
				}
			}
			
			//Soul Steal Animation
			if (stealAnimation.time > 0)
			{	
				if (stealAnimation.time < 35)
					 g.drawImage(soulSteal1.getImage(),550,330,164,154,this);
				else if (stealAnimation.time < 70)
					 g.drawImage(soulSteal2.getImage(),550,330,164,154,this);
				else if (stealAnimation.time < 105)
					 g.drawImage(soulSteal3.getImage(),550,330,164,154,this);
				else if (stealAnimation.time >= 105)
				{
					stealAnimation.interrupt();
					stealAnimation = new Animation();
				}
				g.setColor(Color.magenta);
				if (hisDamage == 0)
				{
					g.setColor(Color.red);
					g.drawString("MISS!",550,330);
					g.setColor(Color.black);
				}
				else
					g.drawString(""+hisDamage,550,330);
				g.setColor(Color.black);
			}
			
			//Magic Cancel Animation
			if (cancelAnimation.time > 0)
			{	
				if (cancelAnimation.time/40%4 == 0)
					g.drawImage(magicCancel1.getImage(),310,270,81,82,this);				
				else if (cancelAnimation.time/40%4 == 1)
					g.drawImage(magicCancel2.getImage(),310,270,81,82,this);
				else if (cancelAnimation.time/40%4 == 2)
					g.drawImage(magicCancel3.getImage(),310,270,81,82,this);
				else if (cancelAnimation.time/40%4 == 3)
					g.drawImage(magicCancel4.getImage(),310,270,81,82,this);
				if (cancelAnimation.time >= 1500)
				{
					cancelAnimation.interrupt();
					cancelAnimation = new Animation();
				}
			}
			
			//Vampiric Aura Animation
			if (auraAnimation.time > 0)
			{
				if (auraAnimation.time < 35)
					g.drawImage(aura1.getImage(),110,110,473,291,this);				
				else if (auraAnimation.time < 70)
					g.drawImage(aura2.getImage(),110,110,473,291,this);
				else if (auraAnimation.time < 105)
					g.drawImage(aura3.getImage(),110,110,473,291,this);
				else if (auraAnimation.time < 140)
					g.drawImage(aura4.getImage(),110,110,473,291,this);
				else if (auraAnimation.time >= 140)
				{
					auraAnimation.interrupt();
					auraAnimation = new Animation();
				}
				g.drawImage(skull.getImage(),(int)((546-(229/140.0*auraAnimation.time))/2)+90,250-(int)(319/140.0*auraAnimation.time),(int)(223/140.0*auraAnimation.time),(int)(352/140.0*auraAnimation.time),this);
			}
			
			//Vampiric Legion
			if (legionAttack.time > 0)
			{
				for (int i = 0; i <= 500; i+= 1)
					for (int j = 0; j <= 7; j++)
						if ((int)(Math.random()*3) == 0)
							g.drawImage(bat1.getImage(),j*30+(int)(Math.random()*100+i*21), i*12+(int)(Math.random()*100),81,83,this);
						else if ((int)(Math.random()*3) == 1)
							g.drawImage(bat2.getImage(),j*30+(int)(Math.random()*100+i*21), i*12+(int)(Math.random()*100),81,83,this);
						else if ((int)(Math.random()*3) == 2)
							g.drawImage(bat3.getImage(),j*30+(int)(Math.random()*100+i*21), i*12+(int)(Math.random()*100),81,83,this); 
				if (legionAttack.time >= 3000)
				{
					legionAttack.interrupt();
					legionAttack = new Animation();
				}
				g.drawString(""+hisDamage,550,330);
			}
			
			//Rampage, Hatred, and Uber rush are abilities of an unused boss
			//Rampage Animation
			if (rampageAnimation.time > 0)
			{
				if (rampageAnimation.time < 35)
					g.drawImage(pump1.getImage(),110,110,473,291,this);				
				else if (rampageAnimation.time < 70)
					g.drawImage(pump2.getImage(),110,110,473,291,this);
				else if (rampageAnimation.time < 105)
					g.drawImage(pump3.getImage(),110,110,473,291,this);
				else if (rampageAnimation.time < 140)
					g.drawImage(pump4.getImage(),110,110,473,291,this);
				else if (rampageAnimation.time >= 140)
				{
					rampageAnimation.interrupt();
					rampageAnimation = new Animation();
				}
				g.drawImage(noh.getImage(),(int)((546-(229/140.0*rampageAnimation.time))/2)+90,250-(int)(319/140.0*rampageAnimation.time),(int)(229/140.0*rampageAnimation.time),(int)(319/140.0*rampageAnimation.time),this);
			}
			
			//Hatred Animation
			if (hatredAnimation.time > 0)
			{
				if (hatredAnimation.time >= 140)
				{
					hatredAnimation.interrupt();
					hatredAnimation = new Animation();
				}
				g.drawImage(noh.getImage(),(int)((546-(229/140.0*hatredAnimation.time))/2)+390,400-(int)(319/140.0*hatredAnimation.time),(int)(229/140.0*hatredAnimation.time),(int)(319/140.0*hatredAnimation.time),this);
			}
			
			//Uber Rush Animation
			if (rushAnimation.time > 0)
			{	
				if (rushAnimation.time < 280)
					for (int i = 0; i <= 500; i+= 1)
						for (int j = 0; j <= 5; j++)
							if (rushAnimation.time < 70)
								g.drawImage(sand1.getImage(),j*60+i*31, i*21,81,83,this);
							else if (rushAnimation.time < 140)
								g.drawImage(sand2.getImage(),j*60+i*31, i*21,81,83,this);
							else if (rushAnimation.time < 210)
								g.drawImage(sand3.getImage(),j*60+i*31, i*21,81,83,this);
							else if (rushAnimation.time < 280)
								g.drawImage(sand4.getImage(),j*60+i*31, i*21,81,83,this); 
				else if (rushAnimation.time >= 400)
				{
					rushAnimation.interrupt();
					rushAnimation = new Animation();
				}
			}
			
			//Poison Attack Animation
			if (poisonAnimation.time > 0)
			{	
				if (poisonAnimation.time < 500)
					g.drawImage(poisonAttack.getImage(),310+(int)(300*poisonAnimation.time/500.0),250+(int)(150*poisonAnimation.time/500.0),90,82,this);
				//Slows down the player and causes the player to for a period of time
				else if (!poisonAnimation.damageDealt)
				{
					if(poisson.duration == 0)
					{
						myRegenHP.rate -= 120;
						poisson.duration = 5;
						poisson.active = true;
					}
					else
						poisson.duration = 5;
					insertBuff(poisson);
					poisonAnimation.damageDealt = true;
				}
				else if (poisonAnimation.time >= 500)
				{
					poisonAnimation.interrupt();
					poisonAnimation = new Animation();
				}
			}
			
			//Time Lapse Animation
			if (lapseAnimation.time > 0)
			{	
				if (lapseAnimation.time < 2000)
				{
					g.drawImage(clock.getImage(),500,150,93,119,this);
					g.drawLine(546,223,546+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),223+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(547,223,547+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),223+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(545,223,545+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),223+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(546,224,546+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),224+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(546,222,546+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),222+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(545,222,545+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),222+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(547,223,547+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),223+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(545,223,545+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),223+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
					g.drawLine(547,222,547+(int)(32*Math.cos(-Math.pow((lapseAnimation.time/1000.0),7))),222+(int)(32*Math.sin(-Math.pow((lapseAnimation.time/1000.0),7))));
				}
				//The enemy's HP and MP point goes back to what it was 10 seconds ago. All the negative duration spells are removed
				else if (lapseAnimation.time >= 2000)
				{
					hisRegenHP.temp = lifeKeeper[9999];
					hisRegenMA.temp = manaKeeper[9999];
					dance.duration = 0;
					freeze.duration = 0;
					nova.duration = 0;
					tangle.duration = 0;
					field1.duration = 0;
					lapseAnimation.interrupt();
					lapseAnimation = new Animation();
				}
			}
			
			//Regeneration Animation
			if (regenAnimation.time > 0)
			{	
				if (regenAnimation.time/40%4 == 0)
					g.drawImage(regen1.getImage(),310,270,81,82,this);				
				else if (regenAnimation.time/40%4 == 1)
					g.drawImage(regen2.getImage(),310,270,81,82,this);
				else if (regenAnimation.time/40%4 == 2)
					g.drawImage(regen3.getImage(),310,270,81,82,this);
				else if (regenAnimation.time/40%4 == 3)
					g.drawImage(regen4.getImage(),310,270,81,82,this);
				if (regenAnimation.time >= 1500)
				{
					regenAnimation.interrupt();
					regenAnimation = new Animation();
				}
			}
			
			//Shock
			if (shockAnimation.time > 0)
			{
				if (shockAnimation.time < 35)
					g.drawImage(shocked1.getImage(),610,400,81,82,this);				
				else if (shockAnimation.time < 70)
					g.drawImage(shocked2.getImage(),610,400,81,82,this);
				else if (shockAnimation.time < 105)
					g.drawImage(shocked3.getImage(),610,400,81,82,this);
				else if (shockAnimation.time < 140)
					g.drawImage(shocked4.getImage(),610,400,81,82,this);
				else if (shockAnimation.time >= 140)
				{
					shockAnimation.interrupt();
					shockAnimation = new Animation();
				} 
			}
			
			//Soul Rip Animation
			if (ripAnimation.time > 0)
			{	
				if (ripAnimation.time < 150)
					 g.drawImage(scythe1.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 185)
					 g.drawImage(scythe2.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 220)
					 g.drawImage(scythe3.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 255)
					 g.drawImage(scythe4.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 290)
					 g.drawImage(scythe5.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 325)
					 g.drawImage(scythe6.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 360)
					 g.drawImage(scythe7.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 395)
					 g.drawImage(scythe8.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 430)
					 g.drawImage(scythe9.getImage(),200,130,600,600,this);
				else if (ripAnimation.time < 465)
					 g.drawImage(scythe10.getImage(),200,130,600,600,this);
				//Does damage the more life the player has
				else if (ripAnimation.time >= 465 && !ripAnimation.damageDealt)
				{
					myRegenHP.temp -= (int)(myRegenHP.temp*0.1*myIntakeS*hisEvasion);
					hisDamage = (int)(myRegenHP.temp*0.1*myIntakeS*hisEvasion);
					ripAnimation.damageDealt = true;
				}
				else if (ripAnimation.time < 900)
				{
					g.drawImage(scythe10.getImage(),200,130,600,600,this);
					g.setColor(Color.black);
					if (hisDamage == 0)
					{
						g.setColor(Color.red);
						g.drawString("MISS!",550,330);
						g.setColor(Color.black);
					}
					else
						g.drawString(""+hisDamage,550,330);
				}
				else if (ripAnimation.time >= 900)
				{
					ripAnimation.interrupt();
					ripAnimation = new Animation();
				}
			}
			
			//Spirit Breaker Animation
			if (breakerAnimation.time > 0)
			{	
				hisEvasion = evade(me.tempEV);
				if  (breakerAnimation.time < 500)
					g.drawImage(breaker1.getImage(),310+(int)(300*breakerAnimation.time/500.0),250+(int)(150*breakerAnimation.time/500.0),109,99,this);
				//Does straight damage and reduces the player's attack power and health regeneration rate for a short amount of time
				else if (breakerAnimation.time >= 500 && !breakerAnimation.damageDealt)
				{
					if (myRegenHP.temp < (int)(150*myIntakeS)*hisEvasion)
						myRegenHP.temp =0;
					else
						myRegenHP.temp -= (int)(150*myIntakeS)*hisEvasion;
					if(broken.duration == 0)
					{
						myRegenHP.rate -= 10;
						me.tempAT -= 20;
						broken.duration = 6;
						broken.active = true;
					}
					else
						broken.duration = 6;
					insertBuff(broken);
					hisDamage = (int)(150*myIntakeS)*hisEvasion;
					breakerAnimation.damageDealt = true;
				}
				else if (breakerAnimation.time < 535)
					g.drawImage(breaker2.getImage(),610,400,109,99,this);
				else if (breakerAnimation.time < 570)
					g.drawImage(breaker3.getImage(),610,400,109,99,this);
				else if (breakerAnimation.time < 605)
					g.drawImage(breaker4.getImage(),610,400,109,99,this);
				else if (breakerAnimation.time < 640)
					g.drawImage(breaker5.getImage(),610,400,109,99,this);
				else if (breakerAnimation.time >= 640)
				{
					breakerAnimation.interrupt();
					breakerAnimation = new Animation();
				}
				if (breakerAnimation.time < 535)
					if (hisDamage == 0)
					{
						g.setColor(Color.red);
						g.drawString("MISS!",550,330);
						g.setColor(Color.black);
					}
					else
						g.drawString(""+hisDamage,550,330); 
			}
			
			//Challenger Animation: A fancy animation to show the start of the fight
			if (challenged)
			{	
				if (challenger.time <= 300)
					g.drawImage(matchStart.getImage(),513-(int)(513/300.0*challenger.time),363,(int)(1025/300.0*challenger.time),3,this);
				else if (challenger.time > 300 && challenger.time < 600)
					g.drawImage(matchStart.getImage(),0,363-(int)(81/300.0*(challenger.time-300)),1025,(int)(162/300.0*(challenger.time-300)),this);
				else if (challenger.time > 600 && challenger.time < 1400)
					g.drawImage(matchStart.getImage(),0,282,1025,162,this);
				else if (challenger.time >= 1400 && challenger.time < 1700)
					g.drawImage(matchStart.getImage(),0,282+(int)(81/300.0*(challenger.time-1400)),1025,165-(int)(162/300.0*(challenger.time-1400)),this);
				else if (challenger.time >= 1700 && challenger.time < 2100)
					g.drawImage(matchStart.getImage(),(int)(513/300.0*(challenger.time-1700)),363,1025-(int)(1025/300.0*(challenger.time-1700)),3,this);
				else if (challenger.time >= 2000)
				{
					challenger.interrupt();
					challenger = new Animation();
					challenged = false;
				}
			}
      }
   }
	
	////////////////////////////THREAD CLASSES//////////////////////////////////////////////////
	//Constantly repaints to allow sprite animations to work
	class Animation extends Thread
   {
		int time;
		boolean damageDealt;
		public Animation()
		{
			time = 0;
			damageDealt = false;
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
	
	//The background calculations of AI actions, and the execution of the actions
	class Boss extends Thread
   {
      int DPSPlayer;
		int DPSBoss;
		int action;
		boolean buffed;
		public Boss()
		{
			DPSPlayer=0;
			DPSBoss=0;
			action = 0;
			buffed = false;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					if (challenged)
					{
						sleep (2000);
						challenged = false;
					}
					DPSPlayer = (int)(me.tempAT*damageIntakeP*myCD.speed/100.0);
					DPSBoss = (int)(boss.tempAT*myIntakeP*hisCD.speed/100.0);
					hisEvasion = evade(me.tempEV);
					
					//Effect of Hatred-Forces the player to use regular attack for a period of time
					if (hatred.active && myCD.coolDown == 100)
					{
						if (hisRegenHP.temp < (int)(me.tempAT*damageIntakeP)*evasion)
							hisRegenHP.temp =0;
						else
							hisRegenHP.temp -= (int)(me.tempAT*damageIntakeP)*evasion;
						myDamage = (int)((me.tempAT*(movesLevel[17]*0.5+1.0))*damageIntakeP);
						myCD.coolDown = 0;
						meAttack = true;
						attackAnimation.start();
					}
					
					//Health and Mana keeper-Used for keeping track of the enemy's life in the past 10 seconds
					for (int i = 9999; i > 0; i--)
						lifeKeeper[i] = lifeKeeper[i-1];
					lifeKeeper[0] = hisRegenHP.temp;
					for (int i = 9999; i > 0; i--)
						manaKeeper[i] = manaKeeper[i-1];
					manaKeeper[0] = hisRegenMA.temp;
					
					//Draco
					//Calculation of what to do
					if (bossNumber == 1)
					{
						//Desperate Attempt to Finish the Game: Nearing the end
						if (myRegenHP.temp <= (int)((300+boss.tempAT)*myIntakeP) && hisRegenMA.temp >= 300 && legion.duration == 0)
							action = 5;
						else if (myRegenHP.temp <= (int)(90*myIntakeS) && hisRegenMA.temp >= 100 && hisCD.coolDown ==100)
							action = 2;
						else if (myRegenHP.temp <= (int)(boss.tempAT*myIntakeP) && hisCD.coolDown == 100 && me.tempEV < 30)
							action = 1;
						else if (myRegenHP.temp <= (int)(boss.tempAT*myIntakeP) && hisRegenMA.temp < 100)
							action = 1;
						else if (myRegenHP.temp <= (int)(90*myIntakeS) && hisRegenMA.temp <= 100 && hisRegenMA.temp >= 80)
							action = 2;
						else if (myRegenHP.temp <= (int)((300+boss.tempAT)*myIntakeP) && hisRegenMA.temp < 300 && hisRegenMA.temp > 280 && legion.duration <= 2)
							action = 5;
						else if (me.tempEV > 60)
							action = 3;
						else if (myRegenMA.temp >= 300 && aura.duration > 0 && aura.duration < 3 && legion.duration == 0 && hisCD.coolDown < 100)
							action = 5;
						else if (DPSPlayer >= 150 && hisRegenMA.temp >= 900 && aura.duration == 0)
							action = 3;
						
						//Magic Cancel-attemp to remove negative effects
						else if (dance.duration > 10 && hisRegenMA.temp >=120)
							action = 4;
						else if (field1.duration > 10 && hisRegenMA.temp >=120)
							action = 4;
						else if (freeze.duration > 20 && hisRegenMA.temp >=120)
							action = 4;
						
						//Before the end game
						else if (myRegenHP.temp <= 500 && hisRegenMA.temp >=80 && hisRegenMA.temp >= 100)
							action = 2;
						else if (aura.duration != 0 && hisRegenMA.temp >= 100)
							action = 2;
						else if (aura.duration > 0 && aura.duration < 3 && hisRegenMA.temp >= 900)
							action = 3;
						else if (hisRegenHP.temp < 3500 && aura.duration == 0 && hisRegenMA.temp >= 900)
							action = 3;
						else if (DPSPlayer > 100 && hisRegenMA.temp >= 900 && aura.duration == 0)
							action = 3;
						
						//Survival
						else if (hisRegenMA.temp >= 100 && hisRegenHP.temp <= 2000)
							action = 2;
						else
							action = 1;
					//Execution of AI action
						//Normal Attack
						if (action == 1 && hisCD.coolDown == 100)
						{
							if (myRegenHP.temp < (int)((boss.tempAT)*myIntakeP)*hisEvasion)
							{
								myRegenHP.temp =0;
								aura.duration =0;
							}
							else
							{
								myRegenHP.temp -= (int)((boss.tempAT)*myIntakeP)*hisEvasion;
								aura.duration =0;
							}
							hisCD.coolDown =0;
							hisDamage = (int)((boss.tempAT)*myIntakeP)*hisEvasion;
							bossAttack = true;
							bossAttackAnimation = new Animation();
							bossAttackAnimation.start();
						}
						//Life Drain-Steals some life from opponent
						else if (action == 2 && hisCD.coolDown == 100 && hisRegenMA.temp >= 100)
						{
							if (myRegenHP.temp < (int)(90*myIntakeS))
							{
								if (hisRegenHP.temp + 110 > hisRegenHP.max)
								{
									if (aura.duration != 0)
									{
										boss.tempAT += 100;
										auraCounter += 1;
									}
									hisRegenHP.temp = hisRegenHP.max;
								}
								else
								{
									if (aura.duration != 0)
									{
										boss.tempAT += 100;
										auraCounter += 1;
									}
									hisRegenHP.temp += 90;
								}
								myRegenHP.temp =0;
							}
							else
							{
								if (hisRegenHP.temp + 90 > hisRegenHP.max)
								{
									if (aura.duration != 0)
									{
										boss.tempAT += 100;
										auraCounter += 1;
									}
									hisRegenHP.temp = hisRegenHP.max;
								}
								else
								{
									if (aura.duration != 0)
									{
										boss.tempAT += 100;
										auraCounter += 1;
									}
									hisRegenHP.temp += 90;
								}
								myRegenHP.temp -= (int)(90*myIntakeS);
							}
							hisRegenMA.temp -= 100;
							hisCD.coolDown =0;
							hisDamage = (int)(90*myIntakeS);
							stealAnimation = new Animation();
							stealAnimation.start();
						}
						//Vampiric Aura-when used in conjunction with Life Steal, it allows the AI's attack and speed to increase
						else if (action == 3 && hisCD.coolDown == 100 && hisRegenMA.temp >= 150)
						{
							if(aura.duration == 0)
							{
								aura.duration = 20;
								aura.active = true;
							}
							else
								aura.duration = 20;
							hisRegenMA.temp -= 150;
							insertBuffOp(aura);
							hisCD.coolDown =0;
							auraAnimation = new Animation();
							auraAnimation.start();
						}
								
						//Magic Cancel-Removes certain negative effects
						else if (action == 4 && hisCD.coolDown == 100 && hisRegenMA.temp >= 150)
						{
							dance.duration = 0;
							field1.duration = 0;
							freeze.duration = 0;
							hisRegenMA.temp -= 150;
							hisCD.coolDown = 0;
							cancelAnimation = new Animation();
							cancelAnimation.start();
						}
								
						//Vampiric Legion-Does straight damage, undodgable, and gain some life based on how much damage was done
						//This ability can only be used every 20 seconds and its usage is independent of the action bar
						else if ((action == 5 || action == 6) && hisRegenMA.temp >= 300 && legion.duration == 0)
						{
							if (hisRegenHP.temp + (int)(0.25*((300 + boss.tempAT)*myIntakeP)) > hisRegenHP.max)
								hisRegenHP.temp = hisRegenHP.max;
							else
								hisRegenHP.temp += (int)(0.25*((300 + boss.tempAT)*myIntakeP)); 
							if (myRegenHP.temp < (int)((300 + boss.tempAT)*myIntakeP))
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((300 + boss.tempAT)*myIntakeP);
							aura.duration = 0;
							hisRegenMA.temp -= 300;
							legion.duration = 20;
							hisCD.coolDown =0;
							hisDamage = (int)((300 + boss.tempAT)*myIntakeP);
							legionAttack = new Animation();
							legionAttack.start();
						}
					}
					
					//Zeroth-Not going to be used for this game
					if (bossNumber == 2)
					{	
						if (myRegenHP.temp <= (int)((boss.tempAT)*myIntakeS) && hisCD.coolDown == 100)
							action = 1;
						else if (myRegenHP.temp <= (int)((Math.sqrt(myRegenHP.max-myRegenHP.temp)*16)*myIntakeS) && hisRegenMA.temp >= 300 && rushCD.duration == 0)
							action = 3;
						else if (hatredCD.duration == 0 && hisRegenMA.temp >= 150 && rampage.active)
							action = 5; 
						else if (hisRegenHP.temp > 3000)
							action = 4;
						else if (hisRegenHP.temp <= 3000 && hisRegenMA.temp >= 500 && rampageCD.duration == 0)
							action = 2;
						else if ((int)((Math.sqrt(myRegenHP.max-myRegenHP.temp)*16)*myIntakeS)-(int)((boss.tempAT)*myIntakeS) > 700 && hisRegenMA.temp >= 300 && rushCD.duration == 0)
							action = 3;
						else
							action = 1;
					//Execution of AI action
						//Normal Attack
						if (action == 1 && hisCD.coolDown == 100)
						{
							if (myRegenHP.temp < (int)((boss.tempAT)*myIntakeS)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((boss.tempAT)*myIntakeS)*hisEvasion;
							hisCD.coolDown =0;
							hisDamage = (int)((boss.tempAT)*myIntakeP)*hisEvasion;
							bossAttack = true;
							bossAttackAnimation.start();
						}
						
						//Rampage-Increases attack and speed based on how much health the AI lost
						else if (action == 2 && hisCD.coolDown == 100 && hisRegenMA.temp >= 200 && rampageCD.duration == 0)
						{
							powerRM = (int)((hisRegenHP.max-hisRegenHP.temp)/30);
							incrRM = (int)((hisRegenHP.max-hisRegenHP.temp)/90);
							if(rampage.duration == 0)
							{
								boss.tempAT += powerRM;
								rampage.duration = 10;
								rampage.active = true;
							}
							else
								rampage.duration = 10;
							rampageCD.duration = 15;
							insertBuffOp(rampage);
							hisRegenMA.temp -= 500;
							hisCD.coolDown =0;
							rampageAnimation = new Animation();
							rampageAnimation.start();
						}
						
						//Uber Rush-Does damage based on how much life the player lost
						else if (action == 3 && hisCD.coolDown == 100 && hisRegenMA.temp >= 300 && rushCD.duration == 0)
						{
							if (myRegenHP.temp < (int)((Math.sqrt(myRegenHP.max-myRegenHP.temp)*16)*myIntakeS)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((Math.sqrt(myRegenHP.max-myRegenHP.temp)*16)*myIntakeS)*hisEvasion;
							hisDamage = (int)((Math.sqrt(myRegenHP.max-myRegenHP.temp)*16)*myIntakeS)*hisEvasion;
							hisRegenMA.temp -= 300;
							rushCD.duration = 60;
							hisCD.coolDown =0;
							rushAnimation = new Animation();
							rushAnimation.start();
						}
						
						//Sacrifical BAM!-Does damage to both opponent and player
						else if (action == 4 && hisCD.coolDown == 100 && hisRegenHP.temp >= 250)
						{
							if (myRegenHP.temp < (int)(250*myIntakeP)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)(250*myIntakeP)*hisEvasion;
							hisRegenHP.temp -= 250;
							hisDamage = (int)(150*myIntakeP)*hisEvasion;
							hisCD.coolDown =0;
							bossAttack = true;
							bossAttackAnimation.start();
						}
						
						//Hatred-Forces the player to use regular attack for a period of time
						else if (action == 5 && hisCD.coolDown == 100 && hatredCD.duration == 0 && hisRegenMA.temp >= 150)
						{
							if(hatred.duration == 0)
							{
								hatred.duration = 6;
								hatred.active = true;
							}
							else
								hatred.duration = 6;
							hisRegenMA.temp -= 150;
							insertBuff(hatred);
							hisCD.coolDown =0;
							hatredCD.duration = 15;
							hatredAnimation = new Animation();
							hatredAnimation.start();
						}
					}
					//Chronos
					if (bossNumber == 3)
					{	
						if (myRegenHP.temp <= (int)((boss.tempAT)*myIntakeP) && hisCD.coolDown == 100)
							action = 2;
						else if (poisson.duration == 0 && hisRegenMA.temp >= 200 && poissonCD.duration == 0)
							action = 3;
						else if ((myRegenHP.temp <= 1000 || hisRegenHP.temp <= 1000) && hisRegenMA.temp >= 150 && silenceCD.duration == 0)
							action = 4;
						else if (movesLevel[9] != 0 && hisRegenMA.temp >= 150 && silenceCD.duration == 0)
							action = 4;
						else if (lifeKeeper[9999] - hisRegenHP.temp > 500 && hisRegenMA.temp >= 250 && timeLapseCD.duration == 0)
							action = 5;
						else if (hisRegenMA.temp >= 30)
							action = 2;
						else
							action = 1;
					//Execution of AI action
						//Normal Attack
						if (action == 1 && hisCD.coolDown == 100)
						{
							if (myRegenHP.temp < (int)((boss.tempAT)*myIntakeS)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((boss.tempAT)*myIntakeS)*hisEvasion;
							hisCD.coolDown =0;
							hisDamage = (int)((boss.tempAT)*myIntakeP)*hisEvasion;
							bossAttack = true;
							bossAttackAnimation = new Animation();
							bossAttackAnimation.start();
						}
						//Brass Knuckle-A slightly stronger normal attack that can randomly paralyze the player
						else if (action == 2 && hisCD.coolDown == 100 && hisRegenMA.temp >= 30)
						{
							if (myRegenHP.temp < (int)((boss.tempAT)*myIntakeS)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((boss.tempAT)*myIntakeS)*hisEvasion;
							if (Math.random()*100 <= 20 && hisEvasion == 1)
							{
								brass.duration = 1;
								brass.active = true;
								insertBuff(brass);
							}
							hisRegenMA.temp -= 30;
							hisCD.coolDown =0;
							hisDamage = (int)((boss.tempAT)*myIntakeS)*hisEvasion;
							bossAttack = true;
							bossAttackAnimation = new Animation();
							bossAttackAnimation.start();
						}
						
						//Poisson-Refer to the animation section
						else if (action == 3 && hisCD.coolDown == 100 && hisRegenMA.temp >= 200 && poissonCD.duration == 0)
						{
							damageDealt = false;
							hisRegenMA.temp -= 200;
							poissonCD.duration = 20;
							hisCD.coolDown =0;
							poisonAnimation = new Animation();
							poisonAnimation.start();
						}
						//Silence-Stops the player from casting any spells for a period of time
						else if (action == 4 && hisCD.coolDown == 100 && hisRegenMA.temp >= 150 && silenceCD.duration == 0)
						{
							if(silence.duration == 0)
							{
								for (int i = 0; i < 20; i++)
									if (moves[i] != null)
										moves[i].setEnabled(false);
								silence.duration = 5;
								silence.active = true;
							}
							else
								silence.duration = 5;
							hisRegenMA.temp -= 150;
							insertBuff(silence);
							silenceCD.duration = 25;
							hisCD.coolDown =0;
						}
						
						//Time Lapse-refer to animation section
						else if (action == 5 && hisCD.coolDown == 100 && hisRegenMA.temp >= 250 && timeLapseCD.duration == 0)
						{
							hisRegenMA.temp -= 250;
							hisCD.coolDown =0;
							timeLapseCD.duration = 20;
							lapseAnimation = new Animation();
							lapseAnimation.start();
						}
					}
					
					//Masaeiv/Soul Keeper/Dianna
					if (bossNumber == 4)
					{
						if (myRegenHP.temp <= (int)(150*myIntakeS) && hisRegenMA.temp >= 50)
							action = 7;
						else if (!exort.active && hisRegenMA.temp >= 100)
							action = 4;
						else if (!wex.active && hisRegenMA.temp >= 100)
							action = 3;
						else if ((DPSPlayer >= 150 || hisRegenHP.temp <= 2000) && hisRegenMA.temp >= 200)
							action = 5;
						else if (!quas.active && hisRegenMA.temp >= 100)
							action = 2;
						else if (broken.active && (int)(myRegenHP.temp*0.1*myIntakeS)-(int)(150*myIntakeS) > 50 && hisRegenMA.temp >= 200)
							action = 6;
						else if (DPSPlayer >= 100 && hisRegenMA.temp >= 50)
							action = 7;
						else if ((int)(myRegenHP.temp*0.1*myIntakeS)-(int)(150*myIntakeS) > 50 && hisRegenMA.temp >= 200)
							action = 6;
						else if (hisRegenMA.temp >= 50)
							action = 7;
						else
							action = 1;
						
					//Execution of AI action
						//Normal Attack
						if (action == 1 && hisCD.coolDown == 100 && hisRegenMA.temp >= 30)
						{
							if (myRegenHP.temp < (int)((boss.tempAT)*myIntakeS)*hisEvasion)
								myRegenHP.temp =0;
							else
								myRegenHP.temp -= (int)((boss.tempAT)*myIntakeS)*hisEvasion;
							hisCD.coolDown =0;
							hisDamage = (int)((boss.tempAT)*myIntakeP)*hisEvasion;
							bossAttack = true;
							bossAttackAnimation = new Animation();
							bossAttackAnimation.start();
						}
						
						//Quas-An orb that increases health regeneration rate of the AI
						else if (action == 2 && hisCD.coolDown == 100 && hisRegenMA.temp >= 100)
						{
							if(quas.duration == 0)
							{
								hisRegenHP.rate += 25;
								quas.duration = 30;
								quas.active = true;
							}
							else
								quas.duration = 30;
							hisRegenMA.temp -= 100;
							insertBuffOp(quas);
							hisCD.coolDown =0;
						}
						//Wex-An orb that increases the mana regneration rate of the AI
						else if (action == 3 && hisCD.coolDown == 100 && hisRegenMA.temp >= 100)
						{
							if(wex.duration == 0)
							{
								hisRegenMA.rate += 25;
								wex.duration = 30;
								wex.active = true;
							}
							else
								wex.duration = 30;
							hisRegenMA.temp -= 100;
							insertBuffOp(wex);
							hisCD.coolDown =0;
						}
						//Exort-An orb taht increases the speed of the AI
						else if (action == 4 && hisCD.coolDown == 100 && hisRegenMA.temp >= 100)
						{
							exort.active = true;
							exort.duration = 30;
							hisRegenMA.temp -= 100;
							insertBuffOp(exort);
							hisCD.coolDown =0;
						}
						
						//Regeneration-Heals some health
						else if (action == 5 && hisRegenMA.temp >= 200 && hisCD.coolDown == 100)
						{
							if (hisRegenHP.max < hisRegenHP.temp+400)
								hisRegenHP.temp = hisRegenHP.max;
							else
								hisRegenHP.temp += 400;
							hisRegenMA.temp -= 200;
							hisCD.coolDown =0;
							regenAnimation = new Animation();
							regenAnimation.start();
						}
						
						//Soul Rip-refer to animation section
						else if (action == 6 && hisRegenMA.temp >= 200 && hisCD.coolDown == 100)
						{							
							hisRegenMA.temp -= 200;
							hisCD.coolDown =0;
							damageDealt = false;
							ripAnimation.start();
						}
						
						//Spirit Break-refer to animation section
						else if (action == 7 && hisRegenMA.temp >= 50 && hisCD.coolDown == 100)
						{							
							hisRegenMA.temp -= 50;
							hisCD.coolDown =0;
							damageDealt = false;
							breakerAnimation.start();
						}
					}
						
					draw.repaint();
					sleep(1);
					
            }
         } catch (InterruptedException e)
            {};
      }
   }
	
	//Duration of spells or effects that are static
	class Duration extends Thread
   {
      int duration;
		boolean active;
		public Duration()
		{
			duration = 0;
			active = false;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					if (challenged)
					{
						sleep (2000);
						challenged = false;
					}
               if (duration > 0)
						duration--;
					draw.repaint();
					
               sleep(1000);
            }
         } catch (InterruptedException e)
            {};
      }
   }
	
	//The real-time cool down of action
	class CoolDown extends Thread
   {
      int coolDown;
		int speed;
		public CoolDown(int SP)
		{
			coolDown = 0;
			speed = SP;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					if (challenged)
					{
						sleep (2000);
						challenged = false;
					}
               if (coolDown < 100)
						coolDown++;
					draw.repaint();
					
               sleep((int)(1000/speed));
            }
         } catch (InterruptedException e)
           {};
      }
   }
	
	//Background Regeneration of the Characters
	class Regen extends Thread
   {
      int max;
		int temp;
		int rate;
		public Regen(int maxBar, int tempBar, int speed)
		{
			max = maxBar;
			temp = tempBar;
			rate = speed;
		}
		public void run()
      {
         try
         {
            while(true)
            {
					if (challenged)
					{
						sleep (2000);
						challenged = false;
					}
               if (rate < 0 && temp > 0)
						temp+=rate;
					else if (temp < max)
						temp += rate;
					if (temp > max)
						temp = max;
					else if (temp < 0)
						temp = 0;
					draw.repaint();
					
               sleep(1000);
            }
         } catch (InterruptedException e)
            {};
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
					
               sleep(5000);
            }
         } catch (InterruptedException e)
            {};
      }
   }
   
   public static void main(String[] args)
   {
      //Battle battle = new Battle(3,another);
   }
	private static void printUsageAndExit()
	{
		out("SimpleAudioPlayer: usage:");
		out("\tjava SimpleAudioPlayer <soundfile>");
		System.exit(1);
	}
	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}
}