package mod.bunkerbuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    	
    	Block c4Block = new C4()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("c4")
			.setBlockTextureName("c4_side");
    	
    	Block semtexBlock = new Semtex()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("semtex")
			.setBlockTextureName("pumpkin_side");
    	
    	Block dynamiteBlock = new Dynamite()
	    	.setHardness(0.0F)
			.setStepSound(Block.soundTypeGrass)
			.setBlockName("dynamite")
			.setBlockTextureName("dynamite");
    	
        GameRegistry.registerBlock(c4Block, C4Item.class,  "c4");
        GameRegistry.registerBlock(semtexBlock, SemtexItem.class, "semtex");
        GameRegistry.registerBlock(dynamiteBlock, DynamiteItem.class, "dynamite");
        
        /********************************************************/
		/********************************************************/
       
        
        /********************************************************/
    	/**********************FORCE PROTECTION******************/
        
        Block sandbag = new Sandbag();
        ItemStack sandbagstack = new ItemStack(new Sandbag());
        ItemStack sandstack = new ItemStack(Block.getBlockById(12));
        ItemStack clothstack = new ItemStack(Block.getBlockById(35));
        
        Block hesco = new Hesco();
        ItemStack hescobastion = new ItemStack(new Hesco());
        ItemStack steelgrid = new ItemStack(Block.getBlockById(15));
        ItemStack gravelstack = new ItemStack(Block.getBlockById(13));
        
        
	    Block texasbarrier = new TexasBarrier();
		GameRegistry.registerBlock(texasbarrier, TexasBarrierItem.class, "TexasBarrier");
		
		
	    GameRegistry.registerBlock(sandbag, SandBagItem.class, "sandbag");
		//GameRegistry.registerBlock(sandbag, "sandbag");
	    GameRegistry.addShapelessRecipe(sandbagstack, sandstack, clothstack);
	    
	    GameRegistry.registerBlock(hesco, HescoItem.class, "hesco");
	    GameRegistry.addShapelessRecipe(hescobastion, steelgrid, gravelstack);
	    
	    /********************************************************/
		/********************************************************/
	    
        
        //FMLCommonHandler.instance().bus().register(new SpawnRemoval());
        MinecraftForge.EVENT_BUS.register(new SpawnRemoval());
        MinecraftForge.EVENT_BUS.register(new ExplosivesSpawner());
        FMLCommonHandler.instance().bus().register(new GameLevel());

        //GameRegistry.registerBlock(sample, "sampleBlock");
        
        sandbag.setCreativeTab(this.tabCustom);
        texasbarrier.setCreativeTab(this.tabCustom);
        hesco.setCreativeTab(this.tabCustom);
        c4Block.setCreativeTab(this.tabCustom);
        dynamiteBlock.setCreativeTab(this.tabCustom);
        semtexBlock.setCreativeTab(this.tabCustom);

    }
    
    //Adds new tab with anvil as tab icon
    public static CreativeTabs tabCustom = new CreativeTabs("Resources") {
        @Override
        @SideOnly(Side.CLIENT)        
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.anvil);
        }
        	
    };
    
}
