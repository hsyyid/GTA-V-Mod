package com.arisux.airix;

import com.arisux.airix.client.render.ui.GlobalRenderer;
import com.arisux.airix.examples.ItemExamples;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = ForgeModule.MODID, version = ForgeModule.VERSION)
public class ForgeModule
{
	@Instance(ForgeModule.MODID)
	public static ForgeModule instance;
	
    public static final String MODID = "airix";
    public static final String VERSION = "1.0.0";
    private static GlobalRenderer globalRenderer;

	public static ForgeModule instance()
	{
		return instance;
	}

    public static final GlobalRenderer globalRenderer()
    {
        return globalRenderer == null ? globalRenderer = new GlobalRenderer() : globalRenderer;
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);

        new ItemExamples().register();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        ;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        globalRenderer().onClientTick(event);
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        globalRenderer().onRenderTick(event);
    }
}
