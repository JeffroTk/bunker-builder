package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import mod.bunkerbuilder.TileMultiBlock;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * @author Pierre
 *
 */
public class Sandbag extends Block implements ITileEntityProvider{

	public Sandbag () {
		 super(Material.sand);
		 setHardness(1.5F);
    	 setResistance(6.0F);
		 setStepSound(Block.soundTypeGravel);
		 setBlockName("sandbag");
		 setBlockTextureName("wool_colored_green");
		 setCreativeTab(CreativeTabs.tabBlock);
		
	}
	@Override
	/**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        TileMultiBlock tileentitychest = new TileMultiBlock();
        return tileentitychest;
    }
	

}