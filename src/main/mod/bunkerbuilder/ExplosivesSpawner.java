package mod.bunkerbuilder;

/*
 * Author: Craig
 * Description: Creates an explosive every handful seconds, will be updated later with more 
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class ExplosivesSpawner {
	
	/* coordinates are for specific save file */
	Random rand = new Random();
	private int x = rand.nextInt((1793 - 1663) + 1) + 1663;
	private int y = 4;
	private int z = rand.nextInt((151 - 121) + 1) + 121;
	
	public ExplosivesSpawner()
	{
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  World world = Minecraft.getMinecraft().theWorld; //get world reference
				  /* Create a block of explosives, type will depend on level */
				  
				  /* Spawns a primed explosive*/
				  C4Primed explosive = new C4Primed(world, x + 0.5F, y + 0.5F,z + 0.5F, (EntityLivingBase) null);
				  world.spawnEntityInWorld(explosive);
				  
				  //world.setBlock(x,y,z, (Block)Block.blockRegistry.getObject("tnt"));
				  //BlockTNT temp = (BlockTNT) world.getBlock(x,y,z);
				  //temp.func_150114_a(world, x, y, z, world.getBlockMetadata(x,y,z), (EntityLivingBase)null);
				  //world.setBlock(x,y,z, (Block)c4);
				  /* Add code inside the onBlockAdded method to set this tnt to primed.*/
			  }
			}, 2*10*1000, 2*10*1000); // repeating timer for every 20 seconds, will be changed later when levels are added
	}
}
