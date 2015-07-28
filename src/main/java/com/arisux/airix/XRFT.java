package com.arisux.airix;

import java.lang.reflect.Field;

/**
 * AIRIX Reflect
 * @author Windows
 */
public class XRFT
{
	public static XRFT instance = new XRFT();
	
	public XRFT()
	{
		super();
	}
	
	public static XRFT instance()
	{
		return instance;
	}
	
    public double getDouble(Object obj, String deobfName, String obfName)
    {
        return ((Double) get(obj, deobfName, obfName)).doubleValue();
    }

    public float getFloat(Object obj, String deobfName, String obfName)
    {
        return ((Float) get(obj, deobfName, obfName)).floatValue();
    }

    public int getInt(Object obj, String deobfName, String obfName)
    {
        return ((Integer) get(obj, deobfName, obfName)).intValue();
    }

    public boolean getBoolean(Object obj, String deobfName, String obfName)
    {
        return ((Boolean) get(obj, deobfName, obfName)).booleanValue();
    }

    public long getLong(Object obj, String deobfName, String obfName)
    {
        return ((Long) get(obj, deobfName, obfName)).longValue();
    }

    public byte getByte(Object obj, String deobfName, String obfName)
    {
        return ((Byte) get(obj, deobfName, obfName)).byteValue();
    }

    public String getString(Object obj, String deobfName, String obfName)
    {
        return (String) get(obj, deobfName, obfName);
    }

    public void set(Object obj, String deobfName, String obfName, Object value)
    {
        String fieldName = AIRIX.isDevEnvironment() ? deobfName : obfName;

        try
        {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        }
        catch (Exception e)
        {
        	AIRIX.logger().warning("Failed setting field %s to %s: %s", fieldName, value, e);
        }
    }

    public Object get(Object obj, String deobfName, String obfName)
    {
        String fieldName = AIRIX.isDevEnvironment() ? deobfName : obfName;

        try
        {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (Exception e)
        {
        	AIRIX.logger().warning("Failed getting field %s: %s", fieldName, e);
        }
        return null;
    }
}