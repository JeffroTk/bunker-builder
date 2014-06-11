package mod.bunkerbuilder;

/*
 * Author: Craig
 * Description: Creates an explosive every handful seconds, will be updated later with more 
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import java.util.Timer;
import java.util.TimerTask;

public class ExplosivesSpawner {
	
	/* coordinates are for specific save file */
	private int x = 1678;
	private int y = 4;
	private int z = 136;
	
	public ExplosivesSpawner()
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  World world = Minecraft.getMinecraft().theWorld; //get world reference
				  /* Create a block of explosives, type will depend on level */
				  
				  System.out.println("Creating explosive");
				  
				  world.setBlock(x,y,z, (Block)Block.blockRegistry.getObject("c4"));
				  //BlockTNT c4 = new BlockTNT();
				  //world.setBlock(x,y,z, (Block)c4);
				  /* Add code inside the onBlockAdded method to set this tnt to primed.*/
			  }
			}, 2*10*1000, 2*10*1000); // repeating timer for every 20 seconds, will be changed later when levels are added
	}
}
