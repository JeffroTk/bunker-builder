package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * @author Pierre
 *
 */
public class sandbag extends Block {

	public sandbag () {
		super(Material.sand);
		
		 setHardness(1.5F);
    	 setResistance(8.0F);
		 setStepSound(Block.soundTypeGravel);
		 setBlockName("sandbag");
		 setBlockTextureName("sand");
		
	}

}