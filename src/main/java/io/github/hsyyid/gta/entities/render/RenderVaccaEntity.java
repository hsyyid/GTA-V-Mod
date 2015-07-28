package io.github.hsyyid.gta.entities.render;

import com.arisux.airix.api.wavefrontapi.Part;
import com.arisux.airix.api.wavefrontapi.WavefrontAPI;
import com.arisux.airix.api.wavefrontapi.WavefrontModel;

import io.github.hsyyid.gta.Main;
import io.github.hsyyid.gta.entities.EntityVacca;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVaccaEntity extends Render
{
	WavefrontModel m = WavefrontAPI.instance().loadModel(Main.class, "gta", "california", "/assets/gta/models/california");

	public RenderVaccaEntity(RenderManager renderManager)
	{
		super(renderManager);
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}

	@Override
	public void doRender(Entity vaccaIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		{
			if (vaccaIn.riddenByEntity != null && vaccaIn.riddenByEntity instanceof EntityPlayer)
			{
				EntityPlayer rider = (EntityPlayer) vaccaIn.riddenByEntity;
				GlStateManager.rotate(-(rider.rotationYawHead), 0f, 1f, 0f);
			}
			GlStateManager.translate(posX, posY + 0.8, posZ);
			double curVelocity = Math.sqrt(vaccaIn.motionX * vaccaIn.motionX + vaccaIn.motionZ * vaccaIn.motionZ);
			float tireRotation = curVelocity > 0.1 ? -(vaccaIn.worldObj.getWorldTime() % 360 * 8) - partialTicks : 0;
			GlStateManager.rotate(-90f, 1f, 0f, 0f);
			for(Part p : m.nameToPartHash.values())
			{
				p.draw();
			}
		}
		GlStateManager.popMatrix();
	}
}