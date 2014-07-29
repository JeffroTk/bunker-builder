package mod.bunkerbuilder;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.registry.GameData;

public class GameLevel {
	private int level;
	private static final int xmax1 = 1694, xmin1 = 1667; //restricting coordinates
	private static final int zmax1 = 148, zmin1 = 117;//restricting coordinates
	private static final int zmax2 = -32, zmin2 = -63; //restricting coordinates
	private static final int zmax3 = -200, zmin3 = -240; //restricting coordinates
	
	private int xmin, xmax, zmax, zmin;
	private Block lifeBlock;
	
	/* Initial money given to the player at beginning of each level */
	private static final int levelOneMoney = 100;
	private static final int levelTwoMoney = 150;
	private static final int levelThreeMoney = 200;
	
	/* Time the player has to place blocks in seconds */
	private static final int levelOneTime = 60;
	private static final int levelTwoTime = 60;
	private static final int levelThreeTime = 60;
	
	public GameLevel()
	{
		level = 1;
		xmin = xmin1;
		xmax = xmax1;
		zmin = zmin1;
		zmax = zmax1;
		
	}
	
	/* Checks if the game was lost by checking the life block is still alive 
	 * Takes the coordinates of the lifeblock as parameter 
	 * Returns true if game was lost
	 * */
	public boolean checkLoss(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x,y,z);
		if(block.getMaterial() == Material.air)
			return true;
		return false;
	}
	
	/* Sets the necessary values to increase to the next
	 * level of the game.
	 * - Player coordinates
	 * - level variable
	 * - player money
	 * 
	 * */
	public void nextLevel()
	{
		this.level = this.level + 1;
		
		/* update the coordinate boundary */
		if(level == 2)
		{
			zmin = zmin2;
			zmax = zmax2;
			GameValues.saveMoney(Minecraft.getMinecraft().thePlayer, levelTwoMoney);
		}
		
		
		
	}
	
	/* Start the first level when the player joins the game
	 * 
	 * */
	@SubscribeEvent
	public void firstJoin(PlayerLoggedInEvent event)
	{
		/* Wait for the game to fully load before doing this*/
    	try{
    		Thread.sleep(1000);
    		
    		/* Set initial money value */
    		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    		GameValues.saveMoney(player, levelOneMoney);
    		
    		/* Start timer*/
    		level = 1;
    		gameTimer(levelOneTime);
    	}catch(InterruptedException ie)
    	{
    		System.out.println("Unable to initialize game.");
    	}
    	
	}
	
	/* Used as a simple timer for the gametime*/
	class SimpleTimer extends TimerTask
	{
		double timeLeft;
		
		public SimpleTimer(double timeLeft)
		{
			super();
			this.timeLeft = timeLeft;
		}
		
		public void run()
		{
			gameTimer(timeLeft);
		}
	}
	
	/* If timeLeft is equal to 0 than timer has elapsed, else recursively call gameTimer with timeLeft-1 
	 * Also updates the value that the HUD displays to the user
	 * */
	public void gameTimer(double timeLeft)
	{
		/* Update the GUI */
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(timeLeft >= 0) //don't update negative values
			GameValues.setValue(Minecraft.getMinecraft().thePlayer, timeLeft, "timer");
    	
    	/* Spawn defense block */
    	if(timeLeft == 59){
    		//LifeBlock life = new LifeBlock();
    		lifeBlock = spawnLifeBlock((xmin+xmax)/2, 4, (zmin+zmax)/2);
    	}
		if(timeLeft == 0)
		{
			/* Timer is finished*/
			/* Call the explosives */
			if(level == 1)
			{
				ExplosivesSpawner.spawnExplosive(Minecraft.getMinecraft().theWorld, 0, (xmin+xmax)/2, 10, (zmin+zmax)/2 );
			}else if(level == 2)
			{
				System.out.println("Spawning level 2 explosives");
				ExplosivesSpawner.spawnExplosive(Minecraft.getMinecraft().theWorld, 0, ((xmin+xmax)/2)+2, 10, (zmin+zmax)/2 );
				ExplosivesSpawner.spawnExplosive(Minecraft.getMinecraft().theWorld, 0, (xmin+xmax)/2, 10, (zmin+zmax)/2 );
				ExplosivesSpawner.spawnExplosive(Minecraft.getMinecraft().theWorld, 0, ((xmin+xmax)/2)+1, 10, ((zmin+zmax)/2)+1 );
			}
		}else if(timeLeft == -7)
		{
			if(checkLoss(Minecraft.getMinecraft().theWorld, (xmin+xmax)/2, 4, (zmin+zmax)/2))
			{
				System.out.println("Game loss");
			}else
			{
				System.out.println("Next level");
				nextLevel();
				timeLeft = 60;
			}
		}
		/* Call game timer again after one second */
		Timer timer = new Timer();
		timer.schedule(new SimpleTimer(timeLeft-1), 1000);
		
	}
	
	/*
	 * Creates the block that the player must defend in the game
	 * Takes coordinates to place the block
	 * 
	 */
	public Block spawnLifeBlock(int x, int y, int z)
	{
		World world = Minecraft.getMinecraft().theWorld;
		world.setBlock(x, y, z, Blocks.dirt, 0, 0x02);
		return world.getBlock(x,y,z);
	}
	
	/*
	 * Check the player coordinates every tick and restrict them to the current level
	 * 
	 */
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent event)
	{
		
		Minecraft mc = Minecraft.getMinecraft();
		mc.theWorld.setWorldTime(75000);
		Vec3 vec = mc.thePlayer.getPosition(1.0F);
		if(checkXMax(vec.xCoord))
			mc.thePlayer.setPosition(xmax, vec.yCoord, vec.zCoord);
		if(checkXMin(vec.xCoord))
			mc.thePlayer.setPosition(xmin, vec.yCoord, vec.zCoord);
		if(checkZMax(vec.zCoord))
			mc.thePlayer.setPosition(vec.xCoord, vec.yCoord, zmax);
		if(checkZMin(vec.zCoord))
			mc.thePlayer.setPosition(vec.xCoord, vec.yCoord, zmin);
		
		if (mc.thePlayer.capabilities.isFlying){
			mc.thePlayer.rotationPitch = 90;
			mc.thePlayer.rotationYaw = 90;
			mc.thePlayer.setPosition(vec.xCoord, 11, vec.zCoord);
			//mc.thePlayer.eyeHeight = 15f;
			mc.thePlayer.capabilities.setFlySpeed(0.1f);
			//mc.renderGlobal.
			mc.gameSettings.mouseSensitivity = 0f;
			mc.gameSettings.saturation = 150f;
			//mc.gameSettings.isKeyDown(par0KeyBinding)				//useful?
			//mc.theWorld. = (Vec3) new MyVec3(new Vec3Pool(20,20), 50, 50, 50);
			//tested; 71089_bv, 70135_k, 71082_cx, 71085_br, 71097_bo
			mc.thePlayer.field_71097_bO = 50f;
			mc.thePlayer.dimension = -1;
		}
		else{
			mc.gameSettings.mouseSensitivity = 0.55f;
			//mc.playerController.
		}
		//mc.thePlayer.setPosition(1,1,1);
		//mc.thePlayer.setPosition(1,1,1);
	}
	
	/* Checks whether the x coordinate is outside minimum range */
	public boolean checkXMin(double x)
	{
		return x < xmin;
	}
	
	/* Checks whether the x coordinate is outside maximum range */
	public boolean checkXMax(double x)
	{
		return x > xmax;
	}
	
	/* Checks whether the Z coordinate is outside maximum range */
	public boolean checkZMax(double z)
	{
		return z > zmax;
	}
	
	/* Checks whether the Z coordinate is outside minimum range */
	public boolean checkZMin(double z)
	{
		return z < zmin;
	}
	public boolean checkLevelOneMoney(double m) 
	{
		return m == levelOneMoney;
	}
	public boolean checkLevelTwoMoney(double m) 
	{
		return m == levelTwoMoney;
	}
	public boolean checkLevelThreeMoney(double m) 
	{
		return m == levelThreeMoney;
	}
}
