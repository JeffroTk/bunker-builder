package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/*
 * These are slightly more expensive than some of the other blocks. They are pretty resistant to explosives.
 */
public class TexasBarrier extends BlockFalling {

	public TexasBarrier() {
		
		 super(Material.rock);
		 setHardness(2.5F);
		 setResistance(16.0F);
		 setStepSound(Block.soundTypeStone);
		 setBlockName("texasbarrier");
		 setBlockTextureName("stone_slab_side");
		 setCreativeTab(CreativeTabs.tabBlock);		
	}

}
