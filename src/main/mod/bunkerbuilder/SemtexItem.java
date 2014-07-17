package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SemtexItem extends ItemBlock
{
	public SemtexItem(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Cheap explosive");
		list.add("");
		list.add("Power: 3.0");
		list.add("Cost: 8");
	}
}
