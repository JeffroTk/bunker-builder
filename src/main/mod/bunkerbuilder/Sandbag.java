package mod.bunkerbuilder;

import net.minecraft.block.*;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraft.init.Blocks;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * 
 *
 */
public class Sandbag extends Block{

	public Sandbag () {
		 super(Material.sand);
		 setHardness(1.5F);
    	 setResistance(6.0F);
		 setStepSound(Block.soundTypeGravel);
		 setBlockName("sandbag");
		 setBlockTextureName("wool_colored_green");
		 setCreativeTab(CreativeTabs.tabBlock);
	}
}