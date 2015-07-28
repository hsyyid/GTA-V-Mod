package com.arisux.airix;

/**
 * AIRIX Logger
 * @author Ri5ux
 */
public class XLOG
{
	private static XLOG instance = new XLOG();
	
	public void info(String info, Object... args)
	{
		System.out.println(String.format("[AIRIX/INFO] %s", String.format(info, args)));
	}

	public void bug(String info, Object... args)
	{
		System.out.println(String.format("[AIRIX/BUG] %s. This should not happen.", String.format(info, args)));
	}

	public void warning(String warning, Object... args)
	{
		System.out.println(String.format("[AIRIX/WARNING] %s", String.format(warning, args)));
	}

	public static XLOG instance()
	{
		return instance;
	}
}