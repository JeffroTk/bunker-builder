package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SandBagItem extends WallBlock
{
	//private Block sandbag;	
	private final double cost = 5;
	public SandBagItem(Block block)
	{
		super(block);
		//sandbag = block;
	}
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Cheap defensive structure");
		list.add("");
		list.add("Resistance: 8.0");
		list.add("Cost: 1");
	}
	
}
