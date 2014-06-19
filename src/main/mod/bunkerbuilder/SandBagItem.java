package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SandBagItem extends ItemBlock
{
	public SandBagItem(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Cheap defensive structure");
		list.add("");
		list.add("Hardness: 1.0");
		list.add("Cost: 1");
	}
}
