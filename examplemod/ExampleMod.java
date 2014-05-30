package example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    @Instance(ExampleMod.MODID)
    public static ExampleMod exampleMod;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	/*Block sample = new sampleBlock(Material.dragonEgg)
    		.setHardness(4000F)
    		.setResistance(2000.0F)
    		.setStepSound(Block.soundTypeGravel)
    		.setBlockName("sampleBlock")
    		.setCreativeTab(CreativeTabs.tabBlock);
    		*/
    	/*Block sampTNT = new sampleTNT();
    		.setHardness(0.0F)
    		.setStepSound(Block.soundTypeGrass)
    	*/
    	Block sample = new sampleTNT()
    		.setHardness(0.0F)
    		.setStepSound(Block.soundTypeGrass)
    		.setBlockName("sampletnt")
    		.setBlockTextureName("tnt");
    //GameRegistry.registerBlock(sample, "sampleBlock");
    GameRegistry.registerBlock(sample, "sampletnt");

    
    }
}
