package com.arisux.airix.client.render.ui.elements;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import com.arisux.airix.AIRIX;

public class GuiButton extends GuiElement
{
    public GuiButton(ArrayList<GuiElement> elements)
    {
        super(elements);
    }

    @Override
    public void onRender()
    {
        super.onRender();

        if (this.isTextured())
        {
        	AIRIX.render().bindResource(this.getTexture().getResourceLocation());
        	AIRIX.render().drawQuad(this.getX(), this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), 0, this.getTexture().getUV().getMinU(), this.getTexture().getUV().getMinV(), this.getTexture().getUV().getMaxU(), this.getTexture().getUV().getMaxV());
        }

        AIRIX.render().drawQuadColored(this.getX(), this.getY(), this.getX() + this.getW(), this.getY() + this.getH(), this.isPressed() ? this.getColorActive() : (this.isHovered() ? this.getColorHover() : this.getColor()));
        AIRIX.uiRender().drawStringCentered(this.getText(), this.getX() + (this.getW() / 2), this.getY() + (this.getH() / 2) - AIRIX.uiRender().FONT_HEIGHT / 2, this.getColorForeground(), false);
    }

    @Override
    public void onTick()
    {
        this.setHovered(this.isMouseInElement(AIRIX.uiRender().getScaledMousePosition()));
        this.setPressed(Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.isHovered());

        if (this.isPressed())
        {
            this.action.onAction();
        }
    }
}
