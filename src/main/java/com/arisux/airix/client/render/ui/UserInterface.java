package com.arisux.airix.client.render.ui;

import java.util.ArrayList;

import com.arisux.airix.client.render.ui.elements.GuiElement;

import net.minecraft.client.gui.GuiScreen;

public class UserInterface extends GuiScreen
{
    public ArrayList<GuiElement> elements = new ArrayList<GuiElement>();

    @Override
    public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        super.drawGradientRect(par1, par2, par3, par4, par5, par6);
    }

    public void setZLevel(float f)
    {
        this.zLevel = f;
    }

    public float getZLevel()
    {
        return this.zLevel;
    }

    public void incZLevel(float f)
    {
        this.zLevel += f;
    }
}