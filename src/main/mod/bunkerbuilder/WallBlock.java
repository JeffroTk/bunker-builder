package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/* Main multiblock class used for placing blocks and checking costs*/
public class WallBlock extends ItemBlock{

	private Block blockType;
	private double cost;
	
	public WallBlock(Block block) {
		super(block);
		blockType = block;
		if(block instanceof Sandbag)
			cost = 5;
		else if(block instanceof Hesco)
			cost = 6;
		else if(block instanceof TexasBarrier)
			cost = 8;
		else
			cost = 0;
	}
	
	/*
	 * Places a multiblock structure in the form of a 3x1 based on the player's current facing direction
	 * Blocks require a certain amount of money to be placed (which is substracted from total money once the block is placed)
	 * 
	 */
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
                
                //Takes the player sight direction
                int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
                //This array will store information about the coordinates where we want to place our gags relatively to the primary block.
                //To add blocks, just add some more {rel_x, rel_y, rel_z} into the array.
                //Exactly this array will add three gag block from the side of the primary block.
                int[][] gagShift = {{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1},{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}};
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
                if(canPlace && checkCosts()){
                	int xcord,zcord;
                	/* Height of the block structure */
                	for(int j=0;j< 4;j++){
                		xcord = x; zcord = z;
                		/* Width of the block structure*/
                		for(int i = 0; i < 3; i++){
                            shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                            world.setBlock(xcord, y, zcord, blockType, dir, 0x02);
                            xcord += shift[0];
                            zcord += shift[2];
                    	}
                		y++;
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
    
    /* Checks if player has enough money to place the block, if he does then save the value. */
    private boolean checkCosts()
    {
    	System.out.println(cost);
    	/* Retrieve player's current money */
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	Double money = GameValues.getMoney(Minecraft.getMinecraft().thePlayer);
    	
    	/* Checks if player has enough money */
    	if(money >= cost)
    	{
    		GameValues.saveMoney(player, money - cost);
    		return true;
    	}
    	System.out.println("Not enough money");
    	return false;
    }

}
