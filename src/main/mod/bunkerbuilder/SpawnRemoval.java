package mod.bunkerbuilder;

/*
 * Author: Craig
 * Prevents hostile/friendly mobs from spawning into games
 */

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
//import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpawnRemoval {

	//taken from http://www.minecraftforge.net/forum/index.php?topic=8937.0
	
	@SubscribeEvent
	public void CheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		//if(event.entity instanceof EntitySkeleton || event.entity instanceof EntityZombie || event.entity instanceof EntitySpider) {
	    //	event.setCanceled(true);
	    //}
		//System.out.println("denied spawn");
		event.setResult(Result.DENY);
	}
}
