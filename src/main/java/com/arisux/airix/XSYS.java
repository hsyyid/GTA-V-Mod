package com.arisux.airix;

import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.GL11;

/**
 * AIRIX System
 * @author Windows
 */
public class XSYS
{
	public static XSYS instance = new XSYS();
	
	public XSYS()
	{
		super();
	}

	public static XSYS instance()
	{
		return instance;
	}
    
	public String gpu()
    {
        return GL11.glGetString(GL11.GL_RENDERER);
    }

    public String gpuVendor()
    {
        return GL11.glGetString(GL11.GL_VENDOR);
    }

    public String cpu()
    {
        return System.getenv("processor.identifier");
    }

    public int cpuCores()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public String javaVersion()
    {
        return SystemUtils.JAVA_VERSION;
    }

    public String osName()
    {
        return System.getProperty("os.name");
    }

    public String osVersion()
    {
        return System.getProperty("os.version");
    }

    public String osArchitecture()
    {
        return System.getProperty("os.arch");
    }

    public long vmMemoryTotalBytes()
    {
        return Runtime.getRuntime().totalMemory();
    }

    public long vmMemoryMaxBytes()
    {
        return Runtime.getRuntime().maxMemory();
    }

    public long vmMemoryFreeBytes()
    {
        return Runtime.getRuntime().freeMemory();
    }
}
