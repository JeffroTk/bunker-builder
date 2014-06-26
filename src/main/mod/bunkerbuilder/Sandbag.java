package mod.bunkerbuilder;

import net.minecraft.block.*;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import mod.bunkerbuilder.TileMultiBlock;
import net.minecraft.init.Blocks;

/**
 * They are cheap and can be crafted easily. They offer a decent blast resistance
 * @author Pierre
 *
 */
public class Sandbag extends BlockContainer implements ITileEntityProvider{

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
        TileMultiBlock multiblock = new TileMultiBlock();
        return multiblock;
    }
	
	//This method checks if primary block exists. 
    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block par5){
            TileMultiBlock tileEntity = (TileMultiBlock)world.getTileEntity(i, j, k);
            if (tileEntity != null){
                    //No need to check if block's Id matches the Id of our primary block, 
                    //because if a player want to change a block, he needs to brake it first, 
                    //and in this case block will be set to Air (Id = 0)
                    if(world.getBlock(tileEntity.getMasterX(), tileEntity.getMasterY(), 
                                    tileEntity.getMasterZ()) == Blocks.air){
                            world.setBlockToAir(i, j, k);
                            world.removeTileEntity(i, j, k);
                    }
            }
    }
    //This tells minecraft to render surrounding blocks.
    @Override
    public boolean isOpaqueCube(){
            return false;
    }
  //This block is called when block is broken and destroys the primary block.
    @Override
    public void breakBlock(World world, int i, int j, int k, Block block, int par6){
            //Reading the gag's tile entity.
            TileMultiBlock tileEntity = (TileMultiBlock)world.getTileEntity(i, j, k);
            //If not make this check, the game may crash if there's no tile entity at i, j, k.
            if (tileEntity != null){
                    //Actually destroys primary block.
                    world.setBlockToAir(i, j, k);
                    //Forces removing tile entity from primary block coordinates,
                    //cause sometimes minecraft forgets to do that.
                    world.removeTileEntity(tileEntity.getMasterX(), tileEntity.getMasterY(), tileEntity.getMasterZ());
            }
            //Same as above, but for the gag block tile entity.
            world.removeTileEntity(i, j, k);
    }
    
    

}