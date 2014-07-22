package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class HescoItem extends WallBlock
{
	public HescoItem(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Moderately priced, reliable defense");
		list.add("");
		list.add("Resistance: 9.0");
		list.add("Cost: 6");
	}
}
