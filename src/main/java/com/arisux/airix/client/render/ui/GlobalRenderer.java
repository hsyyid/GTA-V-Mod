package com.arisux.airix.client.render.ui;

import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

import com.arisux.airix.ForgeModule;
import com.arisux.airix.client.render.ui.elements.GuiElement;

public class GlobalRenderer
{
    private ArrayList<GuiElement> elements;

    public GlobalRenderer()
    {
        this.elements = new ArrayList<GuiElement>();
    }

    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        for (GuiElement element : elements)
        {
            element.onTick();
        }
    }

    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        for (GuiElement element : elements)
        {
            element.onRender();
        }
    }

    public static final GlobalRenderer instance()
    {
        return ForgeModule.globalRenderer();
    }

    public ArrayList<GuiElement> getElements()
    {
        return this.elements;
    }
}
