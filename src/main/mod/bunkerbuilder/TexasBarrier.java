package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TexasBarrier extends Block {

	public TexasBarrier() {
		
		 super(Material.rock);
		 setHardness(2.5F);
		 setResistance(12.0F);
		 setStepSound(Block.soundTypeStone);
		 setBlockName("texasbarrier");
		 setBlockTextureName("concrete");
		 setCreativeTab(CreativeTabs.tabBlock);
		
	}

}
