package io.github.hsyyid.gta.entities.render;

import com.arisux.airix.api.wavefrontapi.Part;
import com.arisux.airix.api.wavefrontapi.WavefrontAPI;
import com.arisux.airix.api.wavefrontapi.WavefrontModel;

import io.github.hsyyid.gta.Main;
import io.github.hsyyid.gta.entities.EntityZentorno;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZentornoEntity extends Render
{
	WavefrontModel model = WavefrontAPI.instance().loadModel(Main.class, "gta", "aventador", "/assets/gta/models/aventador");

	public RenderZentornoEntity(RenderManager renderManager)
	{
		super(renderManager);
	}

	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}

	public void doRender(Entity zentornoIn, double posX, double posY, double posZ, float yaw, float partialTicks)
	{
		GlStateManager.pushMatrix();
		{
			if (zentornoIn.riddenByEntity != null && zentornoIn.riddenByEntity instanceof EntityPlayer)
			{
				EntityPlayer rider = (EntityPlayer) zentornoIn.riddenByEntity;
				GlStateManager.rotate(-(rider.rotationYawHead - 90), 0f, 1f, 0f);
			}
			GlStateManager.enableLighting();
			GlStateManager.translate(posX, posY, posZ);
			double curVelocity = Math.sqrt(zentornoIn.motionX * zentornoIn.motionX + zentornoIn.motionZ * zentornoIn.motionZ);
			float tireRotation = curVelocity > 0.1 ? -(zentornoIn.worldObj.getWorldTime() % 360 * 8) - partialTicks : 0;
			for (Part p : model.nameToPartHash.values())
			{
				if (p != null)
				{
					p.draw();
				}
			}
		}
		GlStateManager.popMatrix();
	}
}
