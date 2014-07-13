package mod.bunkerbuilder;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
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
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffSet)
    {
		//System.out.println(props.getMoney());
		//Prevents itemstack from decreasing when in creative mod
        if (!player.capabilities.isCreativeMode){
            --item.stackSize;
        }
        //Prevents from making changes in inactive world
        if (!world.isRemote){
	        	Double mon = GameValues.getMoney(player);
	        	System.out.println("Money: " + mon);
	        	GameValues.saveMoney(player, mon + 1);
                //Increases y coordinate, so our block will be placed on top of the block you clicked, just as it should be
                y++;
                //Takes the player sight direction
                int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
                //This array will store information about the coordinates where we want to place our gags relatively to the primary block.
                //To add blocks, just add some more {rel_x, rel_y, rel_z} into the array.
                //Exactly this array will add three gag block from the side of the primary block.
                int[][] gagShift = {{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}};
                int[] shift;
                //This cycle will prevent us from placing block instead of other ones, more commonly, it checks if the places where we want
                //to place gags are empty.
                boolean canPlace = true;
                for(int i = 0; i < gagShift.length; i++){
                        shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                        if(!world.isAirBlock(x + shift[0], y + shift[1], z + shift[2])){
                                canPlace = false;
                        }
                }
                //If the check was successful
                if(canPlace){
                	world.setBlock(x, y, z, Blocks.dirt, dir, 0x02);
                	for(int i = 0; i < gagShift.length; i++){
                        shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                        world.setBlock(x + shift[0], y, z + shift[2], Blocks.dirt, dir, 0x02);
                	}
					return true;
                }
        }
        return false;
    }
	
	//This function rotates the relative coordinates accordingly to the given direction
    public int[] rotXZByDir(int x, int y, int z, int dir){
    	if (dir == 0){
    		return new int[]{x, y, z};
        }else if(dir == 1){
            return new int[]{-z, y, x};
        }else if(dir == 2){
            return new int[]{-x, y, -z};
        }else{
            return new int[]{z, y, -x};
        }
    }
}
