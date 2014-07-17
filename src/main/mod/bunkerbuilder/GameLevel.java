package mod.bunkerbuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class GameLevel {
	private int level;
	private int zmax, zmin, xmax, xmin; //restricting coordinates
	
	public GameLevel()
	{
		level = 1;
		zmax = 148;
		zmin = 117;
		xmax = 1694;
		xmin = 1667;
	}
	
	
	/*
	 * Check the player coordinates every tick and restrict them to the current level
	 * 
	 */
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event)
	{
		
		Minecraft mc = Minecraft.getMinecraft();
		mc.theWorld.setWorldTime(75000);
		Vec3 vec = mc.thePlayer.getPosition(1.0F);
		if(checkXMax(vec.xCoord))
			mc.thePlayer.setPosition(xmax, vec.yCoord, vec.zCoord);
		if(checkXMin(vec.xCoord))
			mc.thePlayer.setPosition(xmin, vec.yCoord, vec.zCoord);
		if(checkZMax(vec.zCoord))
			mc.thePlayer.setPosition(vec.xCoord, vec.yCoord, zmax);
		if(checkZMin(vec.zCoord))
			mc.thePlayer.setPosition(vec.xCoord, vec.yCoord, zmin);
			
		//mc.thePlayer.setPosition(1,1,1);
	}
	
	/* Checks whether the x coordinate is outside minimum range */
	public boolean checkXMin(double x)
	{
		return x < xmin;
	}
	
	/* Checks whether the x coordinate is outside maximum range */
	public boolean checkXMax(double x)
	{
		return x > xmax;
	}
	
	/* Checks whether the Z coordinate is outside maximum range */
	public boolean checkZMax(double z)
	{
		return z > zmax;
	}
	
	/* Checks whether the Z coordinate is outside minimum range */
	public boolean checkZMin(double z)
	{
		return z < zmin;
	}
	
}
