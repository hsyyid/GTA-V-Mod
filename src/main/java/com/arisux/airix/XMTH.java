package com.arisux.airix;

/**
 * AIRIX Math
 * @author Windows
 */
public class XMTH
{
	public static XMTH instance = new XMTH();
	
    public final double PHI = 1.618033988749894D;
    public final double PI = Math.PI;
    public final double TO_DEG = 57.29577951308232D;
    public final double TO_RAD = 0.017453292519943D;
    public final double SQRT2 = 1.414213562373095D;
    public double[] SIN_TABLE = new double[65536];

    public XMTH()
    {
        for (int i = 0; i < 65536; ++i)
        {
            SIN_TABLE[i] = sin(i / 65536D * 2 * PI);
        }

        SIN_TABLE[0] = 0;
        SIN_TABLE[16384] = 1;
        SIN_TABLE[32768] = 0;
        SIN_TABLE[49152] = 1;
    }

	public static XMTH instance()
	{
		return instance;
	}

    public double sin(double d)
    {
        return SIN_TABLE[(int) ((float) d * 10430.378F) & 65535];
    }

    public double cos(double d)
    {
        return SIN_TABLE[(int) ((float) d * 10430.378F + 16384.0F) & 65535];
    }

    public float approachLinear(float a, float b, float max)
    {
        return (a > b) ? (a - b < max ? b : a - max) : (b - a < max ? b : a + max);
    }

    public double approachLinear(double a, double b, double max)
    {
        return (a > b) ? (a - b < max ? b : a - max) : (b - a < max ? b : a + max);
    }

    public float interpolate(float a, float b, float d)
    {
        return a + (b - a) * d;
    }

    public double interpolate(double a, double b, double d)
    {
        return a + (b - a) * d;
    }

    public double approachExp(double a, double b, double ratio)
    {
        return a + (b - a) * ratio;
    }

    public double approachExp(double a, double b, double ratio, double cap)
    {
        double d = (b - a) * ratio;
        if (Math.abs(d) > cap)
            d = Math.signum(d) * cap;
        return a + d;
    }

    public double retreatExp(double a, double b, double c, double ratio, double kick)
    {
        double d = (Math.abs(c - a) + kick) * ratio;
        if (d > Math.abs(b - a))
            return b;
        return a + Math.signum(b - a) * d;
    }

    public double clip(double value, double min, double max)
    {
        if (value > max)
            value = max;
        if (value < min)
            value = min;
        return value;
    }

    public boolean between(double a, double x, double b)
    {
        return a <= x && x <= b;
    }

    public int approachExpI(int a, int b, double ratio)
    {
        int r = (int) Math.round(approachExp(a, b, ratio));
        return r == a ? b : r;
    }

    public int retreatExpI(int a, int b, int c, double ratio, int kick)
    {
        int r = (int) Math.round(retreatExp(a, b, c, ratio, kick));
        return r == a ? b : r;
    }

    public int floor_double(double d)
    {
        return net.minecraft.util.MathHelper.floor_double(d);
    }

    public int roundAway(double d)
    {
        return (int) (d < 0 ? Math.floor(d) : Math.ceil(d));
    }

    public int compare(int a, int b)
    {
        return a == b ? 0 : a < b ? -1 : 1;
    }

    public int compare(double a, double b)
    {
        return a == b ? 0 : a < b ? -1 : 1;
    }
}