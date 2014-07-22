package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TexasBarrierItem extends WallBlock
{
	private Block texasbarrier;
	public TexasBarrierItem(Block block)
	{
		super(block);
		texasbarrier = block;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Very strong defense measure");
		list.add("");
		list.add("Resistence: 12.0");
		list.add("Cost: 8");
	}
	

}
