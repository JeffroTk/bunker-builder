package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TexasBarrierItem extends ItemBlock
{
	public TexasBarrierItem(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Very strong defense measure");
		list.add("");
		list.add("Resistence: 12.0");
		list.add("Cost: 4");
	}
}
