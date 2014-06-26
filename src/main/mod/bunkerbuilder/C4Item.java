package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class C4Item extends ItemBlock
{
	public C4Item(Block block)
	{
		super(block);
	}

	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Even more powerfuller explosive");
		list.add("");
		list.add("Power: 4.5");
		list.add("Cost: 20");
	}
}
