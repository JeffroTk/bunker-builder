package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SandBagItem extends Item
{
	/*public SandBagItem(Block block)
	{
		super(block);
	}*/
	
	public SandBagItem()
	{
		maxStackSize = 64;
        setUnlocalizedName("genericItem");
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Cheap defensive structure");
		list.add("");
		list.add("Resistance: 6.0");
		list.add("Cost: 1");
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
    {
       world.setBlock(x,y,z, Blocks.dirt);
       world.setBlock(x+1,y,z, Blocks.dirt);
       world.setBlock(x+2,y,z, Blocks.dirt);
       return true;
    }
}
