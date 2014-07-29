package mod.bunkerbuilder;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class GameLevel {
	private int level;
	private int zmax, zmin, xmax, xmin; //restricting coordinates
	
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
		zmax = 148;
		zmin = 117;
		xmax = 1694;
		xmin = 1667;
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
    	GameValues.setValue(Minecraft.getMinecraft().thePlayer, timeLeft, "timer");
    	
    	/* Spawn defense block */
    	if(timeLeft == 59)
    		spawnLifeBlock((xmin+xmax)/2, 4, (zmin+zmax)/2);
		if(timeLeft == 0)
		{
			/* Timer is finished*/
			/* Call the explosives */
			if(level == 1)
			{
				ExplosivesSpawner.spawnExplosive(Minecraft.getMinecraft().theWorld, 0, (xmin+xmax)/2, 10, (zmin+zmax)/2 );
				//MinecraftForge.EVENT_BUS.register(new ExplosivesSpawner());
			}
		}else{
			/* Call game timer again after one second */
			Timer timer = new Timer();
			timer.schedule(new SimpleTimer(timeLeft-1), 1000);
		}
	}
	
	/*
	 * Creates the block that the player must defend in the game
	 * Takes coordinates to place the block
	 * 
	 */
	public void spawnLifeBlock(int x, int y, int z)
	{
		World world = Minecraft.getMinecraft().theWorld;
		world.setBlock(x, y, z, Blocks.dirt, 0, 0x02);
		System.out.println("x: " + x +  "Y: " + y + "Z: " + z);
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
			//mc.thePlayer.rotationPitch = 90;
			//mc.thePlayer.rotationYaw = 90;
			mc.thePlayer.setPosition(vec.xCoord, 20, vec.zCoord);
			//mc.thePlayer.eyeHeight = 20f;
			//mc.thePlayer.capabilities.setFlySpeed(1.5f);
			//mc.renderGlobal.
			mc.gameSettings.mouseSensitivity = 0f;
			mc.gameSettings.saturation = 150f;
			//mc.gameSettings.isKeyDown(par0KeyBinding)				//useful?
			//mc.objectMouseOver.hitVec = new Vec3(new Vec3Pool(0,0), 0, 20, 0);
		}
		else{
			mc.gameSettings.mouseSensitivity = 0.55f;
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
