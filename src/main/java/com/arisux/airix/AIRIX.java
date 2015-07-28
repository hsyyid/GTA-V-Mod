package com.arisux.airix;

import com.arisux.airix.api.wavefrontapi.WavefrontAPI;

import net.minecraft.client.Minecraft;

/**
 * AIRIX Main
 * @author Windows
 */
public class AIRIX
{
    public static Minecraft game()
    {
        return Minecraft.getMinecraft();
    }
    
    public static ForgeModule mod()
    {
    	return ForgeModule.instance();
    }
    
    public static WavefrontAPI wavefrontAPI()
    {
    	return WavefrontAPI.instance();
    }
    
    public static XLOG logger()
	{
		return XLOG.instance();
	}
    
    public static XMTH math()
	{
		return XMTH.instance();
	}
    
    public static XRDR render()
    {
    	return XRDR.instance();
    }
    
    public static XRFT reflect()
    {
    	return XRFT.instance();
    }
    
    public static XSYS system()
    {
    	return XSYS.instance();
    }
    
    public static XUIR uiRender()
    {
    	return XUIR.instance();
    }

    /**
     * Returns if the current Minecraft installation is running
     * in a development environment or normal environment.
     *
     * @return Returns true if in a dev environment. Returns false if other.
     */
    public static boolean isDevEnvironment()
    {
        return (Boolean) net.minecraft.launchwrapper.Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }
}
