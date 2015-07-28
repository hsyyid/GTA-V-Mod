package com.arisux.airix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL12;

import com.arisux.airix.client.render.ui.UserInterface;
import com.arisux.airix.client.render.ui.elements.ITooltipLineHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * AIRIX UI Render
 * @author Windows
 */
public class XUIR
{
	public static XUIR instance = new XUIR();
	public final int FONT_HEIGHT = 8;
	public final String TOOLTIP_LINESPACE = "\u00A7h";
	public final String TOOLTIP_HANDLER = "\u00A7x";
	private ArrayList<ITooltipLineHandler> tipLineHandlers = new ArrayList<ITooltipLineHandler>();
	public final UserInterface globalInterface = new UserInterface();
	
	public XUIR()
	{
		super();
	}

	public static XUIR instance()
	{
		return instance;
	}

	public FontRenderer fontRenderer()
	{
		return AIRIX.game().fontRendererObj;
	}

	public int getRenderWidth(String s)
	{
		return fontRenderer().getStringWidth(s);
	}

	public void drawString(String s, int x, int y, int color, boolean shadow)
	{
		fontRenderer().drawString(s, x, y, color, shadow);
	}

	public void drawString(String s, int x, int y, int color)
	{
		drawString(s, x, y, color, true);
	}

	public void drawString(String s, int x, int y)
	{
		drawString(s, x, y, 0xFFFFFFFF);
	}

	public void drawStringCentered(String s, int x, int y, int color, boolean shadow)
	{
		drawString(s, x - (getRenderWidth(s) / 2), y, color, shadow);
	}

	public void drawStringCentered(String s, int x, int y, int color)
	{
		drawStringCentered(s, x, y, color, true);
	}

	public void drawStringCentered(String s, int x, int y)
	{
		drawStringCentered(s, x, y, 0xFFFFFFFF);
	}

	public void drawStringRight(String s, int x, int y, int color, boolean shadow)
	{
		drawString(s, x - getRenderWidth(s), y, color, shadow);
	}

	public void drawStringRight(String s, int x, int y, int color)
	{
		drawStringRight(s, x, y, color, true);
	}

	public void drawStringRight(String s, int x, int y)
	{
		drawStringRight(s, x, y, 0xFFFFFF);
	}

	public ScaledResolution getScaledResolution()
	{
		return new ScaledResolution(AIRIX.game(), AIRIX.game().displayWidth, AIRIX.game().displayHeight);
	}

	public Vec3 getScaledMousePosition()
	{
		ScaledResolution scaledResolution = getScaledResolution();
		int scaledMouseX = (Mouse.getX() * scaledResolution.getScaledWidth() / Display.getWidth());
		int scaledMouseY = scaledResolution.getScaledHeight() - (Mouse.getY() * scaledResolution.getScaledHeight() / Display.getHeight());
		return new Vec3(scaledMouseX, scaledMouseY, 0);
	}

	/**
	 * @return Returns the current game display width and height as a Dimension
	 */
	public Dimension getDisplayResolution()
	{
		Minecraft mc = Minecraft.getMinecraft();
		return new Dimension(mc.displayWidth, mc.displayHeight);
	}

	/**
	 * @return Returns the mouse location in-game.
	 */
	public Point getMouseLocation()
	{
		ScaledResolution size = getScaledResolution();
		Dimension res = getDisplayResolution();
		return new Point(Mouse.getX() * size.getScaledWidth() / res.width, size.getScaledHeight() - Mouse.getY() * size.getScaledHeight() / res.height - 1);
	}

	public int getTipLineId(ITooltipLineHandler handler)
	{
		tipLineHandlers.add(handler);
		return tipLineHandlers.size() - 1;
	}

	public ITooltipLineHandler getTipLine(String line)
	{
		return !line.startsWith(TOOLTIP_HANDLER) ? null : tipLineHandlers.get(Integer.parseInt(line.substring(2)));
	}

	/**
	 * Draws a multi-line tooltip at the specified cordinates.
	 *
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param list - List of Strings to show in the tooltip.
	 */
	public void drawMultilineToolTip(int x, int y, ArrayList<String> list)
	{
		if (list.isEmpty())
		{
			return;
		}
		glDisable(GL12.GL_RESCALE_NORMAL);
		glDisable(GL_DEPTH_TEST);
		RenderHelper.disableStandardItemLighting();
		int w = 0;
		int h = -2;

		for (int i = 0; i < list.size(); i++)
		{
			String s = list.get(i);
			ITooltipLineHandler line = getTipLine(s);
			Dimension d = line != null ? line.getSize() : new Dimension(getRenderWidth(s), list.get(i).endsWith(TOOLTIP_LINESPACE) && i + 1 < list.size() ? 12 : 10);
			w = Math.max(w, d.width);
			h += d.height;
		}

		if (x < 8)
		{
			x = 8;
		}
		else if (x > getScaledResolution().getScaledWidth() - w - 8)
		{
			x -= 24 + w;
		}
		y = (int) AIRIX.math().clip(y, 8, getScaledResolution().getScaledHeight() - 8 - h);

		globalInterface.incZLevel(300);
		drawTooltipBox(x - 4, y - 4, w + 7, h + 7);

		for (String s : list)
		{
			ITooltipLineHandler line = getTipLine(s);
			if (line != null)
			{
				line.draw(x, y);
				y += line.getSize().height;
			}
			else
			{
				drawString(s, x, y, -1);
				y += s.endsWith(TOOLTIP_LINESPACE) ? 12 : 10;
			}
		}

		tipLineHandlers.clear();
		globalInterface.incZLevel(-300);

		glEnable(GL_DEPTH_TEST);
		glEnable(GL12.GL_RESCALE_NORMAL);
	}

	/**
	 * Draws a tooltip box at the specified cordinates, with the specified width and height.
	 *
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param w - Width of the box
	 * @param h - Height of the box
	 */
	public void drawTooltipBox(int x, int y, int w, int h)
	{
		int bg = 0xf0100010;
		AIRIX.render().drawQuadColored(x + 1, y, w - 1, 1, bg);
		AIRIX.render().drawQuadColored(x + 1, y + h, w - 1, 1, bg);
		AIRIX.render().drawQuadColored(x + 1, y + 1, w - 1, h - 1, bg);
		AIRIX.render().drawQuadColored(x, y + 1, 1, h - 1, bg);
		AIRIX.render().drawQuadColored(x + w, y + 1, 1, h - 1, bg);
		int grad1 = 0x505000ff;
		int grad2 = 0x5028007F;
		globalInterface.drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2);
		globalInterface.drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2);
		globalInterface.drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1);
		globalInterface.drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2);
	}
}
