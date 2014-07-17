package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TexasBarrierItem extends ItemBlock
{
	private Block texasbarrier;
	public TexasBarrierItem(Block block)
	{
		super(block);
		texasbarrier = block;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Very strong defense measure");
		list.add("");
		list.add("Resistence: 12.0");
		list.add("Cost: 8");
	}
	
	@Override
	public boolean placeBlockAt(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffSet, int metadata)
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
                
                //Takes the player sight direction
                int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
                //This array will store information about the coordinates where we want to place our gags relatively to the primary block.
                //To add blocks, just add some more {rel_x, rel_y, rel_z} into the array.
                //Exactly this array will add three gag block from the side of the primary block.
                int[][] gagShift = {{-1, 0, -1}, {-1, 0, 0}};
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
                	int xcord,ycord,zcord;
                	for(int h=0;h<4;h++){
                		ycord=y;
                		for(int j=0;j< 4;j++){
	                		xcord = x; zcord = z;
	                		for(int i = 0; i < gagShift.length; i++){
	                            shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
	                            world.setBlock(xcord, ycord, zcord, texasbarrier, dir, 0x02);
	                            xcord -= shift[0];
	                            zcord -= shift[2];
	                    	}
	                		ycord++;
	                	}
	                	x++;
	                	z--;
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
