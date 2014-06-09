package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BunkerBuilder.MODID, version = BunkerBuilder.VERSION)
public class BunkerBuilder
{
    public static final String MODID = "Bunker Builder";
    public static final String VERSION = "1.0";
    
    @Instance(BunkerBuilder.MODID)
    public static BunkerBuilder exampleMod;
    
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
    	
    	
    	/********************************************************/
    	/**********************EXPOLSIVES***********************/
    	
    	Block c4Block = new c4()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("c4")
			.setBlockTextureName("tnt");
    	
    	Block semtexBlock = new semtex()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("semtex")
			.setBlockTextureName("tnt");
    	
    	Block dynamiteBlock = new dynamite()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("dynamite")
			.setBlockTextureName("tnt");
    	
        GameRegistry.registerBlock(c4Block, "c4");
        GameRegistry.registerBlock(semtexBlock, "semtex");
        GameRegistry.registerBlock(dynamiteBlock, "dynamite");
    	/********************************************************/
    	/********************************************************/
        
        //FMLCommonHandler.instance().bus().register(new SpawnRemoval());
        MinecraftForge.EVENT_BUS.register(new SpawnRemoval());
        MinecraftForge.EVENT_BUS.register(new ExplosivesSpawner());
    //GameRegistry.registerBlock(sample, "sampleBlock");

    
    }
}
