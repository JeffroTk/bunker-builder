package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class DynamiteItem extends ItemBlock
{
	public DynamiteItem(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Powerful explosive");
		list.add("");
		list.add("Power: 3.5");
		list.add("Cost: 12");
	}
}
