package mod.bunkerbuilder;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SandBagItem extends ItemBlock
{
	private Block sandbag;	
	private final double cost = 5;
	public SandBagItem(Block block)
	{
		super(block);
		sandbag = block;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Cheap defensive structure");
		list.add("");
		list.add("Resistance: 6.0");
		list.add("Cost: 1");
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
        		if(!checkCosts())
        			return false;
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
                if(canPlace){
                	int xcord,zcord;
                	for(int j=0;j< 4;j++){
                		xcord = x; zcord = z;
                		for(int i = 0; i < gagShift.length; i++){
                            shift = rotXZByDir(gagShift[i][0], y, gagShift[i][1], dir);
                            world.setBlock(xcord, y, zcord, sandbag, dir, 0x02);
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
    
    /*
     * Checks if the player has enough money available to build the structure.
     * If he doesn't, return false.
     * If he does, substract it from his current money and return true
     */
    private boolean checkCosts()
    {
    	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    	Double money = GameValues.getMoney(Minecraft.getMinecraft().thePlayer);
    	if(money >= cost)
    	{
    		GameValues.saveMoney(player, money - cost);
    		return true;
    	}
    	System.out.println("Not enough money");
    	return false;
    }
}
