package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * @author Pierre
 *
 */
public class Hesco extends Block {

	public Hesco () {
		 super(Material.ground);
		 setHardness(1.9F);
    	 setResistance(9.0F);
		 setStepSound(Block.soundTypeGravel);
		 setBlockName("hesco");
		 setBlockTextureName("stone");
		 setCreativeTab(CreativeTabs.tabBlock);
		
		
	}

}