//This is a class that can create Character objects with a set values of stats
class Character
{
	int attack = 80;
	int defense = 10;
	int speed = 20;
	int evasion = 0;
	int health = 4256;
	int mana = 2145;
	int magicalDefense = 10;
	int regHP = 10;
	int regMA = 6;
	int tempAT = 80;
	int tempDE = 10;
	int tempSP = 20;
	int tempEV = 0;
	int tempHP = 4256;
	int tempMA = 2145;
	int tempMD = 10;
	int tempRHP = 10;
	int tempRMA = 6;
	
	//This is used in conjunction with NewSkills
	//Enemy character objects would be created by me == false, while player character object would be created by extracting information
	//from NewSkills
	public Character(boolean me)
	{
		if (me == true)
		{
			attack += NewSkills.stats()[0]*7;
			defense += NewSkills.stats()[1]*5;
			speed += NewSkills.stats()[2];
			evasion += (int)(NewSkills.stats()[3]*0.75);
			health += NewSkills.stats()[4]*106;
			mana += NewSkills.stats()[5]*37;
			magicalDefense += NewSkills.stats()[6]*5;
			regHP += (int)(Math.sqrt(NewSkills.stats()[4]*5));
			regMA += (int)(Math.sqrt(NewSkills.stats()[5]*5));
			tempAT += NewSkills.stats()[0]*7;
			tempDE += NewSkills.stats()[1]*5;
			tempSP += NewSkills.stats()[2];
			tempEV += (int)(NewSkills.stats()[3]*0.75);
			tempHP += NewSkills.stats()[4]*106;
			tempMA += NewSkills.stats()[5]*37;
			tempMD += NewSkills.stats()[6]*5;
			tempRHP += (int)(Math.sqrt(NewSkills.stats()[4]*5));
			tempRMA += (int)(Math.sqrt(NewSkills.stats()[5]*5));
		}
	}
}