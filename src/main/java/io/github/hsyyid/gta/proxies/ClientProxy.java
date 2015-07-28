package io.github.hsyyid.gta.proxies;

import io.github.hsyyid.gta.entities.EntityVacca;
import io.github.hsyyid.gta.entities.EntityZentorno;
import io.github.hsyyid.gta.entities.render.RenderVaccaEntity;
import io.github.hsyyid.gta.entities.render.RenderZentornoEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityZentorno.class, new RenderZentornoEntity(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityVacca.class, new RenderVaccaEntity(Minecraft.getMinecraft().getRenderManager()));
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
