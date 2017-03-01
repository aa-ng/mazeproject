import java.util.Random;


/** Item that can give the player both good and bad effects
 * @author Alex Ng
 *
 */
public class Potion extends Item
{
	/** Random object for random numbers
	 * @author Alex Ng
	 */
	Random random = new Random();
	
	/** Array of potion effects each index contains a different identifier for a different effect
	 * @author Alex Ng
	 */
	protected static int potionIDs[] = new int[6];
	
	/** Stores the different prefixes/colors to the hidden potion names
	 * @author Alex Ng
	 */
	protected final static String hiddenNames[] = {"Red","Orange","Yellow","Gold","Lime","Green",
											"Cyan","Turqouis","AquaMarine","Blue","violet",
											"Purple"};
	
	protected final int REGENDURRATION = 6;
	protected final int STRENGTHDURRATION = 5;
	protected final int POISONDURRATION = 7;
	protected final int FIREDURRATTION = 5;
	
	
	/** Stores the identifier for the potion 
	 * @author Alex Ng
	 */
	private int id;
	
	/** Creates a random potion
	 * @author Alex Ng
	 */
	public Potion()
	{
		super(null);
		randomPotion();
		setName(determineName());
	}
	
	/** Potion constructor with the following information
	 * @author Alex Ng
	 * @param name
	 */
	public Potion(String name)
	{
		super(name);
		randomPotion();
		setName(determineName());
	}
	
	/** Potion constructor with the following information
	 * @author Alex Ng
	 * @param name
	 * @param id
	 */
	public Potion(String name, int id)
	{
		super(name);
		setID(id);
	}
	
	/** Returns the random color of the potion according to its effect, all potions 
	 * of the same effect will have their own designated color
	 * @author Alex Ng
	 * @return String Color
	 */
	public String determineName()
	{
		String color = null;
		int identity = getID();
		for (int index = 0; index < hiddenNames.length; index++)
		{
			if (index == identity)
				color = hiddenNames[index]+" Potion";
		}
		return color;
	}
	
	/** Determines a random potion identifier
	 * @author Alex Ng
	 * @return
	 */
	public void randomPotion()
	{
		int potionIndex = random.nextInt(hiddenNames.length);
		setID(potionIndex);
	}
	
	/** Randomizes the potionIDs
	 * @author Alex Ng
	 */
	public void randomizeIDs()
	{
		for (int id = 0; id < potionIDs.length; id++)
		{
			boolean unique = false;
			while (unique == false)
			{
				potionIDs[id] = random.nextInt(potionIDs.length);
				unique = true;
				for (int check = id-1; check >= 0; check--)
				{
					if (potionIDs[id]==potionIDs[check])
						unique = false;
				}
			}
		}
	}
	
	/** Prints all the identifiers for potions
	 * @author Alex Ng
	 */
	public void printIDs()
	{
		for (int i : potionIDs)
		{
			System.out.println(i);
		}
	}
	
	/** Returns the identifier of the potion
	 * @author Alex Ng
	 * @return int id
	 */
	public int getID()
	{
		return id;
	}
	
	/** Returns the effect of the potion
	 * @author Alex Ng
	 * @return String effectName
	 */
	public String getEffect()
	{
		String effectName = null;
		int identity = getID();
		if (identity == getHealID())
			effectName = "Heal";
		else if (identity == getRegenID())
			effectName = "Regen";
		else if (identity == getStrengthID())
			effectName = "Strength";
		else if (identity == getWeaknessID())
			effectName = "Weakness";
		else if (identity == getPoisonID())
			effectName = "Poison";
		else if (identity == getFireID())
			effectName = "Fire";
		return effectName;
	}
	
	/** Returns the array of potion IDs
	 * @author Alex Ng
	 * @return in[] potionIDs
	 */
	public int[] getPotionIDs()
	{
		return potionIDs;
	}
	
	/** Returns the identifier for the health potions
	 * @author Alex Ng
	 * @return int potionIDs[0]
	 */
	public int getHealID()
	{
		return getPotionIDs()[0];
	}
	
	/** Returns the identifier for the regeneration potions
	 * @author Alex Ng
	 * @return int potionIDs[1]
	 */
	public int getRegenID()
	{
		return getPotionIDs()[1];
	}
	
	/** Returns the identifier for strength potions
	 * @author Alex Ng
	 * @return int potionIDs[2]
	 */
	public int getStrengthID()
	{
		return getPotionIDs()[2];
	}
	
	/** Returns the identifier for weakness potions
	 * @author Alex Ng
	 * @return int potionIDs[3]
	 */
	public int getWeaknessID()
	{
		return getPotionIDs()[3];
	}
	
	/** Returns the identifier for poision potions
	 * @author Alex Ng
	 * @return int potionIDs[4]
	 */
	public int getPoisonID()
	{
		return getPotionIDs()[4];
	}
	
	/** Returns the identifier for fire potions
	 * @author Alex Ng
	 * @return int potionIDs[5]
	 */
	public int getFireID()
	{
		return getPotionIDs()[5];
	}
	
	/** Sets the identifier for the potion
	 * @author Alex Ng
	 * @param id
	 */
	public void setID(int id)
	{
		this.id = id;
	}
}
