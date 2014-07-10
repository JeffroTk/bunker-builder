package mod.bunkerbuilder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class GameValues {
	/*NBTTagCompound tag = player.getEntityData();
	
	NBTBase modeTag = tag.getTag("money");
	if (modeTag != null) 
	{
		double money = ((NBTTagDouble) modeTag).func_150286_g();
		System.out.println("Current int:" + money);
		tag.setDouble("money", money + 1);
	}else
	{
		System.out.println("Initialzing");
		NBTTagDouble newtag = new NBTTagDouble(0);
		tag.setTag("money", newtag);
	}*/
	
	public static void initialize(EntityPlayer player)
	{
		NBTTagCompound tag = player.getEntityData();
		NBTTagDouble moneytag = new NBTTagDouble(0);
		NBTTagInt leveltag = new NBTTagInt(0);
		tag.setTag("money", moneytag);
	}
	public static void saveMoney(EntityPlayer player, double money)
	{
		NBTTagCompound tag = player.getEntityData();
		
		NBTBase modeTag = tag.getTag("money");
		if (modeTag != null) 
		{
			tag.setDouble("money", money);
		}
		else
		{
			initialize(player);
			modeTag = tag.getTag("money");
			tag.setDouble("money", money);
		}
	}
	
	/* Retrieve the money stored */
	public static double getMoney(EntityPlayer player)
	{
		NBTTagCompound tag = player.getEntityData();
		
		NBTBase modeTag = tag.getTag("money");
		if (modeTag != null) 
		{
			return ((NBTTagDouble) modeTag).func_150286_g();
		}
		else
		{
			initialize(player);
			modeTag = tag.getTag("money");
			return ((NBTTagDouble) modeTag).func_150286_g();
		}

	}
}
