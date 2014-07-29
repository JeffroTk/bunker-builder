package mod.bunkerbuilder;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class CustomGUI extends Gui {
	private Minecraft mc;

	int xPos = 2;
	int yPos = 2;
	
	public CustomGUI(Minecraft mc){
		super();
		this.mc=mc;
	}
	
	/**
	 * This method is what actually writes the text onto the screen
	 * 
	 */
	@SubscribeEvent
	public void onRenderMoney(RenderGameOverlayEvent event){
		if (event.isCancelable())
			return;
		FontRenderer fontRenderer = this.mc.fontRenderer;
		this.drawString(fontRenderer, "Money: "+GameValues.getMoney((EntityPlayer)mc.thePlayer), 1, 1, 0xffffffff);
		this.drawString(fontRenderer, "Time Until Explosives: "+(int)(GameValues.getVal((EntityPlayer)mc.thePlayer, "timer"))+" seconds", 100, 1, 0xffffffff);
	}
}
