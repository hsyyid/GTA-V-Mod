package io.github.hsyyid.gta;

import com.arisux.airix.api.wavefrontapi.WavefrontAPI;
import com.arisux.airix.api.wavefrontapi.WavefrontModel;

import io.github.hsyyid.gta.entities.EntityVacca;
import io.github.hsyyid.gta.entities.EntityZentorno;
import io.github.hsyyid.gta.items.ItemVacca;
import io.github.hsyyid.gta.items.ItemZentorno;
import io.github.hsyyid.gta.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid="gta", version="1.0")
public class Main
{
	@SidedProxy(clientSide="io.github.hsyyid.gta.proxies.ClientProxy", serverSide="io.github.hsyyid.gta.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static String MODID = "gta";
	public static String VERSION = "1.0";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Register Items
		GameRegistry.registerItem(ItemZentorno.instance, ItemZentorno.name);
		GameRegistry.registerItem(ItemVacca.instance, ItemVacca.name);
		
		//Register Entities
		EntityRegistry.registerModEntity(EntityZentorno.class, "Zentorno", EntityRegistry.findGlobalUniqueEntityId(), this, 250, 50, true);
		EntityRegistry.registerModEntity(EntityVacca.class, "Vacca", EntityRegistry.findGlobalUniqueEntityId() + 1, this, 250, 50, true);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		
		if (event.getSide() == Side.CLIENT)
			proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
