package mod.bunkerbuilder;


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
	private int x = 1678;
	private int y = 4;
	private int z = 136;
	public ExplosivesSpawner()
	{
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  World world = Minecraft.getMinecraft().theWorld;
				  System.out.println("Creating explosive.");
				  world.setBlock(x,y,z, (Block)Block.blockRegistry.getObject("tnt"));
				  /* Add code inside the onBlockAdded method to set this tnt to primed.*/
			  }
			}, 2*10*1000, 2*10*1000);
	}
}
