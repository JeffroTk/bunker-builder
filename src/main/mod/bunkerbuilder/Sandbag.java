package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * 
 */
public class Sandbag extends Block{

	public Sandbag () {
		
		 super(Material.sand);
		 setHardness(1.5F);
    	 setResistance(8.0F);
		 setStepSound(Block.soundTypeGravel);
		 setBlockName("sandbag");
		 setBlockTextureName("wool_colored_green");
		 setCreativeTab(CreativeTabs.tabBlock);
	}
}