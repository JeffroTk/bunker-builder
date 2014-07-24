package mod.bunkerbuilder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
/*
 * Keeps track of important player variables throughout the game
 * Notably: player money and game time
 * 
 */
public class GameValues {
	
	/* initializes the timer variable if necessary */
	public static void initialize(EntityPlayer player, String s)
	{
		NBTTagCompound tag = player.getEntityData();
		NBTTagDouble val_tag = new NBTTagDouble(0);
		tag.setTag(s, val_tag);
	}
	
	/* Stores the value val to the given string tagName*/
	public static void setValue(EntityPlayer player, double val, String tagName)
	{
		NBTTagCompound tag = player.getEntityData();
		
		NBTBase modeTag = tag.getTag(tagName);
		if (modeTag != null) 
		{
			tag.setDouble(tagName, val);
		}
		else
		{
			initialize(player, tagName);
			modeTag = tag.getTag(tagName);
			tag.setDouble(tagName, val);
		}
	}
	
	/* retrieves the double stored in the given string s*/
	public static double getVal (EntityPlayer player, String s)
	{
		NBTTagCompound tag = player.getEntityData();
		
		NBTBase modeTag = tag.getTag(s);
		if (modeTag != null) 
		{
			return ((NBTTagDouble) modeTag).func_150286_g();
		}
		else
		{
			initialize(player, s);
			modeTag = tag.getTag(s);
			return ((NBTTagDouble) modeTag).func_150286_g();
		}

	}
	
	public static void initializeMoney(EntityPlayer player)
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
			initializeMoney(player);
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
			initializeMoney(player);
			modeTag = tag.getTag("money");
			return ((NBTTagDouble) modeTag).func_150286_g();
		}

	}
}
