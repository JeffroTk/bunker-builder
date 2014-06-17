package mod.bunkerbuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class GameLevel {
	private int level;
	private int zmax = 148, zmin = 117, xmax = 1694, xmin = 1667;
	
	/*
	 * Check the player coordinates every tick and restrict them to the current level
	 * 
	 */
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event)
	{
		System.out.println("Moving player");
		Minecraft mc = Minecraft.getMinecraft();
		Vec3 vec = mc.thePlayer.getPosition(1.0F);
		if(vec.xCoord > xmax)
			mc.thePlayer.setPosition(xmax, vec.yCoord, vec.zCoord);
			
		//mc.thePlayer.setPosition(1,1,1);
	}
	
}
