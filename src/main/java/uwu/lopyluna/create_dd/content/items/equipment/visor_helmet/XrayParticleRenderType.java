package uwu.lopyluna.create_dd.content.items.equipment.visor_helmet;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import org.jetbrains.annotations.NotNull;

public class XrayParticleRenderType implements ParticleRenderType {

    public static final XrayParticleRenderType INSTANCE = new XrayParticleRenderType();

    @SuppressWarnings("deprecation")
    @Override
    public void begin(BufferBuilder builder, @NotNull TextureManager textureManager) {
        RenderSystem.depthMask(false);
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
    }

    @Override
    public void end(Tesselator tessellator) {
        tessellator.end();
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask(true);
    }

    @Override
    public String toString() {
        return "XRAY_PARTICLE_RENDER";
    }
}