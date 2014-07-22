package mod.bunkerbuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class GameLevel {
	private int level;
	private int zmax, zmin, xmax, xmin; //restricting coordinates
	
	/* Initial money given to the player at beginning of each level */
	private static final int levelOneMoney = 100;
	private static final int levelTwoMoney = 150;
	private static final int levelThreeMoney = 200;
	
	public GameLevel()
	{
		level = 1;
		zmax = 148;
		zmin = 117;
		xmax = 1694;
		xmin = 1667;
	}
	
	/* Start the first level */
	@SubscribeEvent
	public void firstJoin(PlayerLoggedInEvent event)
	{
		/* Wait for the game to fully load before doing this*/
    	try{
    		Thread.sleep(1000);
    		
    		/* Set initial money value */
    		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    		GameValues.saveMoney(player, levelOneMoney);
    		
    		/* Spawn the object the player must defend */
    		
    		/* Start timer*/
    	}catch(InterruptedException ie)
    	{
    		System.out.println("Unable to initialize game.");
    	}
    	
	}
	
	public void gameTimer()
	{
		
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
